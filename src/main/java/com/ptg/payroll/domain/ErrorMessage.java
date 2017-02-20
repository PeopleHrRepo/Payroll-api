package com.ptg.payroll.domain;
public class ErrorMessage {
    private String code;
    private String message;
    private String description;

    /* Auto gen code */

    public ErrorMessage(){
        
    }
    public ErrorMessage(String message){
        this.message = message;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}