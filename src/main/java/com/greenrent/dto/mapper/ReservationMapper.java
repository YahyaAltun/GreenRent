package com.greenrent.dto.mapper;

import com.greenrent.domain.Reservation;
import com.greenrent.dto.request.ReservationRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    Reservation  reservationRequestToReservation(ReservationRequest reservationRequest);
}
