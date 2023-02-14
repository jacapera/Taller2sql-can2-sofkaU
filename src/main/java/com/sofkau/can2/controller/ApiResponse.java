package com.sofkau.can2.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor //genera constructor sin argumentos
@AllArgsConstructor //genera constructor con todos los argumentos
public class ApiResponse {

    private boolean success;
    private String message;
    private Object data;

}
