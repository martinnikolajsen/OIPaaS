package oipaas.oipaas;

import jakarta.transaction.Transactional;
import oipaas.oipaas.models.resources.ResourceCollection;
import oipaas.oipaas.models.resources.ResourceFlow;
import oipaas.oipaas.models.resources.ResourceAbstract;
import oipaas.oipaas.repositories.ResourceRepositoryAbstract;
import oipaas.oipaas.services.ResourceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class ResourceAbstractTest {
/*
    ResourceService resourceService;
    ResourceRepositoryAbstract resourceRepositoryAbstract;

    @Autowired
    public ResourceAbstractTest(ResourceService resourceService, ResourceRepositoryAbstract resourceRepositoryAbstract){
        this.resourceService = resourceService;
        this.resourceRepositoryAbstract = resourceRepositoryAbstract;
    }

    @Test
    public void CollectionResourceCreationRetrival(){
        resourceRepositoryAbstract.deleteAll();

        ResourceCollection resourceCollection = new ResourceCollection("folder", null);
        resourceCollection.add(new ResourceFlow("flow1", resourceCollection));
        resourceCollection.add(new ResourceFlow("flow2", resourceCollection));
        resourceCollection.add(new ResourceFlow("flow3", resourceCollection));

        ResourceAbstract res = resourceService.save(resourceCollection);
        System.out.println("res = " + res);
        ResourceAbstract r = resourceService.get(res.getId());
        //Make sure that data has been saved
        assert r != null : "The resource was not found in the database, while it has just been saved to the database";
        System.out.println("retrived from db: " + r.getResourceType().getClass().getName());
        //CollectionResource collectionResource1 = r.getResourceType();
    }

 */
}
