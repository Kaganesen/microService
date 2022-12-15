package com.kodlamaio.inventoryServivce.business.abstracts;

import java.util.List;

import com.kodlamaio.common.utilities.result.DataResult;
import com.kodlamaio.common.utilities.result.Result;
import com.kodlamaio.inventoryServivce.business.requests.create.CreateModelRequest;
import com.kodlamaio.inventoryServivce.business.requests.delete.DeleteModelRequest;
import com.kodlamaio.inventoryServivce.business.requests.update.UpdateModelRequest;
import com.kodlamaio.inventoryServivce.business.responses.create.CreateModelResponse;
import com.kodlamaio.inventoryServivce.business.responses.get.GetModelResponse;
import com.kodlamaio.inventoryServivce.business.responses.getAll.GetAllModelsResponse;
import com.kodlamaio.inventoryServivce.business.responses.update.UpdateModelResponse;

public interface ModelService {

	DataResult<List<GetAllModelsResponse>> getAll();
	DataResult<CreateModelResponse> add(CreateModelRequest createModelRequest);
	DataResult<UpdateModelResponse> update(UpdateModelRequest updateModelRequest);
	Result delete(DeleteModelRequest deleteModelRequest);
	DataResult<GetModelResponse> getById(String id);
}
