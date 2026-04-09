package backend.cinema.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller

public class CinemaController {

    @GetMapping("/")
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/cinema/{city}")
    public String getCity(@PathVariable String city, Model model) {
        model.addAttribute("city", city);
        return "cinema";
    }

}
