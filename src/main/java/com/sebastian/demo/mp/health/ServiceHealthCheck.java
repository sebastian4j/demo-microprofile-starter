package com.sebastian.demo.mp.health;

import javax.enterprise.context.ApplicationScoped;
/**
 * http://localhost:8080/health
 */
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@Health
@ApplicationScoped
public class ServiceHealthCheck implements HealthCheck {

  @Override
  public HealthCheckResponse call() {

    return HealthCheckResponse.named(ServiceHealthCheck.class.getSimpleName()).up().build();

  }
}
