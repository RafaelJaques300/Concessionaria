package com.concessionaria.resourcers;

import com.concessionaria.dtos.VeiculoDTO;
import com.concessionaria.models.Veiculo;
import com.concessionaria.services.VeiculoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/veiculo")
public class VeiculoResource {

    private final VeiculoService veiculoService;

    public VeiculoResource(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> buscarVeiculo(@PathVariable Long id) {
        Veiculo veiculo = veiculoService.buscarVeiculoPorId(id);
        return ResponseEntity.ok(veiculoService.converterVeiculoParaVeiculoDTO(veiculo));
    }

    @GetMapping("/buscar")
    public ResponseEntity<VeiculoDTO> buscarVeiculoPorAno(@RequestParam String ano) {
        VeiculoDTO veiculoDTO = veiculoService.buscarVeiculoPorAno(ano);
        return ResponseEntity.ok(veiculoDTO);
    }

    @PostMapping
    public ResponseEntity<VeiculoDTO> criarVeiculo(@RequestBody VeiculoDTO veiculoDTO) {
        System.out.println(veiculoDTO.getCor());
        System.out.println(veiculoDTO.getAno());
        System.out.println(veiculoDTO.getMarca());
        System.out.println(veiculoDTO.getPreco());
        System.out.println(veiculoDTO.getModelo());
        return ResponseEntity.ok(veiculoService.salvarVeiculo(veiculoDTO));
    }

    @GetMapping
    public ResponseEntity<List<VeiculoDTO>> buscarTodosVeiculo() {
        List<VeiculoDTO> veiculoDTOList = veiculoService.buscarTodosVeiculo();
        return ResponseEntity.ok(veiculoDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoDTO> atualizarVeiculo(@PathVariable Long id,
                                                       @RequestBody VeiculoDTO veiculoDTO) {
        veiculoDTO.setId(id);
        VeiculoDTO atualizado = veiculoService.atualizarVeiculo(veiculoDTO);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVeiculo(@PathVariable Long id) {
        veiculoService.deletarVeiculo(id);
        return ResponseEntity.noContent().build();
    }
}
