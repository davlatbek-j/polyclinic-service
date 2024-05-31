package uz.imed.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.imed.model.ApiResponse;
import uz.imed.model.PolyclinicDto;
import uz.imed.service.PolyclinicService;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/polyclinic")
public class PolyclinicController
{
    private final PolyclinicService polyclinicService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<PolyclinicDto>> create(@RequestBody PolyclinicDto polyclinicDto)
    {
        return polyclinicService.create(polyclinicDto);
    }

    @PostMapping("/add-patient-phone")
    public ResponseEntity<ApiResponse<Integer>> addPatient(@RequestParam Integer polyclinicNumber ,@RequestParam String patientPhone)
    {
        return polyclinicService.addPatient(polyclinicNumber,patientPhone);
    }

    @PostMapping("/add-doctor")
    public ResponseEntity<?> addDoctor(@RequestParam Integer polyclinicNumber, @RequestParam Long doctorId)
    {
        return polyclinicService.addDoctor(polyclinicNumber,doctorId);
    }

    @GetMapping("/check-patient-phone-exist")
    public ResponseEntity<ApiResponse<Integer>> checkExistByPhone(@RequestParam String phone)
    {
        return polyclinicService.checkExistByPhone(phone);
    }

}
