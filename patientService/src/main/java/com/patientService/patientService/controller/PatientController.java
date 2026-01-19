package com.patientService.patientService.controller;

import com.patientService.patientService.dto.PatientResponseDto;
import com.patientService.patientService.model.Patient;
import com.patientService.patientService.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientResponseDto>> getPatients(){
         List<PatientResponseDto> patients=patientService.getPatients();
         return ResponseEntity.ok().body(patients);
    }
}
