package b3in.spring.projetspring.controller;
import b3in.spring.projetspring.entity.User;
import b3in.spring.projetspring.controller.UserController;
import b3in.spring.projetspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller

public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {return "index";}

    @GetMapping("/login")
   public String login() {return "login";}
}
