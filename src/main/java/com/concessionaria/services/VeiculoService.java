package com.concessionaria.services;

import com.concessionaria.dtos.VeiculoDTO;
import com.concessionaria.models.Veiculo;
import com.concessionaria.repositories.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public VeiculoDTO salvarVeiculo(VeiculoDTO veiculoDTO) {
        Veiculo veiculo = converterVeiculoDTOParaVeiculo(veiculoDTO);
        veiculo = veiculoRepository.save(veiculo);
        return converterVeiculoParaVeiculoDTO(veiculo);
    }

    private Veiculo converterVeiculoDTOParaVeiculo(VeiculoDTO veiculoDTO) {
        Veiculo veiculo = new Veiculo();
        veiculo.setId(veiculoDTO.getId());
        veiculo.setMarca(veiculoDTO.getMarca());
        veiculo.setAno(veiculoDTO.getAno());
        veiculo.setPreco(veiculoDTO.getPreco());
        veiculo.setCor(veiculoDTO.getCor());
        veiculo.setModelo(veiculoDTO.getModelo());
        return veiculo;
    }

    public VeiculoDTO converterVeiculoParaVeiculoDTO(Veiculo veiculo) {
        VeiculoDTO veiculoDTO = new VeiculoDTO();
        veiculoDTO.setId(veiculo.getId());
        veiculoDTO.setMarca(veiculo.getMarca());
        veiculoDTO.setAno(veiculo.getAno());
        veiculoDTO.setPreco(Double.valueOf(veiculo.getPreco()));
        veiculoDTO.setCor(veiculo.getCor());
        veiculoDTO.setModelo(veiculo.getModelo());
        return veiculoDTO;
    }

    public Veiculo buscarVeiculoPorId(Long id) {
        return veiculoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Veiculo não encontrado"));
    }

    public VeiculoDTO buscarVeiculoPorAno(String ano) {
        return converterVeiculoParaVeiculoDTO(
                veiculoRepository.findByAno(ano).orElseThrow(() ->
                        new IllegalArgumentException("Veiculo não encontrado"))
        );
    }

    public VeiculoDTO atualizarVeiculo(VeiculoDTO veiculoDTO) {
        if (isNull(veiculoDTO.getId())) {
            throw new IllegalArgumentException("Veiculo não encontrado");
        }

        Veiculo veiculoExistente = veiculoRepository.findById(veiculoDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Veiculo não encontrado"));

        // atualiza os campos
        veiculoExistente.setMarca(veiculoDTO.getMarca());
        veiculoExistente.setAno(veiculoDTO.getAno());
        veiculoExistente.setPreco(veiculoDTO.getPreco());
        veiculoExistente.setCor(veiculoDTO.getCor());
        veiculoExistente.setModelo(veiculoDTO.getModelo());

        // salva apenas uma vez
        Veiculo veiculoAtualizado = veiculoRepository.save(veiculoExistente);

        return converterVeiculoParaVeiculoDTO(veiculoAtualizado);
    }

    public void deletarVeiculo(Long id) {
        veiculoRepository.deleteById(id);
    }

    public List<VeiculoDTO> buscarTodosVeiculo() {
        return veiculoRepository.findAll()
                .stream()
                .map(this::converterVeiculoParaVeiculoDTO)
                .toList();
    }
}
