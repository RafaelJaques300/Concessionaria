package com.concessionaria.resourcers;

import com.concessionaria.dtos.VeiculoDTO;
import com.concessionaria.models.Veiculo;
import com.concessionaria.services.VeiculoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/veiculo")
public class VeiculoResource {

    private final VeiculoService veiculoService;

    public VeiculoResource(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping("/")
    public ResponseEntity<VeiculoDTO> buscarVeiculo(@PathVariable Long id) {
        Veiculo veiculo = veiculoService.buscarVeiculoPorId(id);
        return ResponseEntity.ok(veiculoService.converterVeiculoParaVeiculoDTO(veiculo));
    }

    @GetMapping("/buscar")
    public ResponseEntity<VeiculoDTO> buscarVeiculoPorAno(@RequestParam String ano) {
        VeiculoDTO veiculoDTO = veiculoService.buscarVeiculoPorAno(ano);
        return ResponseEntity.ok(veiculoDTO);
    }

    @PostMapping()
    public ResponseEntity<VeiculoDTO> criarVeiculo(@RequestBody VeiculoDTO veiculoDTO) {
        return ResponseEntity.ok(veiculoService.salvarVeiculo(veiculoDTO));
    }

    @PutMapping()
    public ResponseEntity<VeiculoDTO> atualizarVeiculo(VeiculoDTO veiculoDTO){
        return ResponseEntity.ok(veiculoService.atualizarVeiculo(veiculoDTO));
    }

    @DeleteMapping()
    public ResponseEntity<Void> deletarVeiculo(@RequestBody VeiculoDTO veiculoDTO){
        veiculoService.deletarVeiculo(veiculoDTO.getId());
        return ResponseEntity.noContent().build();
    }

}
