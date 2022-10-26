package com.crud.producto.viewmodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;


public class ErrorGenerico {

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    public ErrorGenerico code(String code) {
        this.code = code;
        return this;
    }

    public ErrorGenerico(){ }

    public ErrorGenerico jsonParser(String jsonData){
        try	{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonData, ErrorGenerico.class);
        }catch(Exception e){
            return null;
        }
    }


    /**
     * Get code
     * @return code
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ErrorGenerico message(String message) {
        this.message = message;
        return this;
    }

    /**
     * Get message
     * @return message
     */
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ErrorGenerico errorGenerico = (ErrorGenerico) o;
        return Objects.equals(this.code, errorGenerico.code) &&
                Objects.equals(this.message, errorGenerico.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message);
    }
}
