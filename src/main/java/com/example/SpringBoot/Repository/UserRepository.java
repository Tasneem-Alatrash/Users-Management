package com.example.SpringBoot.Repository;

import com.example.SpringBoot.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> , JpaSpecificationExecutor<User> {

    @Query("SELECT u FROM User u")
    Page<User> getAllUsersJpqlPaginated(Pageable pageable);

    @Query(value = "SELECT * FROM \"user\" ORDER BY id" ,
            countQuery = "SELECT count(*) FROM \"user\" " ,
            nativeQuery = true
    )
    Page<User> getAllUsersNativePaginated(Pageable pageable);

    @Query("SELECT u.id FROM User u")
    Page<Integer> findUserIdsPaginated(Pageable pageable);

    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.addresses WHERE u.id IN :ids")
    List<User> findUsersWithAddressesByIds(@Param("ids") List<Integer> ids);

}
