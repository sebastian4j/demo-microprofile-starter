package com.sebastian.demo.mp.secure;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.ClaimValue;
import org.eclipse.microprofile.jwt.Claims;

/**
 *
 */
@Path("/protected")
@RequestScoped
public class ProtectedController {
  @Inject
  @Claim("custom-value")
  private ClaimValue<String> custom;
  @Inject
  @Claim(standard = Claims.raw_token)
  private String token;

  @GET
  @RolesAllowed("protected")
  public String getJWTBasedValue() {
    return "Protected Resource; Custom value : " + custom.getValue() + " token: " + token;
  }
}

