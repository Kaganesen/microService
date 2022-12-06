package com.kodlamaio.filterservice.dataAccess;

import com.kodlamaio.filterservice.entities.Filter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FilterRepository extends MongoRepository<Filter,String> {

    Filter findByCarId(String carId);
    Filter findByPlate(String plate);
    List<Filter> findByBrandName(String brandName);
    List<Filter> findByModelName(String modelName);
    List<Filter> findByBrandId(String brandId);
    List<Filter> findByModelId(String modelId);
    List<Filter> findByDailyPrice(double dailyPrice);
    List<Filter> findByModelYear(int modelYear);
}
