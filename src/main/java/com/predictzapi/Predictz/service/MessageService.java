package com.predictzapi.Predictz.service;

import com.predictzapi.Predictz.model.Message;
import com.predictzapi.Predictz.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;

    public void saveMessage(Message message){
        messageRepo.save(message);
    }

    public List<Message> getMessages(){
        return messageRepo.findAll();
    }


    public Long getCount() {
        return messageRepo.count();
    }
}
