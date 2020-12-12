package com.andrebarca.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.andrebarca.repositories.OperacaoRepository;
import com.andrebarca.models.Operacao;

@Component
@Scope("request")
@FacesConverter(forClass=Operacao.class, value="operacaoConverter")
public class OperacaoConverter implements Converter {

    public OperacaoConverter() {
        
    };

    @Autowired
    OperacaoRepository operacaoRepository;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Long id = Long.getLong(value);
        return operacaoRepository.findById(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return Long.toString(((Operacao) value).getId());
    }    
    
}
