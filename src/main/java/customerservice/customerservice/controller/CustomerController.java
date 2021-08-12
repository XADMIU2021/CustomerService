package customerservice.customerservice.controller;

import customerservice.customerservice.service.CustomLoggerService;
import customerservice.customerservice.service.CustomerService;
import customerservice.customerservice.service.dtos.CustomerDTO;
import customerservice.customerservice.service.dtos.CustomersDTO;
import org.bouncycastle.cert.ocsp.RespID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomLoggerService loggerService;

    @GetMapping("/customer")
    public ResponseEntity<CustomersDTO> getAllCustomers() {
        CustomersDTO dto = customerService.findAll();
        loggerService.log("customer fetched");
        return new ResponseEntity<CustomersDTO>(dto, HttpStatus.OK);
    }

    @PostMapping("/customer")
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO dto) {
        customerService.save(dto);
        loggerService.log("customer created, FirstName: " + dto.getFirstName());
        return new ResponseEntity<CustomerDTO>(dto, HttpStatus.CREATED);
    }

    @PostMapping("/customer/{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDTO dto, @PathVariable String id) {
        CustomerDTO customer = customerService.findById(id);
        if (customer == null) return new ResponseEntity<String>("Customer not found", HttpStatus.NOT_FOUND);

        dto.setId(customer.getId());
        customerService.save(dto);
        return new ResponseEntity<CustomerDTO>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable String id) {
        CustomerDTO dto = customerService.findById(id);
        if (dto == null) return new ResponseEntity<String>("Customer not found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<CustomerDTO>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String id) {
        CustomerDTO customer = customerService.findById(id);
        if (customer == null) return new ResponseEntity<String>("Customer not found", HttpStatus.NOT_FOUND);

        customerService.deleteById(id);
        return new ResponseEntity<String>("Customer deleted successfully", HttpStatus.OK);
    }
}
