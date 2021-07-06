package com.mercadolibre.projetointegrador.service.impl;

import com.mercadolibre.projetointegrador.exceptions.NotFoundException;
import com.mercadolibre.projetointegrador.model.Supervisor;
import com.mercadolibre.projetointegrador.repository.SupervisorRepository;
import com.mercadolibre.projetointegrador.service.SupervisorService;
import org.springframework.stereotype.Service;

@Service
public class SupervisorServiceImpl implements SupervisorService {

    private final SupervisorRepository supervisorRepository;

    public SupervisorServiceImpl(SupervisorRepository supervisorRepository) {
        this.supervisorRepository = supervisorRepository;
    }

    @Override
    public Supervisor getSupervisor(Long id) {
        return supervisorRepository.findById(id).orElseThrow(() -> new NotFoundException("Supervisor de id: "+id+" não encontrado"));
    }
}
