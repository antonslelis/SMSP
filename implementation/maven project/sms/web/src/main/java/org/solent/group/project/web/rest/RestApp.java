package org.solent.group.project.web.rest;

import org.glassfish.jersey.server.ResourceConfig;

public class RestApp extends ResourceConfig {

    public RestApp() {
        packages("org.solent.group.project.web.rest");
    }
}
