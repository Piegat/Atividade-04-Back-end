package com.atividade.atividade.controller;


import java.util.List;

import com.atividade.atividade.dto.LivroDTO;
import com.atividade.atividade.dto.PessoaDTO;
import com.atividade.atividade.service.PessoaService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/pessoa")
@CrossOrigin(origins = "http://localhost:4200")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/getAll")
    private ResponseEntity<List<PessoaDTO>> listAll(){
        try {
            List<PessoaDTO> lista = pessoaService.listAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity<PessoaDTO> getById(@PathVariable("id") Long id){
        try {
            PessoaDTO pessoaDTO = pessoaService.getById(id);
            return new ResponseEntity<>(pessoaDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping
    private ResponseEntity<PessoaDTO> save(@RequestBody PessoaDTO pessoaDTO){
        try {
            PessoaDTO pessoaSalva = pessoaService.save(pessoaDTO);
            return new ResponseEntity<>(pessoaSalva, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    private ResponseEntity<PessoaDTO> edit(@RequestBody PessoaDTO pessoaDTO){
        try {
            PessoaDTO pessoaSalva = pessoaService.edit(pessoaDTO);
            return new ResponseEntity<>(pessoaSalva, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<HttpStatus> delete(@PathVariable("id")Long id){
        try {
            String response = pessoaService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }}
