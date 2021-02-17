package com.andrebarca.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.andrebarca.models.Empresa;
import com.andrebarca.repositories.EmpresaRepository;

@Component
@FacesConverter(value="empresaConverter")
public class EmpresaConverter implements Converter<Empresa> {

    @Autowired
    EmpresaRepository empresaRepository;

    @Override
    public Empresa getAsObject(FacesContext context, UIComponent component, String value) {
        Empresa empresa = empresaRepository.findByCnpj(value);
        return empresa;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Empresa value) {
        return value != null? value.getCnpj(): null;
    }    
    
}
