package com.kodlamaio.inventoryServivce.business.abstracts;

import com.kodlamaio.common.utilities.result.DataResult;
import com.kodlamaio.common.utilities.result.Result;
import com.kodlamaio.inventoryServivce.business.requests.create.CreateCarRequest;
import com.kodlamaio.inventoryServivce.business.requests.update.UpdateCarRequest;
import com.kodlamaio.inventoryServivce.business.responses.create.CreateCarResponse;
import com.kodlamaio.inventoryServivce.business.responses.get.GetCarResponse;
import com.kodlamaio.inventoryServivce.business.responses.getAll.GetAllCarsResponse;
import com.kodlamaio.inventoryServivce.business.responses.update.UpdateCarResponse;

import java.util.List;


public interface CarService {

	DataResult<List<GetAllCarsResponse>> getAll();
	UpdateCarResponse updateCarStateForRental(String carId,int state);


	DataResult<CreateCarResponse> add(CreateCarRequest request);
	DataResult<UpdateCarResponse> update(UpdateCarRequest request, String id);
	Result delete(String id);
	void checkIfCarAvailable(String carId);

	DataResult<GetCarResponse> getById(String id);
}
