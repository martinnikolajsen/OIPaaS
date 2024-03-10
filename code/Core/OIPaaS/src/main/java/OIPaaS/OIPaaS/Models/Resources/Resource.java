package OIPaaS.OIPaaS.Models.Resources;

/**
 * All elements that are shown by the resource browser must implement the resource class.
* */
public interface Resource<T> {

    /**
     * Friendly name for the resource
     * */
    String getName();
    void setName(String name);

    /**
     * This is the DB ID and identifier for the resource
     * */
    int getId();
    void setId(int id);

    /**
     * The extension tell how the resource should be opened.
     * If the extension is unknown the data will be queued for download to the client.
     * If no extension is set, the Resource is a ResourceCollection
     * */
    String getExtension();
    void setExtension(String extension);

    /**
     * Encapsulation of data specific to the extension
     * */
    T getData();
    void setData(T data);
}
