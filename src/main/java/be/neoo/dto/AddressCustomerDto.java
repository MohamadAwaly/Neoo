package be.neoo.dto;

import be.neoo.enums.AddressTypeEnum;


public class AddressCustomerDto {
    private CustomerDto customer;

    private AddressDto address;

    private AddressTypeEnum addressType;

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public AddressTypeEnum getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressTypeEnum addressType) {
        this.addressType = addressType;
    }
}
