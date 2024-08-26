package com.elt.repository;

import com.elt.entity.LoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginDetails,Long> {
    Optional<LoginDetails> findByEmail(String email);
}
