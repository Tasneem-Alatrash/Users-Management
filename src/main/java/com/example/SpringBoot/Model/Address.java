package com.example.SpringBoot.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.bind.annotation.Mapping;

@Entity
@Data
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "street" , nullable = false)
    private String street;

    @Column(name = "city" , nullable = false)
    private String city;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;

    public Address(){}
}
