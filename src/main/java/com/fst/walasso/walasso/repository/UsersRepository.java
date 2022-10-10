package com.fst.walasso.walasso.repository;

import java.util.List;

import com.fst.walasso.walasso.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    //boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    /*boolean existsByTelephone(String telephone);

    Users findByEmail(String email);*/

    Users findByUsername(String username);

    //Users findByTelephone(String telephone);

    //List<Users> findAllByOrderByNomAsc();
}
