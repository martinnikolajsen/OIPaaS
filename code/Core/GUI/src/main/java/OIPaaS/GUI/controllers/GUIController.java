package oipaas.gui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GUIController {
    @GetMapping({"/"})
    public String getHtmlPage() {
        return "index.html";
    }
}
