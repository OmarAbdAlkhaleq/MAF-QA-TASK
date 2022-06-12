package resources;

public enum Endpoints {

    Search("/search");


    private String resource;

    Endpoints(String resource)
    {
        this.resource=resource;
    }

    public String getResource()
    {
        return resource;
    }

}
