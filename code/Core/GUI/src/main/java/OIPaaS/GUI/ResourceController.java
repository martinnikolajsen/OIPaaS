package OIPaaS.GUI;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
public class ResourceController {

    private RestTemplate restTemplate;
    @Autowired
    public ResourceController(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @GetMapping({"/resource/{resourceId}", "/resource/"})
    public ResponseEntity<JsonNode> getResourceById(@PathVariable(required = false) Integer resourceId) {
        if(resourceId == null){
            resourceId = 0;
        }
        String serviceUrl = "http://oipaas/resources/" + resourceId;
        try {
            return restTemplate.exchange(serviceUrl, HttpMethod.GET, null, JsonNode.class);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getRawStatusCode()).body(e.getResponseBodyAs(JsonNode.class));
        }
    }

}
