package com.padel.back.repository;

import com.padel.back.entity.Reserva;
import com.padel.back.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate; 
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    Optional<Reserva> findByPistaIdAndFechaAndHoraInicio(
            Long pistaId,
            LocalDate fecha,
            LocalTime horaInicio
    );

    List<Reserva> findByFechaAndPistaId(LocalDate fecha, Long pistaId);

    List<Reserva> findByUsuario(Usuario usuario);
}
