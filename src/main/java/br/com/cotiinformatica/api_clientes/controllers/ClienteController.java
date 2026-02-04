package br.com.cotiinformatica.api_clientes.controllers;

import br.com.cotiinformatica.api_clientes.dtos.ClienteRequest;
import br.com.cotiinformatica.api_clientes.entities.Cliente;
import br.com.cotiinformatica.api_clientes.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired //Injeção de dependencia( assim nao preciso iniciar o instaciamento, eh automatico)
    private ClienteRepository clienteRepository;

    //Metodo para criar um cadastro de clientes
    @PostMapping
    public String post(@RequestBody ClienteRequest request) {
        try {

            var cliente = new Cliente();

            cliente.setNome(request.nome());
            cliente.setEmail(request.email());
            cliente.setTelefone(request.telefone());

            clienteRepository.inserir(cliente); //Salvando no banco de dados

            return "Cliente cadastrado com sucesso.";

        } catch (Exception e) {
            return "Falha ao inserir cliente: " + e.getMessage();
        }
    }
}
