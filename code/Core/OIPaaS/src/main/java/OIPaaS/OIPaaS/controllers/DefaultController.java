package oipaas.oipaas.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {
    @GetMapping("test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("It works!");
    }
}
