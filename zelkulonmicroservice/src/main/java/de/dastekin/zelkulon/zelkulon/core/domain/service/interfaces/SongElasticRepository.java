package de.dastekin.zelkulon.zelkulon.core.domain.service.interfaces;

import de.dastekin.zelkulon.zelkulon.core.domain.model.SongElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SongElasticRepository extends ElasticsearchRepository<SongElastic, String> {
}
