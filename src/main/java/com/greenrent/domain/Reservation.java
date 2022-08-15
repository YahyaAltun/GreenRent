package com.greenrent.domain;


import com.greenrent.domain.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="tbl_reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime pickUpTime;

    @Column(nullable = false)
    private LocalDateTime dropOffTime;

    @Column(length=150, nullable = false)
    private String pickUpLocation;

    @Column(length=150, nullable = false)
    private String dropOffLocation;

    @Enumerated(EnumType.STRING)
    @Column(length = 30,nullable = false)
    private ReservationStatus status;

    @Column(nullable = false)
    private Double totalPrice;

    @OneToOne
    @JoinColumn(name="car_id",referencedColumnName = "id")
    private Car carId;


    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User userId;

    public Long getTotalHours(LocalDateTime pickUpTime, LocalDateTime dropOffTime) {
        return ChronoUnit.HOURS.between(pickUpTime, dropOffTime);
    }
}
