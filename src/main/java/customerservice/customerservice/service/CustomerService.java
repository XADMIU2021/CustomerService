package customerservice.customerservice.service;

import customerservice.customerservice.data.CustomerRepository;
import customerservice.customerservice.domain.Customer;
import customerservice.customerservice.service.dtos.CustomerDTO;
import customerservice.customerservice.service.dtos.CustomersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerAdapterService adapterService;

    @Autowired
    private CustomerRepository repository;

    public CustomerDTO save(CustomerDTO dto) {
        Customer customer = adapterService.getCustomerFromDTO(dto);
        repository.save(customer);
        dto.setId(customer.getId());
        return dto;
    }

    public CustomerDTO findById(String id) {
        Customer customer = repository.findById(id).orElse(null);
        return adapterService.getDTOFromCustomer(customer);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public CustomersDTO findAll() {
        List<Customer> customers = repository.findAll();
        return adapterService.getDTOFromCustomers(customers);
    }
}
