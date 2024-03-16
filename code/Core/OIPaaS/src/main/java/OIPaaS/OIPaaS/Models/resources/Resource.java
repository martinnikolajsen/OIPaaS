package oipaas.oipaas.models.resources;

import jakarta.persistence.*;
import jakarta.validation.constraints.Null;

@Entity
public abstract class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    private String extension;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @Null
    private Resource parent;

    public Resource(){

    }

    public Resource(String name, String extension, Resource parent){
        this.name = name;
        this.extension = extension;
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
     * The extension tell how the resource should be opened.
     * If the extension is unknown the data will be queued for download to the client.
     * If no extension is set, the Resource is a ResourceCollection
     * */
    public String getExtension(){
        return this.extension;
    }
    public void setExtension(String extension){
        this.extension = extension;
    }

    /**
     * Every Resource has a parent, if the Parent is null the Resource is in the root Collection
     * */
    public Resource getParent() {
        return this.parent;
    }

    public void setParent(Resource parent) {
        this.parent = parent;
    }

}
