package org.crypto.resource;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api/v1") // Toutes vos routes commenceront par /api/v1
public class ApplicationConfig extends Application {
    public static final String FILES_DIR = "MonBarra/files";
}
