package com.example.SpringBoot.specification;

import com.example.SpringBoot.Model.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<User> hasFirstName(String firstName){
        return ((root, query, criteriaBuilder) -> {
            if( firstName == null || firstName.isEmpty()){
                return null;
            }
            return criteriaBuilder.equal(root.get("firstName") , firstName);
        });
    }

    public static Specification<User> hasLastName(String lastName){
        return ((root, query, criteriaBuilder) -> {
            if(lastName == null || lastName.isEmpty())
                return null;
            return criteriaBuilder.equal(root.get("lastName"), lastName);
        });
    }

    public static Specification<User> hasPhoneNumber(String phoneNumber){
        return ((root, query, criteriaBuilder) -> {
            if(phoneNumber == null || phoneNumber.isEmpty())
                return null;
            return criteriaBuilder.equal(root.get("phoneNumber") , phoneNumber);
        });
    }
 }
