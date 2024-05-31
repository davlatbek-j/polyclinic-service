package uz.imed.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.imed.entity.Polyclinic;
import uz.imed.mapper.PolyclinicMapper;
import uz.imed.model.ApiResponse;
import uz.imed.model.PolyclinicDto;
import uz.imed.repository.PolyclinicRepository;

import java.util.Optional;


@RequiredArgsConstructor

@Service
public class PolyclinicService
{
    private final PolyclinicRepository polyclinicRepo;
    private final PolyclinicMapper polyclinicMapper;

    public ResponseEntity<ApiResponse<PolyclinicDto>> create(PolyclinicDto polyclinicDto)
    {
        Polyclinic entity = polyclinicMapper.toEntity(polyclinicDto);
        Polyclinic saved = polyclinicRepo.save(entity);
        ApiResponse<PolyclinicDto> apiResponse = new ApiResponse<>(polyclinicMapper.toDto(saved), "Created");
        return ResponseEntity.ok().body(apiResponse);
    }


    //Agar phone number qaysidir poliklinikadan ro'yxatdan o'tgan bo'lsa
    //o'sha poliklinika id'si qaytariladi , agar hich qaysi p-ka dan
    //ro'yxtadan o'tmagan bo'lsa null qaytariladi
    public ResponseEntity<ApiResponse<Integer>> checkExistByPhone(String phone)
    {
        Long polyClinicId = polyclinicRepo.existsByPhone(phone);
        if (polyClinicId == null)
        {
            ApiResponse<Integer> phoneNotExist = new ApiResponse<>(null, "Phone not exist");
            return ResponseEntity.ok().body(phoneNotExist);
        }

        Polyclinic byId = polyclinicRepo.findById(polyClinicId).get();

        ApiResponse<Integer> phoneExist = new ApiResponse<>(byId.getNumber(), "Phone already registered");
        return ResponseEntity.ok().body(phoneExist);
    }

    public ResponseEntity<ApiResponse<Integer>> addPatient(Integer polyclinicNumber, String patientPhone)
    {
        try
        {
            Polyclinic fromDb = polyclinicRepo.findByNumber(polyclinicNumber);
            fromDb.getPatientsPhone().add(patientPhone);
            polyclinicRepo.save(fromDb);
            ApiResponse<Integer> added = new ApiResponse<>(fromDb.getNumber(), "Added");
            return ResponseEntity.ok().body(added);
        } catch (RuntimeException e)
        {
            ApiResponse<Integer> apiResponse = new ApiResponse<>(null, "Error adding patient to polyclinic" + e.getMessage());
            return ResponseEntity.ok().body(apiResponse);
        }
    }

    public ResponseEntity<?> addDoctor(Integer polyclinicNumber, Long doctorId)
    {
        Polyclinic fromDb = polyclinicRepo.findByNumber(polyclinicNumber);
        if (fromDb.getDoctorsId().contains(doctorId))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Doctor already exists");

        fromDb.getDoctorsId().add(doctorId);
        polyclinicRepo.save(fromDb);
        return ResponseEntity.ok().build();
    }

}
