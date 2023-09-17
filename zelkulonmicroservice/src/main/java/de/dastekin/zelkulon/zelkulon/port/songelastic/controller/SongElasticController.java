package de.dastekin.zelkulon.zelkulon.port.songelastic.controller;

import de.dastekin.zelkulon.zelkulon.core.domain.model.SongElastic;
import de.dastekin.zelkulon.zelkulon.core.domain.service.impl.SongElasticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SongElasticController {
    @Autowired
    private SongElasticService service;

    @PostMapping("/songElastic")
    public void addSong(@RequestBody SongElastic songElastic) {
        service.saveSong(songElastic);
    }
}
