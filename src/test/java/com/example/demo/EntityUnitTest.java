package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.example.demo.entities.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@TestInstance(Lifecycle.PER_CLASS)
class EntityUnitTest {

    @Autowired
    private TestEntityManager entityManager;

    private Doctor d1;

    private Patient p1;

    private Room r1;

    private Appointment a1;
    private Appointment a2;
    private Appointment a3;
    /** TODO
     * Implement tests for each Entity class: Doctor, Patient, Room and Appointment.
     * Make sure you are as exhaustive as possible. Coverage is checked ;)
     */
    
    /**
     * Doctor entity Tests 
     */
    @Test
    void doctorTestConstructorAndGetters(){
       this.d1 = new Doctor("Roberto", "Hernandez",28, "rhernandez@docmail.com");
       
       assertThat("Roberto").isEqualTo(this.d1.getFirstName());
       assertThat("Hernandez").isEqualTo(this.d1.getLastName());
       assertThat(28).isEqualTo(this.d1.getAge());
       assertThat("rhernandez@docmail.com").isEqualTo(this.d1.getEmail());
    }
    
    @Test
    void doctorTestDefaultConstructor(){
       this.d1 = new Doctor();
       
       assertThat(this.d1.getFirstName()).isNull();
       assertThat(this.d1.getLastName()).isNull();
       assertThat(0).isEqualTo(this.d1.getAge());
       assertThat(this.d1.getEmail()).isNull();
    }
    
    @Test
    void doctorTestEqualityAndSetters(){
       this.d1 = new Doctor();
       Doctor doctor = new Doctor("Pedro", "Jimenez", 37, "pjimenez@docmail.com");
       doctor.setId(13L);
       Doctor doctor2 = new Doctor("Maria", "Rocha", 43, "mrocha@docmail.com");
       doctor2.setId(24L);
       
       this.d1.setFirstName("Pedro");
       this.d1.setLastName("Jimenez");
       this.d1.setAge(37);
       this.d1.setEmail("pjimenez@docmail.com");
       this.d1.setId(13L);
       
       assertThat(this.d1.getId()).isEqualTo(doctor.getId());
       assertThat(this.d1.getFirstName()).isEqualTo(doctor.getFirstName());
       assertThat(this.d1.getLastName()).isEqualTo(doctor.getLastName());
       assertThat(this.d1.getAge()).isEqualTo(doctor.getAge());
       assertThat(this.d1.getEmail()).isEqualTo(doctor.getEmail());
       assertThat(this.d1).isNotEqualTo(doctor2);
    }
       /**
        * Patient entity Tests                   
        */
    @Test
    void patientTestConstructorAndGetters(){
       this.p1 = new Patient("Roberto", "Hernandez", 37, "rhernandez@patmail.com");       
       assertThat("Roberto").isEqualTo(this.p1.getFirstName());
       assertThat("Hernandez").isEqualTo(this.p1.getLastName());
       assertThat(37).isEqualTo(this.p1.getAge());
       assertThat("rhernandez@patmail.com").isEqualTo(this.p1.getEmail());
    }
    
    @Test
    void patientTestDefaultConstructor(){
       this.p1 = new Patient();
       
       assertThat(this.p1.getFirstName()).isNull();
       assertThat(this.p1.getLastName()).isNull();
       assertThat(0).isEqualTo(this.p1.getAge());
       assertThat(this.p1.getEmail()).isNull();
    }
    
    @Test
    void patientTestEqualityAndSetters(){
       this.p1 = new Patient();
       Patient patient = new Patient("Pedro", "Jimenez", 37, "pjimenez@patmail.com");
       patient.setId(13L);
       Patient patient2 = new Patient("Maria", "Rocha", 43, "mrocha@patmail.com");
       patient2.setId(24L);
       
       this.p1.setFirstName("Pedro");
       this.p1.setLastName("Jimenez");
       this.p1.setAge(37);
       this.p1.setEmail("pjimenez@patmail.com");
       this.p1.setId(13L);
       
       assertThat(this.p1.getId()).isEqualTo(patient.getId());
       assertThat(this.p1.getFirstName()).isEqualTo(patient.getFirstName());
       assertThat(this.p1.getLastName()).isEqualTo(patient.getLastName());
       assertThat(this.p1.getAge()).isEqualTo(patient.getAge());
       assertThat(this.p1.getEmail()).isEqualTo(patient.getEmail());
       assertThat(this.p1).isNotEqualTo(patient2);
    }
    
    /**
     * Room entity tests
     */
    @Test
    void roomTestConstructorAndGetters(){
       this.r1 = new Room("Pediatria");       
       assertThat("Pediatria").isEqualTo(this.r1.getRoomName());
    }
    
    @Test
    void roomTestDefaultConstructor(){
       this.r1 = new Room();
       assertThat(this.r1.getRoomName()).isNull();
    }
    
    /**
     * Appointment entity Tests
     */
    @Test
    void appointmentTestConstructorAndGetters(){
       this.d1 = new Doctor("Roberto", "Hernandez",28, "rhernandez@docmail.com");
       this.d1.setId(25L);
       this.p1 = new Patient("Raul", "Gimenez",25, "rgimenez@patmail.com");
       this.p1.setId(13L);
       this.r1 = new Room("Ortopedia");
       
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        
       LocalDateTime startsAt= LocalDateTime.parse("19:30 24/04/2023", formatter);
       LocalDateTime finishesAt = LocalDateTime.parse("20:30 24/04/2023", formatter);
       
       this.a1 = new Appointment(this.p1, this.d1, this.r1, startsAt, finishesAt);
       assertThat(this.p1).isEqualTo(this.a1.getPatient());
       assertThat(this.d1).isEqualTo(this.a1.getDoctor());
       assertThat(this.r1).isEqualTo(this.a1.getRoom());
       assertThat(startsAt).isEqualTo(this.a1.getStartsAt());
       assertThat(finishesAt).isEqualTo(this.a1.getFinishesAt());
    }
    
    @Test
    void appointmentTestDefaultConstructor(){
       this.a1 = new Appointment();
       
       assertThat(this.a1.getPatient()).isNull();
       assertThat(this.a1.getDoctor()).isNull();
       assertThat(this.a1.getRoom()).isNull();
       assertThat(this.a1.getStartsAt()).isNull();
       assertThat(this.a1.getFinishesAt()).isNull();
    }
    
    @Test
    void appointmentTestOverlapsAndSetters(){
       //Doctors declaration
       this.d1 = new Doctor("Armando", "Casas", 54, "acasas@docmail.com");
       this.d1.setId(23L);
       Doctor doctor = new Doctor("Pedro", "Jimenez", 37, "pjimenez@docmail.com");
       doctor.setId(13L);
       Doctor doctor2 = new Doctor("Maria", "Rocha", 43, "mrocha@docmail.com");
       doctor2.setId(24L);
       
       //Patients declaration
       this.p1 = new Patient("Raul", "Gimenez",25, "rgimenez@patmail.com");
       this.p1.setId(13L);
       Patient patient = new Patient("Marcela", "Diaz",27, "mdiaz@patmail.com");
       patient.setId(17L);
       Patient patient2 = new Patient("Luis", "Farfan",20, "lfarfan@patmail.com");
       patient2.setId(3L);
       
       //Rooms declarations
       this.r1 = new Room("Ortopedia");
       Room room = new Room("Psiquiatria");
       
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        
       LocalDateTime startsAt= LocalDateTime.parse("19:30 23/07/2023", formatter);
       LocalDateTime finishesAt = LocalDateTime.parse("20:30 23/07/2023", formatter);
       
       LocalDateTime startsAt2= LocalDateTime.parse("20:00 23/07/2023", formatter);
       LocalDateTime finishesAt2 = LocalDateTime.parse("21:00 23/07/2023", formatter);
       
       
       this.a1 = new Appointment(this.p1,this.d1,this.r1, startsAt, finishesAt);
       this.a2 = new Appointment(patient,doctor,this.r1, startsAt2, finishesAt2);
       this.a3 = new Appointment();
       this.a3.setPatient(patient2);
       this.a3.setDoctor(doctor2);
       this.a3.setRoom(room);
       this.a3.setStartsAt(startsAt2);
       this.a3.setFinishesAt(finishesAt2);
       
       assertThat(this.a1.overlaps(this.a2)).isEqualTo(true);
       assertThat(this.a1.overlaps(this.a3)).isEqualTo(false);
       assertThat(this.a3.getPatient()).isEqualTo(patient2);
       assertThat(this.a3.getDoctor()).isEqualTo(doctor2);
       assertThat(this.a3.getRoom()).isEqualTo(room);
       assertThat(this.a3.getStartsAt()).isEqualTo(startsAt2);
       assertThat(this.a3.getFinishesAt()).isEqualTo(finishesAt2);
    }
}
