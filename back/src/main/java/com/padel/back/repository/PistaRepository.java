package com.padel.back.repository;

import com.padel.back.entity.Pista;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PistaRepository extends JpaRepository<Pista, Long> {

    List<Pista> findByActivaTrue();

}
