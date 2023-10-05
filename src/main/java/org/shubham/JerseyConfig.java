package org.shubham;
import org.glassfish.jersey.server.ResourceConfig;

public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("org.shubham.routes");  // Assuming "com.example" is where your Jersey resources are located
    }
}
