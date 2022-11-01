package com.predictzapi.Predictz.service;

import com.predictzapi.Predictz.model.ApiLimit;
import com.predictzapi.Predictz.model.FreePick;
import com.predictzapi.Predictz.repository.ApiLimitRepo;
import com.predictzapi.Predictz.repository.FreePickRepo;
import com.predictzapi.Predictz.util.api.FootballPrediction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
@Slf4j
public class FreePickService {
    FootballPrediction footballPrediction;
    @Autowired
    FreePickRepo freePickRepo;

    private int retries = 0;
    @Autowired
    private ApiLimitRepo apiLimitRepo;

    public FreePickService(){
        footballPrediction = new FootballPrediction();
    }

    public List<FreePick> getPicks() {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        log.debug("Date: "+date);
        List<FreePick> freePicks = freePickRepo.findByGameDate(date);

        if (freePicks.isEmpty()){
            log.debug("Status: Is empty");
            ApiLimit limit = apiLimitRepo.findByApiName(FootballPrediction.NAME);
            if (limit != null){
                if (limit.getStatus().equalsIgnoreCase("loading") || ! (limit.getCurrentRate() <= limit.getRateLimit()/2)){
                    return freePicks;
                }
            }
            else {
                limit = lockTransaction(
                        new ApiLimit(
                                FootballPrediction.NAME,
                                0,
                                FootballPrediction.limit,
                                "open"
                        )
                );
            }

            limit.setCurrentRate(0);
            lockTransaction(limit);
            freePicks.addAll(footballPrediction.getFreePicks(date));
            ApiLimit finalLimit = limit;
            new Thread(()->{
                freePickRepo.saveAll(freePicks);
                unlockTransaction(finalLimit);
            }).start();
        } else {
            log.debug("Not empty");
        }
        return freePicks;
    }

    private void unlockTransaction(ApiLimit limit) {
        limit.setStatus("open");
        apiLimitRepo.save(limit);
    }

    private ApiLimit lockTransaction(ApiLimit limit) {
        limit.setCurrentRate(limit.getCurrentRate()+1);
        limit.setStatus("loading");

        return apiLimitRepo.save(limit);
    }
}
