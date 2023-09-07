package de.dastekin.zelkulon.songs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@SpringBootApplication
@EnableDiscoveryClient
public class SongMicroserviceMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(SongMicroserviceMainApplication.class, args);

    }



    /**
     * Erzeugt ein Bean mit Load-Balancing-Fähigkeiten.
     * Das RestTemplate ist mit dem @LoadBalanced annotiert,
     * um automatisches clientseitiges Load-Balancing durch den Ribbon Load-Balancer zu ermöglichen.
     *
     * @return eine neue  RestTemplate Instanz mit Load-Balancing-Fähigkeiten
     */
    @Bean
    @LoadBalanced //TODO Loadbalancer rein reaus?
    public RestTemplate loadBalancedRestTemplate() {
        return new RestTemplate();
    }

    @RestController
    class ServiceInstanceRestController {
        private static final Logger log = LoggerFactory.getLogger(ServiceInstanceRestController.class);


        @Autowired(required = false) //TODO: remove this
        private DiscoveryClient discoveryClient;

        /**
         * Returns all the instances of the service
         *
         * @param applicationName - the name of the service
         * @return - the list of all the instances of the service
         */
        @RequestMapping("/service-instances/{applicationName}")
        public List<ServiceInstance> serviceInstancesByApplicationName(
                @PathVariable String applicationName) {
            log.debug("Looking for instances of application: {}", applicationName);
            List<ServiceInstance> instances = this.discoveryClient.getInstances(applicationName);
            log.debug("Found instances: {}", instances);
            return instances;
        }
    }


}
