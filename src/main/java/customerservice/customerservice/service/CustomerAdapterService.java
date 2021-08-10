package customerservice.customerservice.service;

import customerservice.customerservice.domain.Address;
import customerservice.customerservice.domain.Customer;
import customerservice.customerservice.service.dtos.AddressDTO;
import customerservice.customerservice.service.dtos.CustomerDTO;
import customerservice.customerservice.service.dtos.CustomersDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerAdapterService {
    public CustomerDTO getDTOFromCustomer(Customer customer) {
        CustomerDTO dto = new CustomerDTO(customer.getId(),customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getPhoneNumber());
        Address address = customer.getAddress();
        AddressDTO addressDTO = new AddressDTO(address.getStreet(), address.getCity(), address.getZip());
        dto.setAddress(addressDTO);
        return dto;
    }

    public Customer getCustomerFromDTO(CustomerDTO dto) {
        Customer customer = new Customer(dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getPhoneNumber());
        AddressDTO addressDTO = dto.getAddress();
        Address address = new Address(addressDTO.getStreet(), addressDTO.getCity(), addressDTO.getZip());
        customer.setAddress(address);
        if (dto.getId() != null) customer.setId(dto.getId());
        return customer;
    }

    public CustomersDTO getDTOFromCustomers(List<Customer> customers) {
        List<CustomerDTO> customerDTOS = new ArrayList<CustomerDTO>();
        for (Customer c: customers) {
            customerDTOS.add(this.getDTOFromCustomer(c));
        }
        CustomersDTO dto = new CustomersDTO();
        dto.setCustomers(customerDTOS);
        return dto;
    }
}
