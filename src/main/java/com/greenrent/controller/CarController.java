package com.greenrent.controller;

import com.greenrent.dto.CarDTO;
import com.greenrent.dto.response.GRResponse;
import com.greenrent.dto.response.ResponseMessage;
import com.greenrent.service.CarService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/car")
public class CarController {

    private CarService carService;


    //http://localhost:8083/car/admin/55b3dccd-5eee-4426-bc94-fbf4c1250b8d/add
    @PostMapping("/admin/{imageId}/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GRResponse> saveCar(@PathVariable String imageId,
                                              @Valid @RequestBody CarDTO carDTO){

        carService.saveCar(carDTO, imageId);

        GRResponse response=new GRResponse();
        response.setMessage(ResponseMessage.CAR_SAVED_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    //http://localhost:8083/car/visitors/all
    @GetMapping("/visitors/all")
    public ResponseEntity<List<CarDTO>> getAllCars(){
        List<CarDTO> carDTOs = carService.getAllCars();

        return ResponseEntity.ok(carDTOs);
    }

    //http://localhost:8083/car/visitors/1
    @GetMapping("/visitors/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long id){
        CarDTO carDTO=carService.findById(id);

        return ResponseEntity.ok(carDTO);
    }

    //http://localhost:8083/car/visitors/pages?page=0&size=5&sort=model&direction=ASC
    @GetMapping("/visitors/pages")
    public ResponseEntity<Page<CarDTO>> getAllWithPage(@RequestParam("page") int page,
                                                       @RequestParam("size") int size,
                                                       @RequestParam("sort") String prop,
                                                       @RequestParam("direction") Sort.Direction direction){

        Pageable pageable= PageRequest.of(page, size, Sort.by(direction,prop));
        Page<CarDTO> carPage= carService.findAllWithPage(pageable);

        return ResponseEntity.ok(carPage);
    }

    //http://localhost:8083/car/admin/auth?id=1&imageId=55b3dccd-5eee-4426-bc94-fbf4c1250b8d
    @PutMapping("/admin/auth")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GRResponse> updateCar(@RequestParam("id") Long id,
                                                @RequestParam("imageId") String imageId,
                                                @Valid @RequestBody CarDTO carDTO){

        carService.updateCar(id, imageId, carDTO);
        GRResponse response = new GRResponse();
        response.setMessage(ResponseMessage.CAR_UPDATED_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return ResponseEntity.ok(response);
    }

    //http://localhost:8083/car/admin/2/auth
    @DeleteMapping("/admin/{id}/auth")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GRResponse> deleteCar(@PathVariable ("id") Long id){
        carService.removeById(id);

        GRResponse response = new GRResponse();
        response.setMessage(ResponseMessage.CAR_DELETED_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return ResponseEntity.ok(response);
    }
}
