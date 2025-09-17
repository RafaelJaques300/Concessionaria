package com.concessionaria.resourcers;

import com.concessionaria.dtos.ClienteDTO;
import com.concessionaria.models.Cliente;
import com.concessionaria.repositories.ClienteRepository;
import com.concessionaria.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Scanner;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/cliente")
public class ClienteResource {

    @Autowired
    private ClienteRepository clienteRepository;

    private final ClienteService clienteService;

    public ClienteResource(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    @GetMapping
    public List<Cliente> listarTodos() {

        return clienteRepository.findAll();
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

    @PostMapping
    public ResponseEntity<ClienteDTO> criarCliente(@RequestBody ClienteDTO clienteDTO) {
        System.out.println("Entrou no post");
        System.out.println(clienteDTO.getNome());
        System.out.println(clienteDTO.getCpf());
        System.out.println(clienteDTO.getEmail());
        System.out.println(clienteDTO.getEndereco());
        System.out.println(clienteDTO.getTelefone());
        return ResponseEntity.ok(clienteService.salvarCliente(clienteDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizarCliente(
            @PathVariable Long id,
            @RequestBody ClienteDTO clienteDTO) {

        clienteDTO.setId(id);

        ClienteDTO atualizado = clienteService.atualizarCliente(clienteDTO);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }
}

