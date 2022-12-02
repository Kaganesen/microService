package com.kodlamaio.inventoryservice.business.concretes;

import com.kodlamaio.common.util.exceptions.BusinessException;
import com.kodlamaio.common.util.mapping.ModelMapperService;
import com.kodlamaio.inventoryservice.business.abstracts.CarService;
import com.kodlamaio.inventoryservice.business.dto.requests.create.CreateCarRequest;
import com.kodlamaio.inventoryservice.business.dto.requests.update.UpdateCarRequest;
import com.kodlamaio.inventoryservice.business.dto.responses.create.CreateCarResponse;
import com.kodlamaio.inventoryservice.business.dto.responses.get.GetAllCarsResponse;
import com.kodlamaio.inventoryservice.business.dto.responses.get.GetCarResponse;
import com.kodlamaio.inventoryservice.business.dto.responses.update.UpdateCarResponse;
import com.kodlamaio.inventoryservice.entities.Car;
import com.kodlamaio.inventoryservice.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final CarRepository repository;
    private final ModelMapperService mapper;

    @Override
    public List<GetAllCarsResponse> getAll() {
        List<Car> cars = repository.findAll();
        List<GetAllCarsResponse> response = cars
                .stream()
                .map(car -> mapper.forResponse().map(car, GetAllCarsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetCarResponse getById(String id) {
        checkIfCarExistsById(id);
        Car car = repository.findById(id).orElseThrow();
        GetCarResponse response = mapper.forResponse().map(car, GetCarResponse.class);

        return response;
    }

    @Override
    public CreateCarResponse add(CreateCarRequest request) {
        checkIfCarExistsByPlate(request.getPlate());
        Car car = mapper.forRequest().map(request, Car.class);
        car.setId(UUID.randomUUID().toString());
        repository.save(car);
        CreateCarResponse response = mapper.forResponse().map(car, CreateCarResponse.class);

        return response;
    }

    @Override
    public UpdateCarResponse update(UpdateCarRequest request, String id) {
        checkIfCarExistsById(id);
        checkIfCarExistsByPlate(request.getPlate());
        Car car = mapper.forRequest().map(request, Car.class);
        car.setId(id);
        repository.save(car);
        UpdateCarResponse response = mapper.forResponse().map(car, UpdateCarResponse.class);

        return response;
    }

    @Override
    public void delete(String id) {
        checkIfCarExistsById(id);
        repository.deleteById(id);
    }

    @Override
    public void updateCarState(String carId, int state) {


    }

    @Override
    public void checkIfCarAvaible(String id) {
        Car car = this.repository.findById(id).get();
        if (car.getState() !=1){
            throw new BusinessException("CAR.NOT_AVAILABLE");
        }

    }

    private void checkIfCarExistsById(String id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("CAR.NOT_EXISTS");
        }
    }

    private void checkIfCarExistsByPlate(String plate) {
        if (repository.existsByPlateIgnoreCase(plate)) {
            throw new BusinessException("CAR.ALREADY_EXISTS");
        }
    }
}
