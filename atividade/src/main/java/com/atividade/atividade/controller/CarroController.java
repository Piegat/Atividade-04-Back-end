package com.atividade.atividade.controller;

import java.util.List;

import com.atividade.atividade.dto.CarroDTO;
import com.atividade.atividade.dto.LivroDTO;
import com.atividade.atividade.dto.PessoaDTO;
import com.atividade.atividade.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carro")
@CrossOrigin(origins = "http://localhost:4200")
public class CarroController {

    @Autowired
    private CarroService service;

    @GetMapping("/getAll")
    private ResponseEntity<List<CarroDTO>> listAll() {
        try {
            List<CarroDTO> lista = service.listAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity<CarroDTO> getById(@PathVariable("id") Long id) {
        try {
            CarroDTO carroDTO = service.getById(id);
            return new ResponseEntity<>(carroDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    private ResponseEntity<CarroDTO> save(@RequestBody CarroDTO carroDTO) {
        try {
            CarroDTO carroSalvo = service.save(carroDTO);
            return new ResponseEntity<>(carroSalvo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    private ResponseEntity<CarroDTO> edit(@RequestBody CarroDTO carroDTO){
        try {
            CarroDTO carroSalvo = service.edit(carroDTO);
            return new ResponseEntity<>(carroSalvo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<HttpStatus> delete(@PathVariable("id")Long id){
        try {
            String response = service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    }
