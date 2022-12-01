package com.kodlamaio.rentalservice.repository;

import com.kodlamaio.rentalservice.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental,String> {
}
