package com.major.project.attendancemanagementbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.major.project.attendancemanagementbackend.DTo.InstituteDTO;
import com.major.project.attendancemanagementbackend.constants.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity

@Table(name = "institute")
public class Institute {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Getter
    private Long id;
    @Column(name = "name",unique = true)
            @Getter@Setter
    String name;
    @Column(name = "collegeId")
    @Getter@Setter

    Long collegeId;
    @Column(unique = true)
    @Getter@Setter

    String email;
    @Column(unique = true)
    @Getter@Setter
    String address;
    @Column(unique = true)
    @Getter@Setter

    String city;
    @Column(unique = true)
    @Getter@Setter

    String postalCode;
    @Column(unique = true)
    @Getter@Setter
    String firebaseId;
    @Column
    @Getter@Setter

    String mobile;
    @Column
    @Getter@Setter

    Role role;
    @OneToMany(mappedBy = "institute",fetch = FetchType.LAZY,orphanRemoval = true)
    @Getter@Setter
    Set<FingerprintDevice> devices=new HashSet<>();
    @OneToMany(mappedBy = "institute",fetch = FetchType.LAZY,orphanRemoval = true)
    @Getter@Setter
    Set<Staff> staff=new HashSet<>();
    @OneToMany(mappedBy = "institute",fetch = FetchType.LAZY,orphanRemoval = true)
    @Getter@Setter
    Set<Course> courses=new HashSet<>();
    @OneToMany(mappedBy = "institute",fetch = FetchType.LAZY,orphanRemoval = true)
    @Getter@Setter
    Set<Student> students=new HashSet<>();

    public InstituteDTO toDto(){
        InstituteDTO instituteDTO=new InstituteDTO();
        instituteDTO.setId(id);
        instituteDTO.setName(name);
        instituteDTO.setCity(city);
        instituteDTO.setAddress(address);
        instituteDTO.setEmail(email);
        instituteDTO.setMobile(mobile);
        instituteDTO.setCollegeId(collegeId);
        instituteDTO.setPostalCode(postalCode);
        return instituteDTO;

    }
}
