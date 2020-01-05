package logic;

import endpoints.AuthorizationFilter;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.DispatcherType;
import java.util.EnumSet;


public class Server {


    public static void main(String[] args) throws Exception {
        org.eclipse.jetty.server.Server server = new org.eclipse.jetty.server.Server();
        org.eclipse.jetty.server.ServerConnector serverConnector = new org.eclipse.jetty.server.ServerConnector(server);
        serverConnector.setPort(8094);

        HttpConfiguration https = new HttpConfiguration();
        https.addCustomizer(new SecureRequestCustomizer());

        SslContextFactory sslContextFactory = new SslContextFactory();
        sslContextFactory.setKeyStorePath("C:/Users/manol/IdeaProjects/Beast/ssl/manolo.pkcs12");
        sslContextFactory.setKeyStorePassword("manolo");

        org.eclipse.jetty.server.ServerConnector sslConnector = new org.eclipse.jetty.server.ServerConnector(server,
                new SslConnectionFactory(sslContextFactory, "http/1.1"),
                new HttpConnectionFactory(https));
        sslConnector.setPort(8095);

        server.setConnectors(new Connector[]{ serverConnector, sslConnector});


//        server.setHandler(getJerseyHandler());


        server.setHandler(getJerseyHandler());
        //Multiple handlers

        server.start();
        server.join();

    }

    private static Handler getJerseyHandler() {
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        handler.setContextPath("/");

        //handler.addFilter(AuthorizationFilter.class, "/*", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));

        ServletHolder servletHolder = handler.addServlet(ServletContainer.class, "/*");
        servletHolder.setInitOrder(0);
        servletHolder.setInitParameter("jersey.config.server.provider.packages", "endpoints"
                );


        return handler;
    }


}

