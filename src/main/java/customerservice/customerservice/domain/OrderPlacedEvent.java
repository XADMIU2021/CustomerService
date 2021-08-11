package customerservice.customerservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderPlacedEvent {
    private String customerId;

    public OrderPlacedEvent() {
    }

    public OrderPlacedEvent(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }
}
