package com.andrebarca.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Messenger {
    public static void showInfo(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "info", message));
    }

    public static void showWarning(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "warn", message));
    }    

    public static void showError(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "error", message));
    }
}
