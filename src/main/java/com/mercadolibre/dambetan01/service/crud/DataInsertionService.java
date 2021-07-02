package com.mercadolibre.dambetan01.service.crud;

import com.mercadolibre.dambetan01.model.*;
import com.mercadolibre.dambetan01.repository.*;
import org.springframework.stereotype.Service;

@Service
public class DataInsertionService {

    final BatchItemRepository batchItemRepository;
    final EmployeeRepository employeeRepository;
    final InboundOrderRepository inboundOrderRepository;
    final ProductRepository productRepository;
    final SectionRepository sectionRepository;
    final SellerRepository sellerRepository;
    final SupervisorRepository supervisorRepository;
    final UserRepository userRepository;
    final WarehouseRepository warehouseRepository;


    public DataInsertionService(BatchItemRepository batchItemRepository,
                                EmployeeRepository employeeRepository,
                                InboundOrderRepository inboundOrderRepository,
                                ProductRepository productRepository,
                                SectionRepository sectionRepository,
                                SellerRepository sellerRepository,
                                SupervisorRepository supervisorRepository,
                                UserRepository userRepository,
                                WarehouseRepository warehouseRepository) {
        this.batchItemRepository = batchItemRepository;
        this.employeeRepository = employeeRepository;
        this.inboundOrderRepository = inboundOrderRepository;
        this.productRepository = productRepository;
        this.sectionRepository = sectionRepository;
        this.sellerRepository = sellerRepository;
        this.supervisorRepository = supervisorRepository;
        this.userRepository = userRepository;
        this.warehouseRepository = warehouseRepository;
    }

    public void createWarehouse(Warehouse warehouse){
        warehouseRepository.save(warehouse);
    }

    public void createSupervisor(Supervisor supervisor){
        supervisorRepository.save(supervisor);
    }

    public void createSeller(Seller seller){
        sellerRepository.save(seller);
    }

    public void createSection(Section section){
        sectionRepository.save(section);
    }

    public void createProduct(Product product){
        productRepository.save(product);
    }

    public void createOrder(InboundOrder inboundOrder){
        inboundOrderRepository.save(inboundOrder);
    }

    public void createEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    public void createBatchItem(BatchItem batchItem){
        batchItemRepository.save(batchItem);
    }

}