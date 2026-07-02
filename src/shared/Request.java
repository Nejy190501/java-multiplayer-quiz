package shared;

import java.io.Serializable;

public class Request implements Serializable {
    private final String type;
    private final String payload;

    public Request(String type, String payload) {
        this.type = type;
        this.payload = payload;
    }
    public String getType()    { return type; }
    public String getPayload() { return payload; }
}