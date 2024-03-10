package OIPaaS.OIPaaS.Models.Resources;


import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * All elements that are shown by the resource browser must implement the resource class.
* */
@Entity
public class Resource<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String extension;
    private T data;

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
     * Encapsulation of data specific to the extension
     * */
    public T getData(){
        return this.data;
    }
    public void setData(T data){
        this.data = data;
    }
}
