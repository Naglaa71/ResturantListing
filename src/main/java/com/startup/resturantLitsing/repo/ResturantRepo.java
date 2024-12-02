package com.startup.resturantLitsing.repo;

import com.startup.resturantLitsing.entity.Resturant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResturantRepo extends JpaRepository<Resturant, Integer> {
}
