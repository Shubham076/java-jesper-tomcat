package org.shubham;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.Context;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class Main {

    static {
        // Optionally remove existing handlers attached to the JUL root logger
        SLF4JBridgeHandler.removeHandlersForRootLogger();

        // Bridge JUL to SLF4J
        SLF4JBridgeHandler.install();
    }
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        // Define a web context for our application.
        Context ctx = tomcat.addWebapp("", System.getProperty("java.io.tmpdir"));
        Tomcat.addServlet(ctx, "jersey-container-servlet", new ServletContainer(new JerseyConfig()));
        ctx.addServletMappingDecoded("/*", "jersey-container-servlet");

        //logging requests
        // Define and map the filter.
        FilterDef requestLoggingFilter = new FilterDef();
        requestLoggingFilter.setFilterName("requestLoggingFilter");
        requestLoggingFilter.setFilterClass(RequestLoggingFilter.class.getName());
        ctx.addFilterDef(requestLoggingFilter);

        FilterMap filterMapping = new FilterMap();
        filterMapping.setFilterName("requestLoggingFilter");
        filterMapping.addURLPattern("/*");  // map filter to all URLs
        ctx.addFilterMap(filterMapping);

        tomcat.start();
        logger.info("Server started on port: {}", 8080);
        tomcat.getServer().await();
    }
}