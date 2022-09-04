package com.predictzapi.Predictz.service;

import com.predictzapi.Predictz.model.VipPick;
import com.predictzapi.Predictz.repository.VipPickRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VipPickService {
    @Autowired
    private VipPickRepo pickRepo;

    public VipPick savePick(VipPick pick){
        return pickRepo.save(pick);
    }
//    Todo: complete the update function
    public VipPick updatePick(long id, VipPick pick){
        return null;
    }

    public List<VipPick> getPicks(){
        return pickRepo.findAll();
    }
}
