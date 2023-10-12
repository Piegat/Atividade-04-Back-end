package com.atividade.atividade.service;

import java.util.ArrayList;
import java.util.List;

import com.atividade.atividade.dto.CarroDTO;
import com.atividade.atividade.dto.PessoaDTO;
import com.atividade.atividade.entity.CarroEntity;
import com.atividade.atividade.entity.PessoaEntity;
import com.atividade.atividade.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public List<CarroDTO> listAll(){
        List<CarroEntity> lista = carroRepository.findAll();
        List<CarroDTO> listaDTO = new ArrayList<>();

        for(int i=0; i<lista.size(); i++)
            listaDTO.add(this.toCarroDTO(lista.get(i)));

        return listaDTO;
    }

    public CarroDTO getById(Long id){
        CarroEntity carro = carroRepository.findById(id).orElse(null);
        return toCarroDTO(carro);
    }

    public CarroDTO save(CarroDTO carroDTO){
        CarroEntity carro = this.toCarro(carroDTO);

        CarroEntity carroSalvo = carroRepository.save(carro);

        return this.toCarroDTO(carroSalvo);
    }

    public CarroDTO edit(CarroDTO carroDTO){
        CarroEntity carro = this.toCarro(carroDTO);
        CarroEntity carroBanco = this.carroRepository.findById(carroDTO.getId()).orElse(null);

        Assert.notNull(carroBanco, "Carro com esse ID não encontrado");

        CarroEntity carroSalvo = carroRepository.save(carro);

        return this.toCarroDTO(carroSalvo);
    }

    public String delete(Long id){
        CarroEntity carroBanco = this.carroRepository.findById(id).orElse(null);

        Assert.notNull(carroBanco, "Carro com esse ID não encontrado");


        carroRepository.deleteById(id);
        return "Deletado com sucesso!";

    }

    private CarroDTO toCarroDTO(CarroEntity carro) {
        CarroDTO carroDTO = new CarroDTO();
        carroDTO.setId(carro.getId());
        carroDTO.setModelo(carro.getModelo());
        carroDTO.setAno(carro.getAno());
        return carroDTO;
    }

    private CarroEntity toCarro(CarroDTO carroDTO) {
        CarroEntity carro = new CarroEntity();
        carro.setId(carroDTO.getId());
        carro.setModelo(carroDTO.getModelo());
        carro.setAno(carroDTO.getAno());
        return carro;
    }
}
