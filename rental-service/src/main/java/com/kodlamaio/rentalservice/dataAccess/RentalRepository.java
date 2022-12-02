package com.kodlamaio.rentalservice.dataAccess;

import com.kodlamaio.rentalservice.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental,String> {
    Rental findByCarId(String carId);
}
