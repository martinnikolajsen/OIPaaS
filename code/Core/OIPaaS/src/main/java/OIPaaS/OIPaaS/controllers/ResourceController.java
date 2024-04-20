package oipaas.oipaas.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import oipaas.oipaas.models.resources.ResourceAbstract;
import oipaas.oipaas.models.resources.ResourceCollection;
import oipaas.oipaas.models.resources.ResourceFlow;
import oipaas.oipaas.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/resources")
public class ResourceController {
    ResourceService resourceService;
    @Autowired
    public ResourceController(ResourceService resourceService){
        this.resourceService = resourceService;
    }
    @GetMapping("{resourceId}")
    @Operation(summary = "Get the resource corresponding the id given or 0 to get root ResourceCollection")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource found",
                    content = {
                        @Content(schema = @Schema(implementation = ResourceFlow.class)),
                        @Content(schema = @Schema(implementation = ResourceCollection.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Resource not found")
    })
    public ResponseEntity<? extends ResourceAbstract> getResource(@PathVariable
                                                                  @Parameter(description = "ID of the resource to fetch", required = false,
                                                                            schema = @Schema(type = "integer", format = "int32"))
                                                                  int resourceId){
        ResourceAbstract resource = this.resourceService.get(resourceId);
        if(resource == null){
            System.out.println("Throwing exception since resource not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource "+resourceId+" not found");
        }
        return ResponseEntity.ok(resource);
    }
}
