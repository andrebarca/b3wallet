package com.andrebarca.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.andrebarca.models.Custodia;
/**
 *
 * @author andre
 */

@Repository
public interface CustodiaRepository extends CrudRepository<Custodia, Long> {
    
    List<Custodia> findAll(Sort sort);

    @Query("select c from Custodia c where c.papel.codigo like %:codigo%")
    List<Custodia> find(@Param("codigo") String query);
    
    @Query("select c from Custodia c where c.papel.codigo = :codigo")
    Custodia findByCodigo(@Param("codigo") String codigo);
}
