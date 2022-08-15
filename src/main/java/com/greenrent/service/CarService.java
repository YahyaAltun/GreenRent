package com.greenrent.service;

import com.greenrent.domain.Car;
import com.greenrent.domain.ImageFile;
import com.greenrent.dto.CarDTO;
import com.greenrent.dto.mapper.CarMapper;
import com.greenrent.exception.BadRequestException;
import com.greenrent.exception.ResourceNotFoundException;
import com.greenrent.exception.message.ErrorMessage;
import com.greenrent.repository.CarRepository;
import com.greenrent.repository.ImageFileRepository;
import com.greenrent.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class CarService {

    private CarRepository carRepository;

    private ImageFileRepository imageFileRepository;

    private CarMapper carMapper;

    private ReservationRepository reservationRepository;


    @Transactional(readOnly = true)
    public List<CarDTO> getAllCars(){
        List<Car> carList = carRepository.findAll();
        return carMapper.map(carList);
    }

    @Transactional(readOnly = true)
    public CarDTO findById(Long id){
        Car car=carRepository.findById(id).orElseThrow(()->new
                ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE,id)));

        return carMapper.carToCarDTO(car);
    }

    public void  saveCar(CarDTO carDTO, String imageId){
        ImageFile imFile = imageFileRepository.findById(imageId).orElseThrow(()-> new
                ResourceNotFoundException(String.format(ErrorMessage.IMAGE_NOT_FOUND_MESSAGE,imageId)));

        Car car= carMapper.carDTOToCar(carDTO);

//        Car car = CarMapper.INSTANCE.carDTOToCar(carDTO);

        Set<ImageFile> imFiles=new HashSet<>();
        imFiles.add(imFile);
        car.setImage(imFiles);

        carRepository.save(car);
    }

    @Transactional(readOnly = true)
    public Page<CarDTO> findAllWithPage(Pageable pageable){
        return carRepository.findAllCarWithPage(pageable);
    }

    public void updateCar(Long id, String imageId, CarDTO carDTO){
        Car foundCar = carRepository.findById(id).orElseThrow(()-> new
                ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE,id)));

        ImageFile imFile = imageFileRepository.findById(imageId).orElseThrow(()->new
                ResourceNotFoundException(String.format(ErrorMessage.IMAGE_NOT_FOUND_MESSAGE,imageId)));

        if (foundCar.getBuiltIn()){
            throw new BadRequestException(ErrorMessage.NOT_PERMITTED_METHOD_MESSAGE);
        }

        Set<ImageFile> imgs = foundCar.getImage();
        imgs.add(imFile);

        Car car = carMapper.carDTOToCar(carDTO);

        car.setId(foundCar.getId());
        car.setImage(imgs);
    }

    public void removeById(Long id){
        Car car = carRepository.findById(id).orElseThrow(()-> new
                ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE,id)));

        boolean exists = reservationRepository.existsByCarId(car);
        if (exists){
            throw new BadRequestException(ErrorMessage.CAR_USED_BY_RESERVATION_MESSAGE);
        }

        if (car.getBuiltIn()){
            throw new BadRequestException(ErrorMessage.NOT_PERMITTED_METHOD_MESSAGE);
        }
        carRepository.deleteById(id);
    }
}
