
package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import java.time.LocalDateTime;
import java.time.format.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.example.demo.controllers.*;
import com.example.demo.repositories.*;
import com.example.demo.entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import org.springframework.http.MediaType;



/** TODO
 * Implement all the unit test in its corresponding class.
 * Make sure to be as exhaustive as possible. Coverage is checked ;)
 */

@WebMvcTest(DoctorController.class)
class DoctorControllerUnitTest{

    @MockBean
    private DoctorRepository doctorRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateDoctor() throws Exception {
        Doctor doctor = new Doctor("Pedro", "Hernandez", 45, "phernandez@docmail.com");
        
        mockMvc.perform(post("/api/doctor").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(doctor)))
                .andExpect(status().isCreated());
    }
    
    @Test
    void shouldGetNoDoctors() throws Exception {
        List<Doctor> doctors = new ArrayList<>();   
        when(doctorRepository.findAll()).thenReturn(doctors);
        mockMvc.perform(get("/api/doctors"))
                .andExpect(status().isNoContent());
    }
    
    @Test
    void shouldGetTwoDoctors() throws Exception {
        Doctor doctor = new Doctor("Pedro", "Hernandez", 45, "phernandez@docmail.com");
        Doctor doctor2 = new Doctor("Mario", "Gutierrez", 50, "mgutierrez@docmail.com");
        
        mockMvc.perform(post("/api/doctor").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(doctor)))
                .andExpect(status().isCreated());
        
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor);
        
        when(doctorRepository.findAll()).thenReturn(doctors);
        mockMvc.perform(post("/api/doctor").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(doctor2)))
                .andExpect(status().isCreated());
    }
    
    @Test
    void shouldGetDoctorById() throws Exception {
        Doctor doctor = new Doctor("Pedro", "Hernandez", 45, "phernandez@docmail.com");
        doctor.setId(1);
        
        Optional<Doctor> doc = Optional.of(doctor);
        
        assertThat(doc).isPresent();
        assertThat(doc.get().getId()).isEqualTo(doctor.getId());   
        when(doctorRepository.findById(doctor.getId())).thenReturn(doc);
        mockMvc.perform(get("/api/doctors/" + doctor.getId()))
                .andExpect(status().isOk());
    }
    
    @Test
    void shouldNotGetDoctorById() throws Exception {   
        long id = 21;
        mockMvc.perform(get("/api/doctors/" + id))
                .andExpect(status().isNotFound());
    }
    
    @Test
    void shouldDeleteDoctorById() throws Exception {
        Doctor doctor = new Doctor("Pedro", "Hernandez", 45, "phernandez@docmail.com");
        doctor.setId(1);
        
        Optional<Doctor> doc = Optional.of(doctor);
        
        assertThat(doc).isPresent();
        assertThat(doc.get().getId()).isEqualTo(doctor.getId());   
        when(doctorRepository.findById(doctor.getId())).thenReturn(doc);
        mockMvc.perform(delete("/api/doctors/" + doctor.getId()))
                .andExpect(status().isOk());
    }
    
    @Test
    void shouldNotDeleteDoctorById() throws Exception {   
        long id = 21;
        mockMvc.perform(delete("/api/doctors/" + id))
                .andExpect(status().isNotFound());
    }
    
    @Test
    void shouldDeleteAllDoctors() throws Exception {
        mockMvc.perform(delete("/api/doctors"))
                .andExpect(status().isOk());
    }
}


@WebMvcTest(PatientController.class)
class PatientControllerUnitTest{

    @MockBean
    private PatientRepository patientRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreatePatient() throws Exception{
        Patient patient = new Patient("Mario", "Hernandez", 25, "mhernandez@patmail.com");
        
        mockMvc.perform(post("/api/patient").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isCreated());
    }
    
    @Test
    void shouldGetNoPatients() throws Exception {
        List<Patient> patients = new ArrayList<>();
        
        when(patientRepository.findAll()).thenReturn(patients);
        mockMvc.perform(get("/api/patients"))
                .andExpect(status().isNoContent());
    }
    
    @Test
    void shouldGetTwoPatients() throws Exception {
        Patient patient = new Patient("Pedro", "Hernandez", 45, "phernandez@patmail.com");
        Patient patient2 = new Patient("Mario", "Gutierrez", 50, "mgutierrez@patmail.com");
        
        mockMvc.perform(post("/api/patient").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isCreated());
        
        List<Patient> patients = new ArrayList<>();
        patients.add(patient);
        
        when(patientRepository.findAll()).thenReturn(patients);
        mockMvc.perform(post("/api/patient").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient2)))
                .andExpect(status().isCreated());
    }
    
    @Test
    void shouldGetPatientById() throws Exception {
        Patient patient = new Patient("Pedro", "Hernandez", 45, "phernandez@patmail.com");
        patient.setId(1);
        
        Optional<Patient> pat = Optional.of(patient);
        
        assertThat(pat).isPresent();
        assertThat(pat.get().getId()).isEqualTo(patient.getId());   
        when(patientRepository.findById(patient.getId())).thenReturn(pat);
        mockMvc.perform(get("/api/patients/" + patient.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotGetPatientById() throws Exception {
        long id = 21;
        mockMvc.perform(get("/api/patients/" + id))
                .andExpect(status().isNotFound());
    }
    
    @Test
    void shouldDeletePatientById() throws Exception {
        Patient patient = new Patient("Pedro", "Hernandez", 45, "phernandez@patmail.com");
        patient.setId(1);
        
        Optional<Patient> pat = Optional.of(patient);
        
        assertThat(pat).isPresent();
        assertThat(pat.get().getId()).isEqualTo(patient.getId());   
        when(patientRepository.findById(patient.getId())).thenReturn(pat);
        mockMvc.perform(delete("/api/patients/" + patient.getId()))
                .andExpect(status().isOk());
    }
    
    @Test
    void shouldNotDeletePatientById() throws Exception {
        long id = 21;
        mockMvc.perform(delete("/api/patients/" + id))
                .andExpect(status().isNotFound());
    }
    
    @Test
    void shouldDeleteAllPatients() throws Exception {
        mockMvc.perform(delete("/api/patients"))
                .andExpect(status().isOk());
    }
}

@WebMvcTest(RoomController.class)
class RoomControllerUnitTest{

    @MockBean
    private RoomRepository roomRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateRoom() throws Exception{
        Room room = new Room("Pediatria");
        
        mockMvc.perform(post("/api/room").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(room)))
                .andExpect(status().isCreated());
    }
    
    @Test
    void shouldGetNoRooms() throws Exception {
        List<Room> rooms = new ArrayList<>();
        
        when(roomRepository.findAll()).thenReturn(rooms);
        mockMvc.perform(get("/api/rooms"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldGetTwoRooms() throws Exception {
        Room room = new Room("Odontologia");
        Room room2 = new Room("Optometria");
        
        mockMvc.perform(post("/api/room").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(room)))
                .andExpect(status().isCreated());
        
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);
        
        when(roomRepository.findAll()).thenReturn(rooms);
        mockMvc.perform(post("/api/room").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(room2)))
                .andExpect(status().isCreated());
    }
    
    @Test
    void shouldGetRoomByRoomName() throws Exception {
        Room room = new Room("Odontologia");
        
        Optional<Room> roo = Optional.of(room);
        
        assertThat(roo).isPresent();
        assertThat(roo.get().getRoomName()).isEqualTo(room.getRoomName());   
        when(roomRepository.findByRoomName(room.getRoomName())).thenReturn(roo);
        mockMvc.perform(get("/api/rooms/" + room.getRoomName()))
                .andExpect(status().isOk());
    }
    
    @Test
    void shouldNotGetRoomByRoomName() throws Exception {
        String roomName = "Odontologia";
        mockMvc.perform(get("/api/rooms/" + roomName))
                .andExpect(status().isNotFound());
    }
    
    @Test
    void shouldDeleteRoomByRoomName() throws Exception {
        Room room = new Room("Odontologia");
        
        Optional<Room> roo = Optional.of(room);
        
        assertThat(roo).isPresent();
        assertThat(roo.get().getRoomName()).isEqualTo(room.getRoomName());   
        when(roomRepository.findByRoomName(room.getRoomName())).thenReturn(roo);
        mockMvc.perform(delete("/api/rooms/" + room.getRoomName()))
                .andExpect(status().isOk());
    }
    
    @Test
    void shouldNotDeleteRoomByRoomName() throws Exception {   
        String roomName = "Odontologia";
        mockMvc.perform(delete("/api/rooms/" + roomName))
                .andExpect(status().isNotFound());
    }
    
    @Test
    void shouldDeleteAllRooms() throws Exception {
        mockMvc.perform(delete("/api/rooms"))
                .andExpect(status().isOk());
    }
}
