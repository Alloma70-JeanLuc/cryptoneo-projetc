/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crypto.security;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

/**
 *
 * @author Gilles N'GUESSAN
 */
@Provider
@PreMatching
public class CORSFilter implements ContainerResponseFilter {

   @Override
   public void filter(final ContainerRequestContext requestContext,
                      final ContainerResponseContext cres) throws IOException {
      // Allow all origins for development
      cres.getHeaders().add("Access-Control-Allow-Origin", "*");
      
      // Allow specific headers
      cres.getHeaders().add("Access-Control-Allow-Headers", 
          "origin, content-type, accept, authorization, x-requested-with, cache-control, pragma");
      
      // Allow credentials
      cres.getHeaders().add("Access-Control-Allow-Credentials", "true");
      
      // Allow all methods
      cres.getHeaders().add("Access-Control-Allow-Methods", 
          "GET, POST, PUT, DELETE, OPTIONS, HEAD, PATCH");
      
      // Cache preflight response for 20 days
      cres.getHeaders().add("Access-Control-Max-Age", "1728000");
      
      // Handle preflight requests
      if (requestContext.getMethod().equals("OPTIONS")) {
         cres.setStatus(200);
      }
   }
    
}
