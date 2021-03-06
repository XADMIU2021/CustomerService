package customerservice.customerservice.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import customerservice.customerservice.domain.OrderPlacedEvent;
import customerservice.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaReceiver {
    @Autowired
    private CustomerService service;

    @KafkaListener(topics = {"order-placed"})
    public void receiveAdd(@Payload String message) {
        System.out.println("Receiver received order placed message = " + message);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            OrderPlacedEvent event = objectMapper.readValue(message, OrderPlacedEvent.class);
            service.handleOrderPlaced(event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
