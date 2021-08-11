package customerservice.customerservice.integration;

import org.springframework.stereotype.Service;

@Service
public class CustomerEmailService {
    public void sendEmailToCustomer(String email, String message) {
        System.out.println("sending email to " + email + " message: " + message);
    }
}
