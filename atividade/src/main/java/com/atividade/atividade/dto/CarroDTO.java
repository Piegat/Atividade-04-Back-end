package com.atividade.atividade.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CarroDTO {

    private Long id;

    private String modelo;

    private Integer ano;


}
