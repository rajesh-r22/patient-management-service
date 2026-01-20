package com.patientService.patientService.service;

import com.patientService.patientService.dto.PatientRequestDto;
import com.patientService.patientService.dto.PatientResponseDto;
import com.patientService.patientService.mapper.PatientMapper;
import com.patientService.patientService.model.Patient;
import com.patientService.patientService.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepo;

    public  List<PatientResponseDto> getPatients(){
        List<Patient> patients=patientRepo.findAll();
//      Best practice to return dto not full entity
        return patients.stream().map(PatientMapper::toDto).toList();

//        List<PatientResponseDto> patientResponseDtos= patients.stream()
//                .map(patient -> PatientMapper.toDto(patient)).toList();
//        return patientResponseDtos;
    }

//   createPatient() → Request DTO ko entity mein convert karta hai → DB mein save karta hai → saved entity ko DTO mein convert karke return karta hai.
    public PatientResponseDto createPatient(PatientRequestDto patientRequestDto){
        Patient newPatient=patientRepo.save(PatientMapper.toModel(patientRequestDto));
        return PatientMapper.toDto(newPatient);
    }
}
