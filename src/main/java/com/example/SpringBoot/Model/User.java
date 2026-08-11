package com.example.SpringBoot.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"user\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name" , nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name" , nullable = false, length = 50)
    private String lastName;
    @Column(name = "phone_number" , nullable = false , unique = true , length = 20)
    private String phoneNumber;

    @Column(name = "password" , nullable = false , length = 255)
    @ToString.Exclude
    private String password;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

}
