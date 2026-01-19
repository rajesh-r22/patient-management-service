package com.patientService.patientService.mapper;

import com.patientService.patientService.dto.PatientResponseDto;
import com.patientService.patientService.model.Patient;

public class PatientMapper {

    public static PatientResponseDto toDto(Patient patient){
        PatientResponseDto responseDto=new PatientResponseDto();
        responseDto.setId(patient.getId().toString());
        responseDto.setName(patient.getName());
        responseDto.setEmail(patient.getEmail());
        responseDto.setAddress(patient.getAddress());
        responseDto.setDateOfBirth(patient.getDateOfBirth().toString());
        return responseDto;
    }
}
