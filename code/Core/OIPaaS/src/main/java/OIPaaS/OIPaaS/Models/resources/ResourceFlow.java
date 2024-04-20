package oipaas.oipaas.models.resources;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
@DiscriminatorValue("FlowResource")
public class ResourceFlow extends ResourceAbstract {

    private String settings;

    public String getSettings() {
        return settings;
    }

    public void setSettings(String settings) {
        this.settings = settings;
    }

    public ResourceFlow(){
        super();
    }

    public ResourceFlow(String name){
        this(name, null);
    }
    public ResourceFlow(String name, ResourceAbstract parent){
        super(name, parent);
    }

    public ResourceFlow(String name, ResourceAbstract parent, String settings){
        super(name, parent);
        this.settings = settings;
    }

    //Settings settings;

    @Transient
    public String getResourceType(){
        DiscriminatorValue discriminatorValue = this.getClass().getAnnotation(DiscriminatorValue.class);
        return (discriminatorValue != null) ? discriminatorValue.value() : null;
    }

}
