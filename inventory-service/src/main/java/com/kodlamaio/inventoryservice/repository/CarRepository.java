package com.kodlamaio.inventoryservice.repository;

import com.kodlamaio.inventoryservice.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, String> {
    boolean existsByPlateIgnoreCase(String plate);
}
