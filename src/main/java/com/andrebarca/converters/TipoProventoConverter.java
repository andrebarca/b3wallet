package com.andrebarca.converters;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.andrebarca.models.TipoProvento;

@Component
@Scope("request")
@FacesConverter(value="tipoProventoConverter")
public class TipoProventoConverter implements Converter<TipoProvento> {

    @Override
    public TipoProvento getAsObject(FacesContext context, UIComponent component, String value) {
        return TipoProvento.valueOf(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, TipoProvento value) {
        return value != null ? value.getTipo() : null;
    }
}
