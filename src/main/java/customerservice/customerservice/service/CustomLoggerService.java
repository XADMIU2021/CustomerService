package customerservice.customerservice.service;

import customerservice.customerservice.integration.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomLoggerService {
    @Autowired
    private KafkaSender sender;
    public void log(String message) {
        sender.logToKafka(message);
    }
}
