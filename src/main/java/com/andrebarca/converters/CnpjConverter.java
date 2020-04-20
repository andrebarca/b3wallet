package com.andrebarca.converters;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Component
@Scope("request")
@FacesConverter("cnpjConverter")
public class CnpjConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value.replaceAll("\\.|/|-","");
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString().replaceAll("(.{2})(.{3})(.{3})(.{4})(.{2})", "$1.$2.$3/$4-$5");
    }
}
