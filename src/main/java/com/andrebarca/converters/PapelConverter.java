package com.andrebarca.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.andrebarca.models.Papel;
import com.andrebarca.repositories.PapelRepository;

@Component
@FacesConverter(value="papelConverter")
public class PapelConverter implements Converter<Papel> {

    @Autowired
    PapelRepository papelRepository;

    @Override
    public Papel getAsObject(FacesContext context, UIComponent component, String value) {
        Papel papel = papelRepository.findByCodigo(value);
        return papel;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Papel value) {
        return value != null? value.getCodigo(): null;
    }    
    
}
