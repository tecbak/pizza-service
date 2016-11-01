package ua.rd.pizzaservice.web.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.pizzaservice.services.PizzaService;
import ua.rd.pizzaservice.services.simple.SimplePizzaService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DispatcherServlet extends HttpServlet {

    private ConfigurableApplicationContext webContext;
    private ConfigurableApplicationContext[] applicationContexts;
//    @Qualifier("simplePizzaService")

//    @Override
//    public void init() throws ServletException {
//        String contextsLocations = getServletContext().getInitParameter("contextConfigLocation");
//        String[] contexts = contextsLocations.split(" ");
//
//        applicationContexts = new ConfigurableApplicationContext[contexts.length];
//
//        for (int i = 0; i < applicationContexts.length; i++) {
//            ConfigurableApplicationContext context;
//            if (i == 0) {
//                context = new ClassPathXmlApplicationContext(contexts[i]);
//            } else {
//                context = new ClassPathXmlApplicationContext(new String[]{contexts[i]}, applicationContexts[i - 1]);
//            }
//            applicationContexts[i] = context;
//        }
//
//        String webContextConfigLocation = getInitParameter("contextConfigLocation");
//        webContext = new ClassPathXmlApplicationContext(new String[]{webContextConfigLocation}, applicationContexts[applicationContexts.length - 1]);
//    }

    @Override
    public void init() throws ServletException {
        String contextConfigLocations = getServletContext().getInitParameter("contextConfigLocation");
        String[] contexts = contextConfigLocations.split(" ");

        applicationContexts = new ConfigurableApplicationContext[contexts.length];

        for (int i = 0; i < applicationContexts.length; i++) {
//            ConfigurableApplicationContext context;
            if (i == 0) {
                applicationContexts[i] = new ClassPathXmlApplicationContext(contexts[i]);
            } else {
                applicationContexts[i] = new ClassPathXmlApplicationContext(new String[]{contexts[i]}, applicationContexts[i - 1]);
            }
        }

        String webContextConfigLocation = getInitParameter("contextConfigLocation");
//        webContext = new ClassPathXmlApplicationContext(new String[]{webContextConfigLocation});
        webContext = new ClassPathXmlApplicationContext(new String[]{webContextConfigLocation}, applicationContexts[applicationContexts.length - 1]);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = request.getRequestURI();
        String controllerName = getControllerName(url);

//        try (PrintWriter writer = response.getWriter()) {
//            writer.println("Hello");
//        }

        MyController controller = webContext.getBean(controllerName, MyController.class); //new HelloController(); //getController(controllerName);
        if (controller != null) {
            controller.handleRequest(request, response);
        }

    }

    private String getControllerName(String url) {
        return url.substring(url.lastIndexOf("/"));
    }

    @Override
    public void destroy() {
        webContext.close();
        for (int i = applicationContexts.length -1; i >=0 ; i++) {
            applicationContexts[i].close();
        }
    }
}
