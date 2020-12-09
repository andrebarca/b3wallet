package com.andrebarca.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.andrebarca.repositories.PapelRepository;
import com.andrebarca.models.Papel;

@Component
@Scope("request")
@FacesConverter("papelConverter")
public class PapelConverter implements Converter {

    @Autowired
    PapelRepository papelRepository;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Long id = Long.getLong(value);
        return papelRepository.findById(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return Long.toString(((Papel) value).getId());
    }    
    
}
