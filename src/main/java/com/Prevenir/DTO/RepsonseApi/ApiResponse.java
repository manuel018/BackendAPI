package com.Prevenir.DTO.RepsonseApi;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)//Valores null en json se omitiran
public class ApiResponse<T> {

    private String message;
    private int status;
    private T data;

}
