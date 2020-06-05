package com.fiiss.operationslist.entities;

import java.io.Serializable;

public class ParameterFirebase implements Serializable {

    private String operation;
    private String parameterOne;
    private String parameterTwo;
    private String result;


    public ParameterFirebase() { }

    public ParameterFirebase(String operation, String parameterOne, String parameterTwo, String result) {
        this.operation = operation;
        this.parameterOne = parameterOne;
        this.parameterTwo = parameterTwo;
        this.result = result;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getParameterOne() {
        return parameterOne;
    }

    public void setParameterOne(String parameterOne) {
        this.parameterOne = parameterOne;
    }

    public String getParameterTwo() {
        return parameterTwo;
    }

    public void setParameterTwo(String parameterTwo) {
        this.parameterTwo = parameterTwo;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
