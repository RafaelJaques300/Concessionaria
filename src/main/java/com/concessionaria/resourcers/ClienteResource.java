package com.concessionaria.resourcers;

import com.concessionaria.dtos.ClienteDTO;
import com.concessionaria.models.Cliente;
import com.concessionaria.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cliente")
public class ClienteResource {

    private final ClienteService clienteService;

    public ClienteResource(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/")
    public ResponseEntity<ClienteDTO> buscarCliente(@PathVariable Long id){
        Cliente cliente = clienteService.buscarClientePorId(id);
        return ResponseEntity.ok(clienteService.converterClienteParaClienteDTO(cliente));
    }

    @GetMapping("/buscar")
    public ResponseEntity<ClienteDTO> buscarClientePorEmail(@RequestParam String email) {
        ClienteDTO clienteDTO = clienteService.buscarClientePorEmail(email);
        return ResponseEntity.ok(clienteDTO);
    }

    @PostMapping()
    public ResponseEntity<ClienteDTO> criarCliente(@RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteService.salvarCliente(clienteDTO));
    }

    @PutMapping()
    public ResponseEntity<ClienteDTO> atualizarCliente(ClienteDTO clienteDTO){
        return ResponseEntity.ok(clienteService.atualizarCliente(clienteDTO));
    }

    @DeleteMapping()
    public ResponseEntity<Void> deletarCliente(@RequestBody ClienteDTO clienteDTO){
        clienteService.deletarCliente(clienteDTO.getId());
        return ResponseEntity.noContent().build();
    }
}
