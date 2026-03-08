package com.padel.back.service;

import com.padel.back.entity.*;
import com.padel.back.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PistaRepository pistaRepository;

    public ReservaService(ReservaRepository reservaRepository,
                          UsuarioRepository usuarioRepository,
                          PistaRepository pistaRepository) {
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
        this.pistaRepository = pistaRepository;
    }

    @Transactional
    public Reserva crearReserva(Long usuarioId,
                                 Long pistaId,
                                 LocalDate fecha,
                                 LocalTime horaInicio) {

        // 1️⃣ Validar que el usuario existe y está activo
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // if (!usuario.getActivo()) {
        //     throw new RuntimeException("Usuario inactivo");
        // }

        // 2️⃣ Validar que la pista existe y está activa
        Pista pista = pistaRepository.findById(pistaId)
                .orElseThrow(() -> new RuntimeException("Pista no encontrada"));

        // if (!pista.getActiva()) {
        //     throw new RuntimeException("Pista no disponible");
        // }

        // 3️⃣ Validar que no existe ya reserva en ese horario
        reservaRepository.findByPistaIdAndFechaAndHoraInicio(pistaId, fecha, horaInicio)
                .ifPresent(r -> {
                    throw new RuntimeException("Horario no disponible");
                });

        // 4️⃣ Crear reserva
        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setPista(pista);
        reserva.setFecha(fecha);
        reserva.setHoraInicio(horaInicio);
        reserva.setEstado(EstadoReserva.CONFIRMADA);

        return reservaRepository.save(reserva);
    }
}
