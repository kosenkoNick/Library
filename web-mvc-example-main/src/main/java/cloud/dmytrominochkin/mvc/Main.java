package cloud.dmytrominochkin.mvc;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Main {

    public static void main(String[] args) throws Exception {
        new Main().startJetty();
    }

    private void startJetty() throws Exception {
        Server server = new Server(8081);
        server.setHandler(getServletContextHandler());

        server.start();
        server.join();
    }

    private static ServletContextHandler getServletContextHandler() {
        var contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.setErrorHandler(null);

        contextHandler.setContextPath("/");

        // Spring
        var webAppContext = getWebApplicationContext();
        var dispatcherServlet = new DispatcherServlet(webAppContext);
        var springServletHolder = new ServletHolder("dispatcherServlet", dispatcherServlet);
        contextHandler.addServlet(springServletHolder, "/*");
        contextHandler.addEventListener(new ContextLoaderListener(webAppContext));

        return contextHandler;
    }

    private static WebApplicationContext getWebApplicationContext() {
        var context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("cloud.dmytrominochkin.mvc.config");
        return context;
    }

}
