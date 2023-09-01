/**
 * Dastekin created on 01.09.2023 the EndpointsListener-Class inside the package - de.dastekin.zelkulon.songs.core.domain.service.impl
 */

package de.dastekin.zelkulon.songs.core.domain.service.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class EndpointsListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger logger = LoggerFactory.getLogger(EndpointsListener.class);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        logger.info("Application is ready. Listing all mapped paths:");
        requestMappingHandlerMapping.getHandlerMethods().forEach((key, value) -> logger.info("Mapped URL path: {}", key));
    }
}
