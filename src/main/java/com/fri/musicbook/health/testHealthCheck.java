package com.fri.musicbook.health;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;

@Health
@ApplicationScoped
public class testHealthCheck implements HealthCheck {


    @Override
    public HealthCheckResponse call(){
        if(Tools.health) {
            return HealthCheckResponse.named(testHealthCheck.class.getSimpleName()).up().build();
        }else{
            return HealthCheckResponse.named(testHealthCheck.class.getSimpleName()).down().build();
        }
    }
}
