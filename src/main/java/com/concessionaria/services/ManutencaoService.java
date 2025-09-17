package com.concessionaria.services;

import com.concessionaria.dtos.ManutencaoDTO;
import com.concessionaria.models.Manutencao;
import com.concessionaria.repositories.ManutencaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class ManutencaoService {

    @Autowired
    private ManutencaoRepository manutencaoRepository;
    private Manutencao manutencaoDTO;

    public List<Manutencao> buscarTodosManutencao() {
        return manutencaoRepository.findAll().stream().toList();
    }

    public ManutencaoDTO salvarManutencao(ManutencaoDTO manutencaoDTO) {
        Manutencao manutencao = converterManutencaoDTOParaManutencao(manutencaoDTO);
        manutencao = manutencaoRepository.save(manutencao);
        return converterManutencaoParaManutencaoDTO(manutencao);
    }

    private Manutencao converterManutencaoDTOParaManutencao(ManutencaoDTO manutencaoDTO) {
        Manutencao manutencao = new Manutencao();
        manutencao.setId(manutencaoDTO.getId());
        manutencao.setTipoManutencao(manutencaoDTO.getTipoManutencao());
        manutencao.setDataManutencao(manutencaoDTO.getDataManutencao());
        manutencao.setStatus(manutencaoDTO.getStatus());
        manutencao.setCusto(manutencaoDTO.getCusto());
        return manutencao;
    }

    public ManutencaoDTO converterManutencaoParaManutencaoDTO(Manutencao manutencao) {
        ManutencaoDTO manutencaoDTO = new ManutencaoDTO();
        manutencaoDTO.setId(manutencao.getId());
        manutencaoDTO.setTipoManutencao(manutencao.getTipoManutencao());
        manutencaoDTO.setDataManutencao(manutencao.getDataManutencao());
        manutencaoDTO.setStatus(manutencao.getStatus());
        manutencaoDTO.setCusto(manutencao.getCusto());
        return manutencaoDTO;
    }

    public Manutencao buscarManutencaoPorId(Long id) {
        return manutencaoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Manutencao não encontrado"));
    }

    public ManutencaoDTO buscarManutencaoPorTipoManutencao(String tipoManutencao) {
        return converterManutencaoParaManutencaoDTO(manutencaoRepository.findByTipoManutencao(tipoManutencao).orElseThrow(() ->
                new IllegalArgumentException("Manutencao não encontrado")));
    }

    public ManutencaoDTO atualizarManutencao( ManutencaoDTO manutencaoDTO) {
       // if (isNull(manutencaoDTO.getId())) {
        //    throw new IllegalArgumentException("Manutencao não encontrado sem id");
       // }

        Manutencao manutencao = manutencaoRepository.findById(manutencaoDTO.getId()).orElseThrow(() ->
                new IllegalArgumentException("Manutencao não encontrado sei la oque"));

        Manutencao manutencaoAtualizado = converterManutencaoDTOParaManutencao(manutencaoDTO);
        manutencaoRepository.save(manutencaoAtualizado);

        return converterManutencaoParaManutencaoDTO(manutencao);
    }

    public void deletarManutencao(Long id) {
        manutencaoRepository.deleteById(id);
    }
}
