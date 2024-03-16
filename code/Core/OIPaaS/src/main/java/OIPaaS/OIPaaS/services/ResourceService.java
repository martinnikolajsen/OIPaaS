package oipaas.oipaas.services;

import oipaas.oipaas.models.resources.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResourceService<T extends Resource> {
    JpaRepository<T, Integer> resourceReposity;
    @Autowired
    public ResourceService(JpaRepository<T, Integer> repository){
        this.resourceReposity = repository;
    }
    public T get(int resourceId){
        T ret = null;
        Optional<T> optionalResource = this.resourceReposity.findById(resourceId);
        if (optionalResource.isPresent()) {
            ret = (T) optionalResource.get();
        }
        return null;
    }

    public T save(T resource){
        return this.resourceReposity.save(resource);
    }
}
