package com.example.SpringBoot.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.Mapping;

@Entity
@Data
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
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

}
