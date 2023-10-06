package org.shubham;
import org.glassfish.jersey.server.ResourceConfig;

public class RouterConfig extends ResourceConfig {
    public RouterConfig() {
        packages("org.shubham.routes");  // Assuming "com.example" is where your Jersey resources are located
    }
}
