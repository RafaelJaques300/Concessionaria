package com.concessionaria.resourcers;

import com.concessionaria.dtos.ManutencaoDTO;
import com.concessionaria.models.Manutencao;
import com.concessionaria.services.ManutencaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5500/")
@RestController
@RequestMapping("/api/manutencao")
public class ManutencaoResource {

    private final ManutencaoService manutencaoService;

    public ManutencaoResource(ManutencaoService manutencaoService) {
        this.manutencaoService = manutencaoService;
    }

    @GetMapping
    public ResponseEntity<List<Manutencao>> buscarTodosManutencao() {
        List<Manutencao> manutencaoDTOList = manutencaoService.buscarTodosManutencao();
        return ResponseEntity.ok(manutencaoDTOList);
    }

    @GetMapping("/")
    public ResponseEntity<ManutencaoDTO> buscarManutencao(@PathVariable Long id) {
        Manutencao manutencao = manutencaoService.buscarManutencaoPorId(id);
        return ResponseEntity.ok(manutencaoService.converterManutencaoParaManutencaoDTO(manutencao));
    }

    @GetMapping("/buscar")
    public ResponseEntity<ManutencaoDTO> buscarManutencaoPorTipoManutencao(@RequestParam String tipoManutencao) {
        ManutencaoDTO manutencaoDTO = manutencaoService.buscarManutencaoPorTipoManutencao(tipoManutencao);
        return ResponseEntity.ok(manutencaoDTO);
    }

    @PostMapping()
    public ResponseEntity<ManutencaoDTO> criarManutencao(@RequestBody ManutencaoDTO manutencaoDTO) {
        return ResponseEntity.ok(manutencaoService.salvarManutencao(manutencaoDTO));
    }

    @PutMapping()
    public ResponseEntity<ManutencaoDTO> atualizarManutencao(@RequestBody ManutencaoDTO manutencaoDTO){
        return ResponseEntity.ok(manutencaoService.atualizarManutencao(manutencaoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarManutencao(@PathVariable Long id){
        manutencaoService.deletarManutencao(id);
        return ResponseEntity.noContent().build();
    }
}
