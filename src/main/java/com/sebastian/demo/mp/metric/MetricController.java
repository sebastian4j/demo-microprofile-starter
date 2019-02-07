package com.sebastian.demo.mp.metric;

import java.util.Random;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Metric;
import org.eclipse.microprofile.metrics.annotation.Timed;

/**
 * http://localhost:8080/metrics
 *
 * http://localhost:8080/metrics/base
 *
 * http://localhost:8080/metrics/application
 *
 * http://localhost:8080/metrics/vendor
 */
@Path("/metric")
@ApplicationScoped
public class MetricController {

  @Inject
  @Metric(name = "nombre_metrica")
  private Counter counter;

  @Path("timed")
  @Timed(name = "metodo_timed")
  @GET
  public String timedRequest() {
    // Demo, not production style
    final int wait = new Random().nextInt(1000);
    try {
      Thread.sleep(wait);
    } catch (final InterruptedException e) {
      // Demo
      e.printStackTrace();
    }

    return "Request is used in statistics, check with the Metrics call.";
  }


  @Path("increment")
  @GET
  public long doIncrement() {
    counter.inc();
    return counter.getCount();
  }

  @Gauge(name = "gauge_contador", unit = "unidad_gauge")
  private long getCustomerCount() {
    return counter.getCount();
  }
}
