package com.example.SpringBoot.Repository;

import com.example.SpringBoot.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueryPracticeRepository extends JpaRepository<User, Integer> {

    // Derived
    List<User> findByFirstNameAndLastName(String firstName, String lastName);

    // JPQL
    @Query(""" 
            SELECT u
            FROM User u 
            WHERE u.firstName = :firstName AND u.lastName = :lastName 
            """)
    List<User> searchByNameJPQL (
            @Param("firstName") String firstName,
            @Param("lastName") String lastName);

    // Native
    @Query(value = """
        SELECT * 
        FROM \"user\" 
        WHERE first_name = :firstName AND last_name = :lastName 
        """ ,
        nativeQuery = true)
    List<User> searchByNameNative (
            @Param("firstName") String firstName,
            @Param("lastName") String lastName);

}
