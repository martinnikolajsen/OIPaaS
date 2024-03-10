package OIPaaS.OIPaaS.Controllers;

import OIPaaS.OIPaaS.Models.Resources.Resource;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/resources")
public class Resources {
    @GetMapping("resource/{id}")
    @Operation(summary = "Get the resource corresponding the the id given")
    public ResponseEntity<Resource> getResource(@PathVariable int id){
        //We load and return the resource
        return ResponseEntity.ok(null);
    }
}
