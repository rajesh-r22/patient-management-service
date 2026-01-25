package com.patientService.patientService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequestDto {
    @NotNull(message = "Name cannot not be blank")
    @Size(max = 100,message ="Name must be  under 100 characters" )
    private String name;

    @NotNull(message = "email should be valid")
    @Email
    @Size(min =10,message = "email should contain Minimum 10 characters")
    private String email;

    @NotNull(message = "Address cannot be blank ")
    private String address;

    @NotNull(message = "Date-Of-Birth cannot be null")
    private String dateOfBirth;

    @NotNull(message = "Registered-Date should be valid")
    private String registeredDate;
}

//- Request DTO → frontend se aane wale JSON ko capture karta hai.
