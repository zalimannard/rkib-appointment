package ru.zalimannard.bachelorthesisserver.service;

import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
    private final ServiceMapper serviceMapper;
    private final ServiceRepository serviceRepository;

    public ServiceServiceImpl(ServiceMapper serviceMapper, ServiceRepository serviceRepository) {
        this.serviceMapper = serviceMapper;
        this.serviceRepository = serviceRepository;
    }

    @Override
    public ServiceDto get(int id) {
        Optional<Service> serviceOptional = serviceRepository.findById(id);
        if (serviceOptional.isPresent()) {
            Service service = serviceOptional.get();
            return serviceMapper.toDto(service);
        } else {
            throw new NotFoundException("Услуга с id=" + id + " не найдена");
        }
    }

    @Override
    public List<ServiceDto> getAll() {
        Iterable<Service> services = serviceRepository.findAll();
        List<ServiceDto> serviceDtos = new ArrayList<>();
        services.forEach(service -> serviceDtos.add(serviceMapper.toDto(service)));
        return serviceDtos;
    }
}
