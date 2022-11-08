package com.greenrent.controller;

import com.greenrent.dto.CarDTO;
import com.greenrent.dto.ReservationDTO;
import com.greenrent.dto.request.ReservationRequest;
import com.greenrent.dto.request.ReservationUpdateRequest;
import com.greenrent.dto.response.CarAvailabilityResponse;
import com.greenrent.dto.response.GRResponse;
import com.greenrent.dto.response.ResponseMessage;
import com.greenrent.service.CarService;
import com.greenrent.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private ReservationService reservationService;


    //http://localhost:8083/reservation/add?carId=1
    @PostMapping("/add")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<GRResponse> makeReservation(HttpServletRequest request, @RequestParam(value="carId") Long carId,
                                                      @Valid @RequestBody ReservationRequest reservationRequest){
        Long userId= (Long) request.getAttribute("id");
        reservationService.createReservation(reservationRequest, userId, carId);

        GRResponse response=new GRResponse();
        response.setMessage(ResponseMessage.RESERVATION_SAVED_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    //http://localhost:8083/reservation/add/auth?userId=2&carId=1
    @PostMapping("/add/auth")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GRResponse> addReservation(@RequestParam(value="userId") Long userId,
                                                     @RequestParam(value="carId") Long carId,
                                                     @Valid @RequestBody ReservationRequest reservationRequest){

        reservationService.createReservation(reservationRequest, userId, carId);

        GRResponse response=new GRResponse();
        response.setMessage(ResponseMessage.RESERVATION_SAVED_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //http://localhost:8083/reservation/admin/auth?carId=1&reservationId=1
    @PutMapping("/admin/auth")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GRResponse> updateReservation(@RequestParam(value="carId") Long carId,
                                                        @RequestParam(value="reservationId") Long reservationId,
                                                        @Valid @RequestBody ReservationUpdateRequest reservationUpdateRequest){

        reservationService.updateReservation(reservationId,carId,reservationUpdateRequest);

        GRResponse response=new GRResponse();
        response.setMessage(ResponseMessage.RESERVATION_UPDATE_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //http://localhost:8083/reservation/auth?carId=1&pickUpDateTime=07/18/2022 11:00:00&dropOfDateTime=07/18/2022 14:00:00
    @GetMapping("/auth")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<GRResponse> checkCarIsAvailable(@RequestParam(value = "carId") Long carId,
                                                          @RequestParam(value = "pickUpDateTime") @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")LocalDateTime pickUpTime,
                                                          @RequestParam(value = "dropOffDateTime") @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")LocalDateTime dropOffTime){

        boolean isNotAvailable = reservationService.checkCarAvailability(carId, pickUpTime, dropOffTime);
        Double totalPrice = reservationService.getTotalPrice(carId, pickUpTime, dropOffTime);

        CarAvailabilityResponse response = new
                CarAvailabilityResponse(!isNotAvailable,totalPrice,ResponseMessage.CAR_AVAILABLE_MESSAGE,true);

        return  new ResponseEntity<>(response, HttpStatus.OK);
    }


    //http://localhost:8083/reservation/admin/2/auth
    @DeleteMapping("admin/{id}/auth")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GRResponse> deleteReservation(@PathVariable("id") Long id){
        reservationService.removeById(id);

        GRResponse response = new GRResponse();
        response.setMessage(ResponseMessage.RESERVATION_DELETED_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Admin butun reservation bilgilerini getirmek icin kullaniyor
    //http://localhost:8083/reservation/admin/all
    @GetMapping("/admin/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ReservationDTO>> getAllReservations(){
        List<ReservationDTO> reservations = reservationService.getAllReservations();
        return  ResponseEntity.ok(reservations);
    }

    //Admin bir reservation id ile reservation bilgisini dondurmek icin kullaniyor.
    //http://localhost:8083/reservation/1/admin
    @GetMapping("/{id}/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable ("id") Long id){
        ReservationDTO reservations = reservationService.findById(id);
        return  ResponseEntity.ok(reservations);
    }

    //Bir userId ile gelerek user'a ait olan tüm reservasyonları döndürüyor
    //http://localhost:8083/reservation/admin/auth/all?userId=1
    @GetMapping("/admin/auth/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ReservationDTO>> getAllUserReservations(@RequestParam(value="userId") Long userId){
        List<ReservationDTO> reservations= reservationService.findAllByUserId(userId);
        return ResponseEntity.ok(reservations);
    }

    //Customer yada admin rolüne sahip bir kullanıcı kendisine ait olan bir reservasyon bilgisini getiriyor.
    //http://localhost:8083/reservation/2/auth
    @GetMapping("/{id}/auth")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<ReservationDTO> getUserReservationById(HttpServletRequest request, @PathVariable Long id){
        Long userId= (Long) request.getAttribute("id");
        ReservationDTO reservationDTO = reservationService.findByIdAndUserId(id, userId);
        return ResponseEntity.ok(reservationDTO);
    }

    //Customer yada admin rolüne sahip bir kullanıcı kendisine ait olan bütün reservasyon bilgilerini getiriyor.
    //http://localhost:8083/reservations/auth/all
    @GetMapping("/auth/all")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<List<ReservationDTO>> getUserReservationsByUserId(HttpServletRequest request){
        Long userId= (Long) request.getAttribute("id");
        List<ReservationDTO> reservations = reservationService.findAllByUserId(userId);
        return ResponseEntity.ok(reservations);
    }
}
