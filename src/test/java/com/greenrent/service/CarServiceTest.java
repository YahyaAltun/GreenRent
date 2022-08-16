package com.greenrent.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.greenrent.domain.Car;
import com.greenrent.dto.CarDTO;
import com.greenrent.dto.mapper.CarMapper;
import com.greenrent.repository.CarRepository;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    CarRepository carRepository;

    @Mock
    CarMapper carMapper;


    @InjectMocks
    CarService underTest;



    @Test
    public void getAllCarTest(){

        List<Car> list = new ArrayList<>();

        Car car = new Car();
        car.setId(1L);
        car.setModel("Merso");

        Car car2 = new Car();
        car2.setId(2L);
        car2.setModel("BMW");

        Car car3 = new Car();
        car3.setId(3L);
        car3.setModel("Seat");

        list.add(car);
        list.add(car2);
        list.add(car3);

        List<CarDTO> listDTO = new ArrayList<>();

        CarDTO carDTO = new CarDTO();
        carDTO.setId(1L);
        carDTO.setModel("Merso");

        CarDTO carDTO2 = new CarDTO();
        carDTO2.setId(2L);
        carDTO2.setModel("BMW");

        CarDTO carDTO3 = new CarDTO();
        carDTO3.setId(3L);
        carDTO3.setModel("Seat");

        listDTO.add(carDTO);
        listDTO.add(carDTO2);
        listDTO.add(carDTO3);


        when(carRepository.findAll()).thenReturn(list);

        when(carMapper.map(list)).thenReturn(listDTO);

        List<CarDTO> listActual= underTest.getAllCars();

        assertEquals(3 ,listActual.size());

        verify(carRepository,times(1)).findAll();

    }

}
