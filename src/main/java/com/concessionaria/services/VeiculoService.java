package com.concessionaria.services;

import com.concessionaria.dtos.VeiculoDTO;
import com.concessionaria.models.Veiculo;
import com.concessionaria.repositories.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;
    private Veiculo veiculoDTO;

    public VeiculoDTO salvarVeiculo(VeiculoDTO veiculoDTO) {
        Veiculo veiculo = converterVeiculoDTOParaVeiculo(veiculoDTO);
        veiculo = veiculoRepository.save(veiculo);
        return converterVeiculoParaVeiculoDTO(veiculo);
    }

    private Veiculo converterVeiculoDTOParaVeiculo(VeiculoDTO veiculoDTO) {
        Veiculo veiculo = new Veiculo();
        veiculoDTO.setId(veiculoDTO.getId());
        veiculoDTO.setMarca(veiculoDTO.getMarca());
        veiculoDTO.setAno(veiculoDTO.getAno());
        veiculoDTO.setPreco(veiculoDTO.getPreco());
        veiculoDTO.setCor(veiculoDTO.getCor());
        return veiculo;
    }

    public VeiculoDTO converterVeiculoParaVeiculoDTO(Veiculo veiculo) {
        VeiculoDTO veiculoDTO = new VeiculoDTO();
        veiculoDTO.setId(veiculo.getId());
        veiculoDTO.setMarca(veiculo.getMarca());
        veiculoDTO.setAno(veiculo.getAno());
        veiculoDTO.setPreco(veiculo.getPreco());
        veiculoDTO.setCor(veiculo.getCor());
        return veiculoDTO;
    }

    public Veiculo buscarVeiculoPorId(Long id) {
        return veiculoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Veiculo não encontrado"));
    }

    public VeiculoDTO buscarVeiculoPorAno(String ano) {
        return converterVeiculoParaVeiculoDTO(veiculoRepository.findByAno(ano).orElseThrow(() ->
                new IllegalArgumentException("Veiculo não encontrado")));
    }

    public VeiculoDTO atualizarVeiculo(VeiculoDTO veiculoDTO) {
        if (isNull(veiculoDTO.getId())) {
            throw new IllegalArgumentException("Veiculo não encontrado");
        }

        Veiculo veiculo = veiculoRepository.findById(veiculoDTO.getId()).orElseThrow(() ->
                new IllegalArgumentException("Veiculo não encontrado"));

        Veiculo veiculoAtualizado = converterVeiculoDTOParaVeiculo(veiculoDTO);
        veiculoRepository.save(veiculoAtualizado);

        veiculo = converterVeiculoDTOParaVeiculo(veiculoDTO);
        veiculo = veiculoRepository.save(veiculo);

        return converterVeiculoParaVeiculoDTO(veiculo);
    }

    public void deletarVeiculo(Long id) {
        veiculoRepository.deleteById(id);
    }
}
