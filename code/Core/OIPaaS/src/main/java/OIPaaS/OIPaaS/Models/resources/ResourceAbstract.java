package oipaas.oipaas.models.resources;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "resource_type", discriminatorType = DiscriminatorType.STRING)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public abstract class ResourceAbstract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @Null
    @JsonIdentityReference(alwaysAsId = true)
    private ResourceAbstract parent;

    public ResourceAbstract(){

    }

    public ResourceAbstract(String name, ResourceAbstract parent){
        this.name = name;
        this.parent = parent;
    }

    /**
     * Friendly name for the resource
     * */
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    /**
     * This is the DB ID and identifier for the resource
     * */
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }

    /**
     * Every Resource has a parent, if the Parent is null the Resource is in the root Collection
     * */
    public ResourceAbstract getParent() {
        return this.parent;
    }

    public void setParent(ResourceAbstract parent) {
        this.parent = parent;
    }


    /**
     * The DiscriminatorColumn should have the same name as the Class used
     * */
    @Transient
    public String getResourceType(){
        DiscriminatorValue discriminatorValue = this.getClass().getAnnotation(DiscriminatorValue.class);
        return (discriminatorValue != null) ? discriminatorValue.value() : null;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent=" + parent +
                '}';
    }
}
