package com.kodlamaio.inventoryServivce.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.inventoryServivce.entities.Car;

public interface CarRespository extends JpaRepository<Car,String>{

}
