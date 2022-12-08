package com.kodlamaio.inventoryServivce.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.inventoryServivce.entities.Brand;

public interface BrandRepository extends JpaRepository<Brand, String> {

	List<Brand> getByName(String name);
	boolean existsByName(String name);
}
