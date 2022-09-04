package com.predictzapi.Predictz.service;

import com.predictzapi.Predictz.model.TelegramInteraction;
import com.predictzapi.Predictz.repository.TelegramRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TelegramService {
    @Autowired
    private TelegramRepo telegramRepo;

    public void saveClick(){
        telegramRepo.save(new TelegramInteraction());
    }

    public long getCount(){
        return telegramRepo.count();
    }
}
