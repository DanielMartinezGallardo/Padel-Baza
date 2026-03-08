package com.padel.back.controller;

import com.padel.back.entity.Reserva;
// import com.padel.back.service.ReservaService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/reservas")
@CrossOrigin(origins = "http://localhost:4200") // Angular
public class ReservaController {

    // private final ReservaService reservaService;

    // public ReservaController(ReservaService reservaService) {
    //     this.reservaService = reservaService;
    // }

    // @PostMapping
    // public Reserva crearReserva(
    //         @RequestParam Long usuarioId,
    //         @RequestParam Long pistaId,
    //         @RequestParam String fecha,
    //         @RequestParam String horaInicio
    // ) {

    //     return reservaService.crearReserva(
    //             usuarioId,
    //             pistaId,
    //             LocalDate.parse(fecha),
    //             LocalTime.parse(horaInicio)
    //     );
    // }
}
