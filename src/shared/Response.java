package shared;

import java.io.Serializable;

public class Response implements Serializable {
    private final String status;
    private final String payload;

    public Response(String status, String payload) {
        this.status = status;
        this.payload = payload;
    }
    public String getStatus()  { return status; }
    public String getPayload() { return payload; }
}