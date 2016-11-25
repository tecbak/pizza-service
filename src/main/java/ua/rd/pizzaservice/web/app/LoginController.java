package ua.rd.pizzaservice.web.app;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Serhii_Mykhliuk
 */
@Controller
public class LoginController {

    @RequestMapping("/loginp")
    public String login() {
        return "login";
    }
}
