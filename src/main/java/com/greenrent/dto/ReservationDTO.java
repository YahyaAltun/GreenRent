package com.greenrent.dto;

import com.greenrent.domain.Reservation;
import com.greenrent.domain.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {

    private Long id;

    private CarDTO carId;

    private Long userId;

    private LocalDateTime pickUpTime;

    private LocalDateTime dropOffTime;

    private String pickUpLocation;

    private String dropOffLocation;

    private ReservationStatus status;

    private Double totalPrice;

    public ReservationDTO(Reservation reservation) {
        this.id=reservation.getId();
        this.carId=new CarDTO(reservation.getCarId());
        this.userId=reservation.getUserId().getId();
        this.pickUpTime=reservation.getPickUpTime();
        this.dropOffTime=reservation.getDropOffTime();
        this.pickUpLocation=reservation.getPickUpLocation();
        this.dropOffLocation=reservation.getDropOffLocation();
        this.status=reservation.getStatus();
        this.totalPrice=reservation.getTotalPrice();
    }
}
