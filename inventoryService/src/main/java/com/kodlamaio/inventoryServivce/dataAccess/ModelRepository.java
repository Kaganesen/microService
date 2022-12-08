package com.kodlamaio.inventoryServivce.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.inventoryServivce.entities.Model;

public interface ModelRepository extends JpaRepository<Model,String> {

}
