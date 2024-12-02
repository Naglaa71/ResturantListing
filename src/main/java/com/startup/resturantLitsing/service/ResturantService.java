package com.startup.resturantLitsing.service;

import com.startup.resturantLitsing.dto.ResturantDto;
import com.startup.resturantLitsing.entity.Resturant;
import com.startup.resturantLitsing.mapper.ResturantMapper;
import com.startup.resturantLitsing.repo.ResturantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResturantService {
    @Autowired
    private ResturantRepo repo;

    public List<ResturantDto> findAll() {
        List<Resturant> allDbRest = repo.findAll();
        List<ResturantDto> allRestDto = allDbRest.stream()
                .map(dbRest -> ResturantMapper.INSTANCE.resturantToResturantDto(dbRest))
                .collect(Collectors.toList());

        return allRestDto;
    }

    public Resturant registerResturant(ResturantDto resturantDto) {
        Resturant resturant = ResturantMapper.INSTANCE.resturnatDtoToResturant(resturantDto);
        return repo.save(resturant);
    }

    public ResturantDto findResturantById(Integer id) throws Exception {
        Optional<Resturant> rest = repo.findById(id);
        if (rest.isPresent()) {
            ResturantDto restDto = ResturantMapper.INSTANCE.resturantToResturantDto(rest.get());
            return restDto;
        } else {
            throw new Exception ("Resturant ID not found.");
        }
    }

    public Resturant updateResturant(Integer id, ResturantDto restDto) throws Exception {
        Optional<Resturant> rest = repo.findById(id);
        if (rest.isPresent()) {
            restDto.setId(rest.get().getId());
            rest = Optional.of(ResturantMapper.INSTANCE.resturnatDtoToResturant(restDto));
            rest = Optional.of(repo.save(rest.get()));
            return rest.get();
        } else {
            throw new Exception ("Resturant ID not found.");
        }
    }
}
