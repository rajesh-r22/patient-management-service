package com.patientService.patientService.service;

import com.patientService.patientService.dto.PatientRequestDto;
import com.patientService.patientService.dto.PatientResponseDto;
import com.patientService.patientService.exception.EmailAlreadyExistException;
import com.patientService.patientService.exception.PatientNotFoundException;
import com.patientService.patientService.mapper.PatientMapper;
import com.patientService.patientService.model.Patient;
import com.patientService.patientService.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
    public PatientResponseDto createPatient(PatientRequestDto patientRequestDto)   {
        if(patientRepo.existsByEmail(patientRequestDto.getEmail())){
            throw new EmailAlreadyExistException("Email already exists"+patientRequestDto.getEmail());
        }
        Patient newPatient=patientRepo.save(PatientMapper.toModel(patientRequestDto));
        return PatientMapper.toDto(newPatient);
    }

    public PatientResponseDto updatePatient(UUID id, PatientRequestDto patientRequestDto){
        // Find existing patient
        Patient patient = patientRepo.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found " + id));

        //   Check if email already exists
        if(patientRepo.existsByEmail(patientRequestDto.getEmail())){
            throw new EmailAlreadyExistException("Email already exists"+patientRequestDto.getEmail());
        }

        // Update patient entity with new values from DTO
        patient.setName(patientRequestDto.getName());
        patient.setEmail(patientRequestDto.getEmail());
        patient.setAddress(patientRequestDto.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));

        // Save updated patient
        Patient updatePatient=patientRepo.save(patient);

        // Convert to DTO and return\
        return PatientMapper.toDto(updatePatient);
    }
}
