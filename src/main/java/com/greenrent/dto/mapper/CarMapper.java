package com.greenrent.dto.mapper;

import com.greenrent.domain.Car;
import com.greenrent.domain.ImageFile;
import com.greenrent.dto.CarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel="spring")
//@Mapper
public interface CarMapper {

    CarMapper INSTANCE= Mappers.getMapper(CarMapper.class);

    @Mapping(target="image", ignore=true)
    Car carDTOToCar(CarDTO carDTO);

    @Mapping(source="image", target="image", qualifiedByName="getImageAsString")
    CarDTO carToCarDTO(Car car);

    @Named("getImageAsString")
    public static Set<String> getImageId(Set<ImageFile> images){
        Set<String> imgs=new HashSet<>();
        imgs=images.stream().map(image->image.getId().toString()).collect(Collectors.toSet());

        return imgs;
    }


    List<CarDTO> map(List<Car> cars);

}
