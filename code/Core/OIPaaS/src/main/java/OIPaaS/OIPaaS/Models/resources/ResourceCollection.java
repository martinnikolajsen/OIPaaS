package oipaas.oipaas.models.resources;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * All elements that are shown by the resource browser must implement the resource class.
* */
@Entity
@DiscriminatorValue("CollectionResource")
public class ResourceCollection extends ResourceAbstract {
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonSerialize(contentAs = ResourceAbstract.class)
    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
    private List<ResourceAbstract> collection = new ArrayList<>();

    public ResourceCollection(){
        super();
    }


    public ResourceCollection(String name, ResourceAbstract parent){
        super(name, parent);
    }

    /**
     * Get the collection of resources that the ResourceCollection holds
     * */
    public List<ResourceAbstract> getCollection(){
        return this.collection;
    }

    public void setCollection(List<ResourceAbstract> collection){
        this.collection = collection;
    }

    public <T extends ResourceAbstract> void add(T resource){
        this.getCollection().add(resource);
    }

    @Transient
    @Override
    public String getResourceType(){
        DiscriminatorValue discriminatorValue = this.getClass().getAnnotation(DiscriminatorValue.class);
        return (discriminatorValue != null) ? discriminatorValue.value() : null;
    }
}
