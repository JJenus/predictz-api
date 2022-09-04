package com.predictzapi.Predictz.service;

import com.predictzapi.Predictz.model.ApiLimit;
import com.predictzapi.Predictz.model.SuperPick;
import com.predictzapi.Predictz.repository.ApiLimitRepo;
import com.predictzapi.Predictz.repository.SuperPickRepo;
import com.predictzapi.Predictz.util.api.SportsBettingPredictions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
@Slf4j
public class SuperPicksService {
    private SportsBettingPredictions predictions = new SportsBettingPredictions();
    @Autowired
    private SuperPickRepo superPickRepo;
    @Autowired
    private ApiLimitRepo apiLimitRepo;
    private int retries = 0;

    public List<SuperPick> getPicks() {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        System.out.println("Date: "+date);
        log.debug("Date: "+date);
        List<SuperPick> superPicks = superPickRepo.findByGameDate(date);

        if (superPicks.isEmpty()){
            ApiLimit limit = apiLimitRepo.findByApiName(SportsBettingPredictions.NAME);
            if (limit == null) {
                limit = lockTransaction(
                        new ApiLimit(
                                SportsBettingPredictions.NAME,
                                0,
                                SportsBettingPredictions.limit,
                                "open"
                        )
                );
            }

            if (! (limit.getCurrentRate() <= limit.getRateLimit()/2))
                return superPicks;
            if (limit.getStatus().equalsIgnoreCase("loading")){
                return superPicks;
            }
            limit.setCurrentRate(0);
            lockTransaction(limit);
            superPicks.addAll(predictions.getSuperPicks(date));
            ApiLimit finalLimit = limit;
            new Thread(()->{
                superPickRepo.saveAll(superPicks);
                unlockTransaction(finalLimit);
            }).start();
        }
        return superPicks;
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
