package OIPaaS.GUI;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controllers {
    @GetMapping({"/"})
    public String getHtmlPage() {
        return "index.html";
    }
}
