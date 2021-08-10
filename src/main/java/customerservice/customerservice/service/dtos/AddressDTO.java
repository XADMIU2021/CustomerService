package customerservice.customerservice.service.dtos;

public class AddressDTO {
    private String Street;
    private String city;
    private String zip;

    public AddressDTO(String street, String city, String zip) {
        Street = street;
        this.city = city;
        this.zip = zip;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
