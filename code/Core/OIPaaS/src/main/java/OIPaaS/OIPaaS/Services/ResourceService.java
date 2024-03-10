package OIPaaS.OIPaaS.Services;

import OIPaaS.OIPaaS.Models.Resources.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {
    JpaRepository<Resource, Integer> repository;
    @Autowired
    public ResourceService(JpaRepository<Resource, Integer> repository){
        this.repository = repository;
    }
    public Resource getResource(int resourceId){
        return repository.getOne(resourceId);
    }
}
