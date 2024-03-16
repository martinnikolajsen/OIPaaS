package oipaas.oipaas.controllers;


import io.swagger.v3.oas.annotations.Operation;
import oipaas.oipaas.models.resources.Resource;
import oipaas.oipaas.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/resources")
public class ResourceController {
    ResourceService resourceService;
    @Autowired
    public ResourceController(ResourceService resourceService){
        this.resourceService = resourceService;
    }
    @GetMapping("resource/{id}")
    @Operation(summary = "Get the resource corresponding the the id given")
    public ResponseEntity<? extends Resource> getResource(@PathVariable int id){
        return ResponseEntity.ok(this.resourceService.get(id));
    }
}
