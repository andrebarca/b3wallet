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
@FacesConverter(value="operacaoConverter")
public class OperacaoConverter implements Converter<Operacao> {

    public OperacaoConverter() {
        
    };

    @Autowired
    OperacaoRepository operacaoRepository;

    @Override
    public Operacao getAsObject(FacesContext context, UIComponent component, String value) {
        Long id = Long.getLong(value);
        return operacaoRepository.findById(id).isPresent() ?  operacaoRepository.findById(id).get(): null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Operacao value) {
        return Long.toString(value.getId());
    }    
    
}
