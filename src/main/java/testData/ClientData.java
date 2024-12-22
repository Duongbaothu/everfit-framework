package testData;

import lombok.Getter;

@Getter
public class ClientData {
    private String clientName;

    public ClientData(String clientName) {
        this.clientName = clientName;
    }
}
