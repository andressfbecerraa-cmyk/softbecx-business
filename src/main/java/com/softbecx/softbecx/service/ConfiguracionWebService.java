package com.softbecx.softbecx.service;

import com.softbecx.softbecx.model.ConfiguracionWeb;
import com.softbecx.softbecx.repository.ConfiguracionWebRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConfiguracionWebService {

    private final ConfiguracionWebRepository configuracionWebRepository;

    public ConfiguracionWebService(
            ConfiguracionWebRepository configuracionWebRepository) {

        this.configuracionWebRepository = configuracionWebRepository;
    }

    public List<ConfiguracionWeb> listarTodas() {
        return configuracionWebRepository.findAll();
    }

    public Optional<ConfiguracionWeb> buscarPorId(Long id) {
        return configuracionWebRepository.findById(id);
    }

    public ConfiguracionWeb guardar(ConfiguracionWeb configuracionWeb) {
        return configuracionWebRepository.save(configuracionWeb);
    }

    public void eliminar(Long id) {
        configuracionWebRepository.deleteById(id);
    }
}