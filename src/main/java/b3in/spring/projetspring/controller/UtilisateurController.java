package b3in.spring.projetspring.controller;

import b3in.spring.projetspring.entity.Utilisateur;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import b3in.spring.projetspring.service.UtilisateurService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, RedirectAttributes redirectAttributes) {
        Utilisateur user = utilisateurService.authenticateUser(email, password);
        if (user != null) {
            session.setAttribute("user", user);
            redirectAttributes.addFlashAttribute("successMessage", "Connexion réussie.");
            return "redirect:/mainpage";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Email ou mot de passe incorrect.");
            return "redirect:/connexion";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            SecurityContextHolder.clearContext();
        }
        session.invalidate();
        redirectAttributes.addFlashAttribute("successMessage", "Déconnexion réussie.");
        return "redirect:/connexion";
    }



    @RequestMapping(value = "infoUser", method = RequestMethod.GET)
    @ResponseBody
    public String infoUser() {
        return "L'appli tourne...";
    }

//    @RequestMapping(value = "createUser", method = RequestMethod.POST)
//    public String createUser(@RequestBody User user) {
//        return userService.createUser(user);
//    }


    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String createUser(@ModelAttribute Utilisateur user, RedirectAttributes redirectAttributes) {
        System.out.println("createUser appelé!!!!");
        String result = utilisateurService.createUser(user);
        if ("Utilisateur cree avec succes".equals(result)) {
            redirectAttributes.addFlashAttribute("successMessage", "Inscription réussie, veuillez vous connecter.");
            return "redirect:/connexion";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", result);
            return "redirect:/inscription";
        }
    }

    @RequestMapping(value = "getAllUsers", method = RequestMethod.GET)
    @ResponseBody
    public List<Utilisateur> getAllUsers() {
        return utilisateurService.getAllUsers();
    }

    @RequestMapping(value = "updateUser", method = RequestMethod.PUT)
    @ResponseBody
    public String updateUser(@RequestBody Utilisateur user) {
        return utilisateurService.updateUser(user);
    }

    @RequestMapping(value = "deleteUser", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteUser(@RequestBody Utilisateur user) {
        return utilisateurService.deleteUser(user);
    }


}