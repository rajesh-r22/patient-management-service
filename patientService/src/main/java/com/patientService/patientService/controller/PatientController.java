package com.patientService.patientService.controller;

import com.patientService.patientService.dto.PatientRequestDto;
import com.patientService.patientService.dto.PatientResponseDto;
import com.patientService.patientService.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientResponseDto>> getPatients() {
        List<PatientResponseDto> patients = patientService.getPatients();
        return ResponseEntity.ok().body(patients);
    }
    
//Frontend JSON → Request DTO → Entity → Database → Entity → Response DTO → Frontend JSON
    @PostMapping
    public ResponseEntity<PatientResponseDto> createPatient(@Valid @RequestBody PatientRequestDto patientRequestDto)  {
        PatientResponseDto patientResponseDto=patientService.createPatient(patientRequestDto);
        return ResponseEntity.ok().body(patientResponseDto);
    }
}
