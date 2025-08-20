package org.crypto.core;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject; // <-- N'oublie pas d'importer Inject
import org.eclipse.microprofile.config.inject.ConfigProperty; // <-- Et ConfigProperty

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AppLifecycleBean {

    // On injecte les propriétés directement. C'est plus propre et plus sûr.
    @Inject
    @ConfigProperty(name = "upload.base-dir")
    String baseDir; // Cette variable va maintenant contenir "/tmp/crypto-files"

    @Inject
    @ConfigProperty(name = "upload.files_dir")
    String filesDir;

    @Inject
    @ConfigProperty(name = "upload.template")
    String templateDir;

    void onStart(@Observes StartupEvent ev) {
        // ON N'UTILISE PLUS LA PROPRIÉTÉ KUBERNETES ICI
        // String mountedFolder = ...

        System.out.println("<=========== Création des dossiers de l'application sur la base de : " + baseDir + " =========>");

        List<String> folders = new ArrayList<>();

        // On construit les chemins en utilisant notre nouvelle propriété 'baseDir'
        folders.add(Paths.get(baseDir, filesDir).toString());
        folders.add(Paths.get(baseDir, templateDir).toString());

        folders.forEach(this::createFolders);
    }

    private void createFolders(String folderName) {
        System.out.println("createFolders:::::::::::::::>");
        try {
            System.out.println("    > " + folderName);
            File directory = new File(folderName);
            if (!directory.exists()) {
                directory.mkdirs();
                System.out.println("     > created");
            } else {
                System.out.println("     > already exist");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("     > error : " + e.getMessage());
        }
    }

    void onStop(@Observes ShutdownEvent ev) {
    }
}