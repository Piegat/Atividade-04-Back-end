package com.atividade.atividade.service;

import java.util.ArrayList;
import java.util.List;

import com.atividade.atividade.dto.PessoaDTO;
import com.atividade.atividade.entity.PessoaEntity;
import com.atividade.atividade.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<PessoaDTO> listAll(){
        List<PessoaEntity> lista = pessoaRepository.findAll();
        List<PessoaDTO> listaDTO = new ArrayList<>();

        for(int i=0; i<lista.size(); i++)
            listaDTO.add(this.toPessoaDTO(lista.get(i)));

        return listaDTO;
    }

    public PessoaDTO getById(Long id){
        PessoaEntity pessoa = pessoaRepository.findById(id).orElse(null);
        return toPessoaDTO(pessoa);
    }

    public PessoaDTO save(PessoaDTO pessoaDTO){
        PessoaEntity pessoa = this.toPessoa(pessoaDTO);

        PessoaEntity pessoasalva = pessoaRepository.save(pessoa);

        return this.toPessoaDTO(pessoasalva);
    }

    private PessoaDTO toPessoaDTO(PessoaEntity pessoa) {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(pessoa.getId());
        pessoaDTO.setNome(pessoa.getNome());
        pessoaDTO.setIdade(pessoa.getIdade());
        return pessoaDTO;
    }

    private PessoaEntity toPessoa(PessoaDTO pessoaDTO) {
        PessoaEntity pessoa = new PessoaEntity();
        pessoa.setId(pessoaDTO.getId());
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setIdade(pessoaDTO.getIdade());
        return pessoa;
    }
}
