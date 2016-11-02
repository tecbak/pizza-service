package ua.rd.pizzaservice.web.infrastructure;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.servlet.http.HttpServletRequest;

public class SimpleUrlHandlerMapping implements HandlerMapping, ApplicationContextAware {
    private org.springframework.context.ApplicationContext webContext;

//    public SimpleUrlHandlerMapping(ApplicationContext webContext) {
//        this.webContext = webContext;
//    }

    @Override
    public MyController getController(HttpServletRequest request) {
        String url = request.getRequestURI();
        String controllerName = getControllerName(url);
        return webContext.getBean(controllerName, MyController.class);

    }

    private String getControllerName(String url) {
        return url.substring(url.lastIndexOf("/"));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        webContext = applicationContext;
    }
}
