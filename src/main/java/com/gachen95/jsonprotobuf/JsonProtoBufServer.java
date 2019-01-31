package com.gachen95.jsonprotobuf;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class JsonProtoBufServer {
    public static void main( String[] args ) throws Exception
    {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/person");

        context.addServlet(new ServletHolder(new SavePerson()), "/save/*");

        Server jettyServer = new Server(8080);
        jettyServer.setHandler(context);

        // Start the server
        try {
            jettyServer.start();
            jettyServer.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            jettyServer.destroy();
        }
    }
}
