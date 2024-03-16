package oipaas.oipaas.models.resources;


import jakarta.persistence.*;

import java.util.List;

/**
 * All elements that are shown by the resource browser must implement the resource class.
* */
@Entity(name = "collection")
public class CollectionResource extends Resource {
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Resource> collection;

    public CollectionResource(){
        super();
    }

    public CollectionResource(String name, String extension, Resource parent){
        super(name, extension, parent);
    }

    /**
     * Get the collection of resources that the ResourceCollection holds
     * */
    public List<Resource> getCollection(){
        return this.collection;
    }

    public void setCollection(List<Resource> collection){
        this.collection = collection;
    }
}
