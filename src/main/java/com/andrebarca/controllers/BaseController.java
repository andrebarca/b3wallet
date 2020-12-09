
package com.andrebarca.controllers;

import java.io.Serializable;

public interface BaseController extends Serializable {

    String index();
    
    String save();

    String add();

    String edit(long id);

    void delete(long id);

    Iterable list();

    Iterable find(String query);

}