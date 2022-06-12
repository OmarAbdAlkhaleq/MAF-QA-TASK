package resources;

public enum HttpMethod {

    // To list all available Http Methods in API Request

    POST("POST"),
    GET("GET"),
    PUT("PUT"),
    DELETE("DELETE");

    private String method;
    //Constructor of the enum
    HttpMethod(String method) {

        this.method = method;
    }

    public String getHttpMethod() {

        return this.method;
    }

}
