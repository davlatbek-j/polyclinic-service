package uz.imed.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.imed.entity.Polyclinic;
import uz.imed.model.PolyclinicDto;

@RequiredArgsConstructor

@Component
public class PolyclinicMapper
{
    private final AddressMapper addressMapper;

    public Polyclinic toEntity(PolyclinicDto dto)
    {
        Polyclinic polyclinic = new Polyclinic();
        polyclinic.setId(dto.getId());
        polyclinic.setName(dto.getName());
        polyclinic.setNumber(dto.getNumber());
        polyclinic.setAddress(addressMapper.toEntity(dto.getAddress()));
        return polyclinic;
    }

    public PolyclinicDto toDto(Polyclinic entity)
    {
        PolyclinicDto dto = new PolyclinicDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setNumber(entity.getNumber());
        dto.setAddress(addressMapper.toDto(entity.getAddress()));
        return dto;
    }

}
