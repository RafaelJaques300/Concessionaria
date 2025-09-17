package com.concessionaria.services;

import com.concessionaria.dtos.ClienteDTO;
import com.concessionaria.models.Cliente;
import com.concessionaria.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    private Cliente clienteDTO;

    public ClienteDTO salvarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = converterClienteDTOParaCliente(clienteDTO);
        cliente = clienteRepository.save(cliente);
        return converterClienteParaClienteDTO(cliente);
    }

    private Cliente converterClienteDTOParaCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();

        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setEndereco(clienteDTO.getEndereco());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setEmail(clienteDTO.getEmail());
        return cliente;
    }

    public ClienteDTO converterClienteParaClienteDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setCpf(cliente.getCpf());
        clienteDTO.setEndereco(cliente.getEndereco());
        clienteDTO.setTelefone(cliente.getTelefone());
        clienteDTO.setEmail(cliente.getEmail());
        return clienteDTO;
    }


    public Cliente buscarClientePorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Cliente não encontrado"));
    }

    public ClienteDTO buscarClientePorEmail(String email) {
        return converterClienteParaClienteDTO(clienteRepository.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException("Cliente não encontrado")));
    }

    public ClienteDTO atualizarCliente(ClienteDTO clienteDTO) {
        if (isNull(clienteDTO.getId())) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }

        // Busca o cliente no banco
        Cliente cliente = clienteRepository.findById(clienteDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        // Atualiza apenas os campos necessários
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setEndereco(clienteDTO.getEndereco());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setEmail(clienteDTO.getEmail());

        // Salva só UMA vez
        Cliente atualizado = clienteRepository.save(cliente);

        return converterClienteParaClienteDTO(atualizado);
    }


    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}
