package com.fri.musicbook.health;

import com.fri.musicbook.AlbumsBean;
import com.kumuluz.ee.discovery.annotations.DiscoverService;
import com.kumuluz.ee.discovery.enums.AccessType;
import com.kumuluz.ee.discovery.utils.DiscoveryUtil;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@Health
@ApplicationScoped
public class checkIfGetSongs implements HealthCheck {
    @Inject
    DiscoveryUtil discoveryUtil;

    @Override
    public HealthCheckResponse  call() {
        Optional<List<URL>> instances = discoveryUtil.getServiceInstances("songs", "*", "*",AccessType.DIRECT);


        if(instances.isPresent()){
            if(instances.get().isEmpty()) return HealthCheckResponse.named(checkIfGetSongs.class.getSimpleName()).down().build();
            System.out.println(instances.get());
            return HealthCheckResponse.named(checkIfGetSongs.class.getSimpleName()).up().build();
        }
        else {
            return HealthCheckResponse.named(checkIfGetSongs.class.getSimpleName()).down().build();
        }
    }
}

