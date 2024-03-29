package me.whiteship.demospringsecurityform.form;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SampleController {
    
    @GetMapping(value="/")
    public String index(Model model, Principal principal) {
        if(principal == null){
            model.addAttribute("message", "Hello Spring Security!!");
        }else{
            model.addAttribute("message", "Hello" + principal.getName());
        }
        return "index";
    }
    @GetMapping(value="/info")
    public String info(Model model) {
        model.addAttribute("message", "Hello Info");
        return "info";
    }
    @GetMapping(value="/dashboard")
    public String dashBoard(Model model, Principal principal) {
        model.addAttribute("message", "Hello" + principal.getName());
        return "dashboard";
    }
    @GetMapping(value="/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("message", "Hello Admin" + principal.getName());
        return "admin";
    }
    
}
