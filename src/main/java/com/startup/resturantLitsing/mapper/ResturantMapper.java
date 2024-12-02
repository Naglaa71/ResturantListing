package com.startup.resturantLitsing.mapper;

import com.startup.resturantLitsing.dto.ResturantDto;
import com.startup.resturantLitsing.entity.Resturant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ResturantMapper {

    ResturantMapper INSTANCE = Mappers.getMapper(ResturantMapper.class);

    Resturant resturnatDtoToResturant (ResturantDto restDto);
    ResturantDto resturantToResturantDto (Resturant rest);
}
