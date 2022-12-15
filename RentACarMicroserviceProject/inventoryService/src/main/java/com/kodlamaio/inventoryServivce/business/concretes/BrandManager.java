package com.kodlamaio.inventoryServivce.business.concretes;

import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.common.utilities.messages.BusinessMessage;
import com.kodlamaio.common.utilities.result.DataResult;
import com.kodlamaio.common.utilities.result.Result;
import com.kodlamaio.common.utilities.result.SuccessDataResult;
import com.kodlamaio.common.utilities.result.SuccessResult;
import com.kodlamaio.inventoryServivce.business.abstracts.BrandService;
import com.kodlamaio.inventoryServivce.business.requests.create.CreateBrandRequest;
import com.kodlamaio.inventoryServivce.business.requests.update.UpdateBrandRequest;
import com.kodlamaio.inventoryServivce.business.responses.create.CreateBrandResponse;
import com.kodlamaio.inventoryServivce.business.responses.get.GetBrandResponse;
import com.kodlamaio.inventoryServivce.business.responses.getAll.GetAllBrandResponse;
import com.kodlamaio.inventoryServivce.business.responses.update.UpdateBrandResponse;
import com.kodlamaio.inventoryServivce.dataAccess.BrandRepository;
import com.kodlamaio.inventoryServivce.entities.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {

    private BrandRepository brandRepository;
    private ModelMapperService modelMapperService;



	@Override
	public DataResult<List<GetAllBrandResponse>> getAll() {
		List<Brand> brands = brandRepository.findAll();
		List<GetAllBrandResponse> responses = brands.stream()
				.map(brand -> modelMapperService.forResponse().map(brand, GetAllBrandResponse.class)).toList();
		return new SuccessDataResult<>(responses, BusinessMessage.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
	}

	@Override
	public DataResult<CreateBrandResponse> add(CreateBrandRequest createBrandRequest) {
		Brand brand = modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		brand.setId(UUID.randomUUID().toString());
		brandRepository.save(brand);
		CreateBrandResponse createBrandResponse = modelMapperService.forResponse().map(brand, CreateBrandResponse.class);
		return new SuccessDataResult<>(createBrandResponse, BusinessMessage.GlobalMessages.DATA_ADDED_SUCCESSFULLY);
	}

	@Override
	public DataResult<List<GetBrandResponse>> getByName(String name) {
		checkIfExistsByName(name);
		List<Brand> brands = brandRepository.getByName(name);
		List<GetBrandResponse> responses = brands.stream()
				.map(brand -> modelMapperService.forResponse().map(brand, GetBrandResponse.class)).toList();
		return new SuccessDataResult<>(responses, BusinessMessage.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
	}

	@Override
	public DataResult<UpdateBrandResponse> update(UpdateBrandRequest updateBrandRequest) {
		checkIfExistsById(updateBrandRequest.getId());
		Brand brand = modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		brandRepository.save(brand);
		UpdateBrandResponse response = modelMapperService.forResponse().map(brand, UpdateBrandResponse.class);
		return new SuccessDataResult<>(response, BusinessMessage.GlobalMessages.DATA_UPDATED_SUCCESSFULLY);
	}

	@Override
	public Result delete(String id) {
		checkIfExistsById(id);
		Brand brand = this.brandRepository.findById(id).get();
		brandRepository.delete(brand);
		return new SuccessResult(BusinessMessage.GlobalMessages.DATA_DELETED_SUCCESSFULLY);
	}

	@Override
	public DataResult<GetBrandResponse> getById(String id) {
		checkIfExistsById(id);
		Brand brand = this.brandRepository.findById(id).get();
		GetBrandResponse getBrandResponse = this.modelMapperService.forResponse().map(brand, GetBrandResponse.class);
		return  new SuccessDataResult<>(getBrandResponse,BusinessMessage.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
	}

	private void checkIfExistsById(String id){
		if (!this.brandRepository.existsById(id)){
			throw new BusinessException(BusinessMessage.GlobalMessages.ID_NOT_FOUND+id);
		}
	}
	private void checkIfExistsByName(String name){
		if (!this.brandRepository.existsByName(name)){
			throw new BusinessException(BusinessMessage.GlobalMessages.ID_NOT_FOUND+name);
		}
	}
}
