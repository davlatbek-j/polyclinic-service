package uz.imed.mapper;

import org.springframework.stereotype.Component;
import uz.imed.entity.Address;
import uz.imed.entity.Polyclinic;
import uz.imed.model.AddressDto;
import uz.imed.model.PolyclinicDto;

@Component
public class AddressMapper
{
    public Address toEntity(AddressDto dto)
    {
        Address address = new Address();
        address.setDistrict(dto.getDistrict());
        address.setStreet(dto.getStreet());
        return address;
    }

    public AddressDto toDto(Address entity)
    {
        AddressDto dto = new AddressDto();
        dto.setDistrict(entity.getDistrict());
        dto.setStreet(entity.getStreet());
        return dto;
    }
}
