package com.startup.resturantLitsing.controller;


import com.startup.resturantLitsing.dto.ResturantDto;
import com.startup.resturantLitsing.entity.Resturant;
import com.startup.resturantLitsing.mapper.ResturantMapper;
import com.startup.resturantLitsing.service.ResturantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RestaurantControllerTest {

    @InjectMocks
    ResturantController restaurantController;

    @Mock
    ResturantService restaurantService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFetchAllRestaurants(){
        // Mock the service behavior
        List<ResturantDto> mockRestaurants = Arrays.asList(
                new ResturantDto(1, "Restaurant 1", "Address 1", "city 1", "Desc 1"),
                new ResturantDto(2, "Restaurant 2", "Address 2", "city 2", "Desc 2")
        );
        when(restaurantService.findAll()).thenReturn(mockRestaurants);

        // Call the controller method
        ResponseEntity<List<ResturantDto>> response = restaurantController.getAllResturants();

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockRestaurants, response.getBody());

        // Verify that the service method was called
        verify(restaurantService, times(1)).findAll();
    }

    @Test
    void testSaveRestaurant() {
        // Create a mock restaurant to be saved
        ResturantDto mockRestaurant =  new ResturantDto(1, "Restaurant 1", "Address 1", "city 1", "Desc 1");
        Resturant resturant = ResturantMapper.INSTANCE.resturnatDtoToResturant(mockRestaurant);
        // Mock the service behavior
        when(restaurantService.registerResturant(mockRestaurant)).thenReturn(resturant);

        // Call the controller method
        ResponseEntity<Resturant> response = restaurantController.registerResturant(mockRestaurant);

        // Verify the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(resturant, response.getBody());

        // Verify that the service method was called
        verify(restaurantService, times(1)).registerResturant(mockRestaurant);
    }

    @Test
    void testFindRestaurantById() {
        // Create a mock restaurant ID
        Integer mockRestaurantId = 1;

        // Create a mock restaurant to be returned by the service
        ResturantDto mockRestaurant = new ResturantDto(1, "Restaurant 1", "Address 1", "city 1", "Desc 1");

        // Mock the service behavior
        try {
            when(restaurantService.findResturantById(mockRestaurantId)).thenReturn(mockRestaurant);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Call the controller method
        ResponseEntity<ResturantDto> response = restaurantController.fetchResturant(mockRestaurantId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockRestaurant, response.getBody());

        // Verify that the service method was called
        try {
            verify(restaurantService, times(1)).findResturantById(mockRestaurantId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
