package com.concessionaria.services;

import com.concessionaria.dtos.EstoqueDTO;
import com.concessionaria.models.Estoque;
import com.concessionaria.repositories.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;
    private Estoque estoqueDTO;

    public EstoqueDTO salvarEstoque(EstoqueDTO estoqueDTO) {
        Estoque estoque = converterEstoqueDTOParaEstoque(estoqueDTO);
        estoque = estoqueRepository.save(estoque);
        return converterEstoqueParaEstoqueDTO(estoque);
    }

    private Estoque converterEstoqueDTOParaEstoque(EstoqueDTO estoqueDTO) {
        Estoque estoque = new Estoque();
        estoque.setId(estoqueDTO.getId());
        estoque.setQuantidadeDisponivel(estoqueDTO.getQuantidadeDisponivel());
        return estoque;
    }

    public EstoqueDTO converterEstoqueParaEstoqueDTO(Estoque estoque) {
        EstoqueDTO estoqueDTO = new EstoqueDTO();
        estoqueDTO.setId(estoque.getId());
        estoqueDTO.setQuantidadeDisponivel(estoque.getQuantidadeDisponivel());
        return estoqueDTO;
    }

    public Estoque buscarEstoquePorId(Long id) {
        return estoqueRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Estoque não encontrado"));
    }

    public EstoqueDTO buscarEstoquePorQuantidadeDisponivel(Double quantidadeDisponivel) {
        return converterEstoqueParaEstoqueDTO(estoqueRepository.findByQuantidadeDisponivel(quantidadeDisponivel).orElseThrow(() ->
                new IllegalArgumentException("Estoque não encontrado")));
    }

    public EstoqueDTO atualizarEstoque(EstoqueDTO estoqueDTO) {
        if (estoqueDTO.getId() == null) {
            throw new IllegalArgumentException("Estoque não encontrado");
        }

        Estoque estoque = estoqueRepository.findById(estoqueDTO.getId()).orElseThrow(() ->
                new IllegalArgumentException("Estoque não encontrado"));

        Estoque estoqueAtualizado = converterEstoqueDTOParaEstoque(estoqueDTO);
        estoqueRepository.save(estoqueAtualizado);

        return converterEstoqueParaEstoqueDTO(estoque);
    }

    public void deletarEstoque(Long id) {
        estoqueRepository.deleteById(id);
    }

    public List<EstoqueDTO> buscarTodosEstoque() {
        return estoqueRepository.findAll().stream().map(estoque -> converterEstoqueParaEstoqueDTO(estoque)).toList();

    }
}
