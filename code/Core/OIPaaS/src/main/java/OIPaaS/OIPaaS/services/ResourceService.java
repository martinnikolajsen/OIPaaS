package oipaas.oipaas.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import oipaas.oipaas.models.resources.ResourceCollection;
import oipaas.oipaas.models.resources.ResourceFlow;
import oipaas.oipaas.models.resources.ResourceAbstract;
import oipaas.oipaas.repositories.ResourceCollectionRepository;
import oipaas.oipaas.repositories.ResourceFlowRepository;
import oipaas.oipaas.repositories.ResourceRepositoryAbstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {
    ResourceRepositoryAbstract resourceRepositoryAbstract;
    ResourceCollectionRepository resourceCollectionRepository;
    ResourceFlowRepository resourceFlowRepository;
    @Autowired
    public ResourceService(ResourceRepositoryAbstract resourceRepositoryAbstract, ResourceCollectionRepository resourceCollectionRepository, ResourceFlowRepository resourceFlowRepository){
        this.resourceRepositoryAbstract = resourceRepositoryAbstract;
        this.resourceCollectionRepository = resourceCollectionRepository;
        this.resourceFlowRepository = resourceFlowRepository;
    }

    public ResourceCollection getRoot(){
        ResourceCollection ret = null;
        List<ResourceAbstract> elements = this.resourceRepositoryAbstract.findByParentIsNull();
        ret = new ResourceCollection("Root", null);

        if(!elements.isEmpty()){
            ret.getCollection().addAll(elements);
        }
        return ret;
    }

    public ResourceAbstract get(int resourceId){
        if(resourceId == 0){
            return getRoot();
        }
        ResourceAbstract ret = null;
        Optional<ResourceAbstract> optionalResource = this.resourceRepositoryAbstract.findById(resourceId);
        if (optionalResource.isPresent()) {
            ret = optionalResource.get();
        }
        return ret;
    }

    public ResourceCollection save(ResourceCollection resource){
        return this.resourceCollectionRepository.save(resource);
    }

    public ResourceFlow save(ResourceFlow resource){
        return this.resourceFlowRepository.save(resource);
    }

    public ResourceAbstract save(ResourceAbstract resourceAbstract){
        ResourceAbstract ret = null;
        ret = save(resourceAbstract);
        return ret;
    }
    public JsonNode convert(ResourceAbstract resource){
        JsonNode ret = null;
        ObjectMapper objectMapper = new ObjectMapper();
        //Since the Object can be multiple forms, we need to get the right type
        if(resource instanceof ResourceCollection || resource instanceof  ResourceFlow){
            ret = objectMapper.valueToTree(resource);
        }

        return ret;
    }

    public JsonNode geJsont(int resourceId){
        ResourceAbstract resourceAbstract = get(resourceId);
        return convert(resourceAbstract);
    }
}
