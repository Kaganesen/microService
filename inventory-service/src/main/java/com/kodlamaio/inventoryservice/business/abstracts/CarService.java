package com.kodlamaio.inventoryservice.business.abstracts;

import com.kodlamaio.inventoryservice.business.dto.requests.create.CreateCarRequest;
import com.kodlamaio.inventoryservice.business.dto.requests.update.UpdateCarRequest;
import com.kodlamaio.inventoryservice.business.dto.responses.create.CreateCarResponse;
import com.kodlamaio.inventoryservice.business.dto.responses.get.GetAllCarsResponse;
import com.kodlamaio.inventoryservice.business.dto.responses.get.GetCarResponse;
import com.kodlamaio.inventoryservice.business.dto.responses.update.UpdateCarResponse;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CarService {
    List<GetAllCarsResponse> getAll();
    GetCarResponse getById(String id);
    CreateCarResponse add(CreateCarRequest request);
    UpdateCarResponse update(UpdateCarRequest request, String id);
    void delete(String id);

    void updateCarState(String carId,int state);

    void checkIfCarAvaible (@PathVariable String id);


}

