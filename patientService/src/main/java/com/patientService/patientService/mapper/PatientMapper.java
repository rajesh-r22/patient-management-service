package com.patientService.patientService.mapper;

import com.patientService.patientService.dto.PatientRequestDto;
import com.patientService.patientService.dto.PatientResponseDto;
import com.patientService.patientService.model.Patient;

import java.time.LocalDate;

public class PatientMapper {

//    hum client ko response dete hai responsedto ke form me, naki pura entity/model return kare it is danger, responsedto safe
    public static PatientResponseDto toDto(Patient patient){
        PatientResponseDto responseDto=new PatientResponseDto();
        responseDto.setId(patient.getId().toString());
        responseDto.setName(patient.getName());
        responseDto.setEmail(patient.getEmail());
        responseDto.setAddress(patient.getAddress());
        responseDto.setDateOfBirth(patient.getDateOfBirth().toString());
        return responseDto;
    }

//    jo client data post kiya hai usko Entity/model me convert kar re hai phir Database me save karenge
    public static Patient toModel(PatientRequestDto patientRequestDto){
        Patient patient=new Patient();
        patient.setName(patientRequestDto.getName());
        patient.setEmail(patientRequestDto.getEmail());
        patient.setAddress(patientRequestDto.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(patientRequestDto.getRegisteredDate()));
        return  patient;
    }
}
