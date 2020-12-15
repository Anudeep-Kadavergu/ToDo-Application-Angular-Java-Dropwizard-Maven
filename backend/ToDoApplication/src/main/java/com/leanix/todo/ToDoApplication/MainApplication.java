package com.leanix.todo.ToDoApplication;

import com.leanix.todo.ToDoApplication.controller.ToDoController;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import java.util.EnumSet;


public class MainApplication extends Application<MainConfiguration> {

    public static void main(final String[] args) throws Exception {
        new MainApplication().run(args);
    }

    @Override
    public String getName() {
        return "Main";
    }

    @Override
    public void initialize(final Bootstrap<MainConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final MainConfiguration configuration,final Environment environment) {
    	
    	 // Enable CORS headers
        final FilterRegistration.Dynamic cors =
            environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        environment.jersey().register(new ToDoController(environment.getValidator()));
    }

}
