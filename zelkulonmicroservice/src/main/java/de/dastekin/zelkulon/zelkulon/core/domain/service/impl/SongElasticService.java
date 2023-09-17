package de.dastekin.zelkulon.zelkulon.core.domain.service.impl;

import de.dastekin.zelkulon.zelkulon.core.domain.model.SongElastic;
import de.dastekin.zelkulon.zelkulon.core.domain.service.interfaces.SongElasticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongElasticService {
    @Autowired
    private SongElasticRepository songElasticRepository;

    public void saveSong(SongElastic songElastic) {
        songElasticRepository.save(songElastic);
    }
}
