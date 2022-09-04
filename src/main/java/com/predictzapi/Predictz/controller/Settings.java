package com.predictzapi.Predictz.controller;

import com.predictzapi.Predictz.model.Setting;
import com.predictzapi.Predictz.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("settings")
public class Settings {
    @Autowired
    private SettingsService settingsService;

    @PostMapping
    public Setting setting(@RequestBody Setting setting){
        return settingsService.saveSetting(setting);
    }

    @GetMapping
    public Setting getSettings(){
        return settingsService.getSettings();
    }

    @PostMapping("/password")
    public void changePassword(){}
}
