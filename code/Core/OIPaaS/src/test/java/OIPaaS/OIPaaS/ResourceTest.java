package oipaas.oipaas;

import jakarta.transaction.Transactional;
import oipaas.oipaas.models.resources.CollectionResource;
import oipaas.oipaas.models.resources.Resource;
import oipaas.oipaas.repositories.ResourceRepository;
import oipaas.oipaas.services.ResourceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class ResourceTest {

    ResourceService resourceService;
    ResourceRepository resourceRepository;

    @Autowired
    public ResourceTest(ResourceService resourceService, ResourceRepository resourceRepository){
        this.resourceService = resourceService;
        this.resourceRepository = resourceRepository;
    }

    @Test
    public void CollectionResourceCreationRetrival(){
        resourceRepository.deleteAll();

        CollectionResource collectionResource = new CollectionResource("folder", null, null);
        Resource res = resourceService.save(collectionResource);
        CollectionResource collectionResourceReturned = resourceService.get(res.getId());
        System.out.println("testCreation");
    }
}
