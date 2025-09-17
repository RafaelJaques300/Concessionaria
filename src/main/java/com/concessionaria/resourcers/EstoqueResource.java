package com.concessionaria.resourcers;

import com.concessionaria.dtos.EstoqueDTO;
import com.concessionaria.models.Estoque;
import com.concessionaria.services.EstoqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5500/")
@RestController
@RequestMapping("/api/estoque")
public class EstoqueResource {

    private final EstoqueService estoqueService;

    public EstoqueResource(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @GetMapping("/")
    public ResponseEntity<EstoqueDTO> buscarEstoque(@PathVariable Long id) {
        Estoque estoque = estoqueService.buscarEstoquePorId(id);
        return ResponseEntity.ok(estoqueService.converterEstoqueParaEstoqueDTO(estoque));
    }

    @GetMapping("/buscar")
    public ResponseEntity<EstoqueDTO> buscarEstoquePorQuantidadeDisponivel(@RequestParam Double quantidadeDisponivel) {
        EstoqueDTO estoqueDTO = estoqueService.buscarEstoquePorQuantidadeDisponivel(quantidadeDisponivel);
        return ResponseEntity.ok(estoqueDTO);
    }

    @PostMapping()
    public ResponseEntity<EstoqueDTO> criarEstoque(@RequestBody EstoqueDTO estoqueDTO) {
        return ResponseEntity.ok(estoqueService.salvarEstoque(estoqueDTO));
    }

    @GetMapping
    public ResponseEntity<List<EstoqueDTO>> buscarTodosEstoque() {
        List<EstoqueDTO> estoqueDTOList = estoqueService.buscarTodosEstoque();
        return ResponseEntity.ok(estoqueDTOList);
    }

    @PutMapping()
    public ResponseEntity<EstoqueDTO> atualizarEstoque(EstoqueDTO estoqueDTO){
        return ResponseEntity.ok(estoqueService.atualizarEstoque(estoqueDTO));
    }

    @DeleteMapping()
    public ResponseEntity<Void> deletarEstoque(@RequestBody EstoqueDTO estoqueDTO){
        estoqueService.deletarEstoque(estoqueDTO.getId());
        return ResponseEntity.noContent().build();
    }
}
