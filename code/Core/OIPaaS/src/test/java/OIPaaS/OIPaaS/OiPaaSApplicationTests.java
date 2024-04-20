package oipaas.oipaas;


import com.fasterxml.jackson.databind.JsonNode;
import oipaas.oipaas.models.resources.ResourceAbstract;
import oipaas.oipaas.models.resources.ResourceCollection;
import oipaas.oipaas.models.resources.ResourceFlow;
import oipaas.oipaas.repositories.ResourceRepositoryAbstract;
import oipaas.oipaas.services.ResourceService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
class OiPaaSApplicationTests {
    ResourceService resourceService;
    ResourceRepositoryAbstract resourceRepositoryAbstract;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    public OiPaaSApplicationTests(ResourceService resourceService, ResourceRepositoryAbstract resourceRepositoryAbstract){
        this.resourceService = resourceService;
        this.resourceRepositoryAbstract = resourceRepositoryAbstract;
    }

    @Test
    public void CollectionResourceCreationRetrival(){
        resourceRepositoryAbstract.deleteAll();

        ResourceCollection resourceCollection = new ResourceCollection("folder", null);
        ResourceAbstract res = resourceService.save(resourceCollection);

        System.out.println("retrived ResourceAbstract before db: " + (resourceCollection.getResourceType()));
        ResourceAbstract flow = resourceService.save(new ResourceFlow("flow1", resourceCollection, "test1"));
        resourceService.save(new ResourceFlow("flow2", resourceCollection, "test2"));
        ResourceCollection rescsub = new ResourceCollection("subfolder", resourceCollection);
        resourceService.save(rescsub);
        resourceService.save(new ResourceFlow("sub-flow2", rescsub, "test2"));
        resourceService.save(new ResourceCollection("sub-sub-flow2", rescsub));
        System.out.println("retrived ResourceAbstract after db save: " + (res.getResourceType()) + res);

        ResourceAbstract ra = resourceService.get(res.getId());
        System.out.println("retrived ResourceAbstract from db: " + (ra.getResourceType()));

        ResourceCollection r = (ResourceCollection)resourceService.get(res.getId());
        //Make sure that data has been saved
        assert r != null : "The resource was not found in the database, while it has just been saved to the database";
        System.out.println("retrived from db: " + (r.getResourceType()));

        //CollectionResource collectionResource1 = r.getResourceType();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> rest = restTemplate.getForEntity("http://localhost:"+serverPort+"/resources/" + res.getId(), JsonNode.class);
        System.out.println("Result dir in root: " + rest.getBody().toPrettyString());

        rest = restTemplate.getForEntity("http://localhost:"+serverPort+"/resources/" + flow.getId(), JsonNode.class);
        System.out.println("Result flow resource: " + rest.getBody().toPrettyString());

        rest = restTemplate.getForEntity("http://localhost:"+serverPort+"/resources/" + rescsub.getId(), JsonNode.class);
        System.out.println("Result dir subfolder: " + rest.getBody().toPrettyString());

        res.setName("testfolder2");
        resourceService.save(res);
    }

}
