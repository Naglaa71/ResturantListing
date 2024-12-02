package com.startup.resturantLitsing.controller;

import com.startup.resturantLitsing.dto.ResturantDto;

import com.startup.resturantLitsing.entity.Resturant;
import com.startup.resturantLitsing.service.ResturantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import java.util.List;

@RestController
@RequestMapping("/v1/resturant")
@CrossOrigin
public class ResturantController {

    @Autowired
    private ResturantService restService;

    @GetMapping("/listAll")
    public ResponseEntity<List<ResturantDto>> getAllResturants ()
    {
        List<ResturantDto> allResturants = restService.findAll();
        return new ResponseEntity<>(allResturants, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Resturant> registerResturant(@RequestBody ResturantDto resturantDto) {
        Resturant resturant = restService.registerResturant(resturantDto);
        return new ResponseEntity<>(resturant,HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Resturant> updateResturant(@PathVariable Integer id, @RequestBody ResturantDto restDto) {
        Resturant resturant = null;
        try {
            resturant = restService.updateResturant(id,restDto);
            return new ResponseEntity<>(resturant,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(resturant,HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/findRestaurant/{id}")
    public ResponseEntity<ResturantDto> fetchResturant(@PathVariable Integer id) {
        ResturantDto resturantDto = null;
        try {
            resturantDto = restService.findResturantById(id);
            return new ResponseEntity<>(resturantDto,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(resturantDto,HttpStatus.NO_CONTENT);
        }

    }
}
