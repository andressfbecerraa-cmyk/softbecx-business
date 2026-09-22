package com.softbecx.softbecx.controller;

import com.softbecx.softbecx.model.ConfiguracionWeb;
import com.softbecx.softbecx.service.ConfiguracionWebService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/configuracion-web")
public class ConfiguracionWebController {

    private final ConfiguracionWebService configuracionWebService;

    public ConfiguracionWebController(
            ConfiguracionWebService configuracionWebService) {

        this.configuracionWebService = configuracionWebService;
    }

    @GetMapping
    public List<ConfiguracionWeb> listarConfiguraciones() {
        return configuracionWebService.listarTodas();
    }

    @GetMapping("/{id}")
    public ConfiguracionWeb buscarPorId(@PathVariable Long id) {

        return configuracionWebService
                .buscarPorId(id)
                .orElse(null);
    }

    @PostMapping
    public ConfiguracionWeb guardar(
            @RequestBody ConfiguracionWeb configuracionWeb) {

        return configuracionWebService.guardar(
                configuracionWeb
        );
    }

    @PutMapping("/{id}")
    public ConfiguracionWeb actualizar(
            @PathVariable Long id,
            @RequestBody ConfiguracionWeb configuracionWeb) {

        ConfiguracionWeb existente =
                configuracionWebService
                        .buscarPorId(id)
                        .orElse(null);

        if (existente == null) {
            return null;
        }

        existente.setNombreEmpresa(
                configuracionWeb.getNombreEmpresa()
        );

        existente.setDescripcion(
                configuracionWeb.getDescripcion()
        );

        existente.setLogo(
                configuracionWeb.getLogo()
        );

        existente.setBanner(
                configuracionWeb.getBanner()
        );

        existente.setTextoInicio(
                configuracionWeb.getTextoInicio()
        );

        existente.setTelefono(
                configuracionWeb.getTelefono()
        );

        existente.setCorreo(
                configuracionWeb.getCorreo()
        );

        existente.setDireccion(
                configuracionWeb.getDireccion()
        );

        existente.setFacebook(
                configuracionWeb.getFacebook()
        );

        existente.setInstagram(
                configuracionWeb.getInstagram()
        );

        existente.setWhatsapp(
                configuracionWeb.getWhatsapp()
        );

        return configuracionWebService.guardar(
                existente
        );
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        configuracionWebService.eliminar(id);
    }
}