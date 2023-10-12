package com.atividade.atividade.service;

import java.util.ArrayList;
import java.util.List;

import com.atividade.atividade.dto.LivroDTO;
import com.atividade.atividade.entity.LivroEntity;
import com.atividade.atividade.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<LivroDTO> listAll(){
        List<LivroEntity> lista = livroRepository.findAll();
        List<LivroDTO> listaDTO = new ArrayList<>();

        for(int i=0; i<lista.size(); i++)
            listaDTO.add(this.toLivroDTO(lista.get(i)));

        return listaDTO;
    }

    public LivroDTO getById(Long id){
        LivroEntity livro = livroRepository.findById(id).orElse(null);
        return toLivroDTO(livro);
    }

    public LivroDTO save(LivroDTO livroDTO){
        LivroEntity livro = this.toLivro(livroDTO);

        LivroEntity livroSalvo = livroRepository.save(livro);

        return this.toLivroDTO(livroSalvo);
    }

    public LivroDTO edit(LivroDTO livroDTO){
        LivroEntity livro = this.toLivro(livroDTO);
        LivroEntity livroBanco = this.livroRepository.findById(livroDTO.getId()).orElse(null);

        Assert.notNull(livroBanco, "livro com esse ID não encontrado");

        LivroEntity livroSalvo = livroRepository.save(livro);

        return this.toLivroDTO(livroSalvo);
    }

    public String delete(Long id){
        LivroEntity livroBanco = this.livroRepository.findById(id).orElse(null);

        Assert.notNull(livroBanco, "livro com esse ID não encontrado");


        livroRepository.deleteById(id);
        return "Deletado com sucesso!";

    }


    private LivroDTO toLivroDTO(LivroEntity livro) {
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setId(livro.getId());
        livroDTO.setTitulo(livro.getTitulo());
        livroDTO.setAutor(livro.getAutor());
        return livroDTO;
    }

    private LivroEntity toLivro(LivroDTO livroDTO) {
        LivroEntity livro = new LivroEntity();
        livro.setId(livroDTO.getId());
        livro.setTitulo(livroDTO.getTitulo());
        livro.setAutor(livroDTO.getAutor());
        return livro;
    }

}
