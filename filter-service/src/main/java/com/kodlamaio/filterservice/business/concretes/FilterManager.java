package com.kodlamaio.filterservice.business.concretes;

import com.kodlamaio.common.events.inventory.brand.BrandUpdatedEvent;
import com.kodlamaio.common.events.inventory.car.CarCreatedEvent;
import com.kodlamaio.common.events.inventory.car.CarDeletedEvent;
import com.kodlamaio.common.events.inventory.car.CarUpdatedEvent;
import com.kodlamaio.common.events.inventory.model.ModelUpdatedEvent;
import com.kodlamaio.common.util.mapping.ModelMapperService;
import com.kodlamaio.filterservice.business.abstracts.FilterService;
import com.kodlamaio.filterservice.business.responses.get.GetAllFiltersResponse;
import com.kodlamaio.filterservice.business.responses.get.GetFilterResponse;
import com.kodlamaio.filterservice.dataAccess.FilterRepository;
import com.kodlamaio.filterservice.entities.Filter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FilterManager implements FilterService {

    private FilterRepository filterRepository;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllFiltersResponse> getAll() {

        List<Filter> filters = this.filterRepository.findAll();
        List<GetAllFiltersResponse> responses = filters.stream().map(filter -> this.modelMapperService.forResponse().map(filter, GetAllFiltersResponse.class)).collect(Collectors.toList());

        return responses;
    }

    @Override
    public List<GetAllFiltersResponse> getByBrandName(String brandName) {
        List<Filter> filters = this.filterRepository.findByBrandName(brandName);
        List<GetAllFiltersResponse> responses = filters.stream().map(filter -> this.modelMapperService.forResponse().map(filter, GetAllFiltersResponse.class)).collect(Collectors.toList());

        return responses;
    }

    @Override
    public List<GetAllFiltersResponse> getByModelName(String modelName) {
        List<Filter> filters = this.filterRepository.findByModelName(modelName);
        List<GetAllFiltersResponse> responses = filters.stream().map(filter -> this.modelMapperService.forResponse().map(filter, GetAllFiltersResponse.class)).collect(Collectors.toList());

        return responses;
    }

    @Override
    public GetFilterResponse getByPlate(String plate) {
        Filter filter = this.filterRepository.findByPlate(plate);
        GetFilterResponse response = this.modelMapperService.forResponse().map(filter, GetFilterResponse.class);

        return response;
    }

    @Override
    public List<GetAllFiltersResponse> getByDailyPrice(double min, double max) {
        List<Filter> filters = this.filterRepository.findAll();
        List<GetAllFiltersResponse> responses = new ArrayList<GetAllFiltersResponse>();

        for (Filter filter : filters) {
            if (filter.getDailyPrice() < max && filter.getDailyPrice() > min) {
                GetAllFiltersResponse response = this.modelMapperService.forResponse().map(filter, GetAllFiltersResponse.class);
                responses.add(response);
            }
        }

        return responses;
    }

    @Override
    public List<GetAllFiltersResponse> getByModelYear(int min, int max) {
        List<Filter> filters = this.filterRepository.findAll();
        List<GetAllFiltersResponse> responses = new ArrayList<GetAllFiltersResponse>();

        for (Filter filter : filters) {
            if (filter.getModelYear() < max && filter.getModelYear() > min) {
                GetAllFiltersResponse response = this.modelMapperService.forResponse().map(filter, GetAllFiltersResponse.class);
                responses.add(response);
            }
        }
        return responses;
    }

    @Override
    public void addCar(CarCreatedEvent carCreatedEvent) {
        Filter filter = this.modelMapperService.forRequest().map(carCreatedEvent,Filter.class);
        this.filterRepository.save(filter);

    }

    @Override
    public void deleteCar(CarDeletedEvent carDeletedEvent) {
        Filter filter = this.modelMapperService.forRequest().map(carDeletedEvent, Filter.class);
        this.filterRepository.delete(filter);

    }

    @Override
    public void updateCar(CarUpdatedEvent carUpdatedEvent) {
        Filter filter = this.modelMapperService.forRequest().map(carUpdatedEvent,Filter.class);
        this.filterRepository.save(filter);

    }

    @Override
    public void updateBrand(BrandUpdatedEvent brandUpdatedEvent) {
       List<Filter> filters = this.filterRepository.findByBrandName(brandUpdatedEvent.getBrandName());
       for (Filter filter : filters){
           filter.setBrandName(brandUpdatedEvent.getBrandName());
           filter.setBrandId(brandUpdatedEvent.getBrandId());
           this.filterRepository.save(filter);
       }

    }

    @Override
    public void updateModel(ModelUpdatedEvent modelUpdatedEvent) {
        List<Filter> filters = this.filterRepository.findByModelName(modelUpdatedEvent.getModelName());
        for (Filter filter : filters){
            filter.setModelName(modelUpdatedEvent.getModelName());
            filter.setModelId(modelUpdatedEvent.getModelId());
            filter.setModelYear(filter.getModelYear());
            this.filterRepository.save(filter);
        }

    }
}
