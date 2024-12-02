package com.startup.resturantLitsing.service;

import com.startup.resturantLitsing.dto.ResturantDto;
import com.startup.resturantLitsing.entity.Resturant;
import com.startup.resturantLitsing.mapper.ResturantMapper;
import com.startup.resturantLitsing.repo.ResturantRepo;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RestaurantServiceTest {

    @Mock
    ResturantRepo restaurantRepo;

    @InjectMocks
    ResturantService restaurantService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); //in order for Mock and InjectMocks annotations to take effect, you need to call MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllRestaurants() {
        // Create mock restaurants
        List<Resturant> mockRestaurants = Arrays.asList(
                new Resturant(1, "Restaurant 1", "Address 1", "city 1", "Desc 1"),
                new Resturant(2, "Restaurant 2", "Address 2", "city 2", "Desc 2")
        );
        when(restaurantRepo.findAll()).thenReturn(mockRestaurants);

        // Call the service method
        List<ResturantDto> restaurantDTOList = restaurantService.findAll();

        // Verify the result
        assertEquals(mockRestaurants.size(), restaurantDTOList.size());
        for (int i = 0; i < mockRestaurants.size(); i++) {
            ResturantDto expectedDTO = ResturantMapper.INSTANCE.resturantToResturantDto(mockRestaurants.get(i));
            assertEquals(expectedDTO, restaurantDTOList.get(i));
        }

        // Verify that the repository method was called
        verify(restaurantRepo, times(1)).findAll();
    }

    @Test
    public void testAddRestaurantInDB() {
        // Create a mock restaurant to be saved
        ResturantDto mockRestaurantDTO = new ResturantDto(1, "Restaurant 1", "Address 1", "city 1", "Desc 1");
        Resturant mockRestaurant = ResturantMapper.INSTANCE.resturnatDtoToResturant(mockRestaurantDTO);

        // Mock the repository behavior
        when(restaurantRepo.save(mockRestaurant)).thenReturn(mockRestaurant);

        // Call the service method
        Resturant savedRestaurant = restaurantService.registerResturant(mockRestaurantDTO);

        // Verify the result
        assertEquals(mockRestaurant, savedRestaurant);

        // Verify that the repository method was called
        verify(restaurantRepo, times(1)).save(mockRestaurant);
    }

    @Test
    public void testFetchRestaurantById_ExistingId() {
        // Create a mock restaurant ID
        Integer mockRestaurantId = 1;

        // Create a mock restaurant to be returned by the repository
        Resturant mockRestaurant = new Resturant(1, "Restaurant 1", "Address 1", "city 1", "Desc 1");

        // Mock the repository behavior
        when(restaurantRepo.findById(mockRestaurantId)).thenReturn(Optional.of(mockRestaurant));

        // Call the service method
        ResturantDto response = null;
        try {
            response = restaurantService.findResturantById(mockRestaurantId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Verify the response
        assertEquals(mockRestaurantId, response.getId());

        // Verify that the repository method was called
        verify(restaurantRepo, times(1)).findById(mockRestaurantId);
    }

    @Test
    public void testFetchRestaurantById_NonExistingId() {
        // Create a mock non-existing restaurant ID
        Integer mockRestaurantId = 1;

        // Mock the repository behavior
        when(restaurantRepo.findById(mockRestaurantId)).thenReturn(Optional.empty());

        // Call the service method
        ResturantDto response = null;

        Exception thrown = assertThrows(
                Exception.class,
                () -> restaurantService.findResturantById(mockRestaurantId),
                "Resturant ID not found."
        );

        assertTrue(thrown.getMessage().contains("ID not found"));
        // Verify that the repository method was called
        verify(restaurantRepo, times(1)).findById(mockRestaurantId);
    }



}
