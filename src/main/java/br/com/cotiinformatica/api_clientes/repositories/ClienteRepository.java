package br.com.cotiinformatica.api_clientes.repositories;


import br.com.cotiinformatica.api_clientes.entities.Cliente;
import br.com.cotiinformatica.api_clientes.factories.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteRepository {

    @Autowired
    private ConnectionFactory connectionFactory;

    //Metodo para inserir um cliente no banco de dados
    public void inserir(Cliente cliente) throws Exception {

        //Escrever o comando SQL que será executado no bancod e dados
        var sql = """
                    INSERT INTO clientes(nome, email, telefone)
                    VALUES(?, ?, ?)
                """;

        //Abrindo conexão com o banco de dados
        var connection = connectionFactory.getConnection();

        //Preparando o comando para ser executado
        var statement = connection.prepareStatement(sql);
        statement.setString(1, cliente.getNome());
        statement.setString(2, cliente.getEmail());
        statement.setString(3, cliente.getTelefone());

        //Executando o comando SQL
        statement.execute();

        //Fechando a conexão
        connection.close();
    }

    //Metodo para retornar uma lista de clientes no banco de dados
    //Não é void pois efetivamente retorna algo
    public List<Cliente> obterPornome(String nome) throws Exception {

        //Escrevendo o comando SQL que será executado no bancod e dados
        var sql = """
                    SELECT id, nome, email, telefone, datacadastro, ativo 
                    FROM clientes
                    WHERE ativo = 1 AND nome ILIKE ?
               """;

        //Abrindo conexão com o banco de dados
        var connection = connectionFactory.getConnection();

        //Executando o comando SQL no banco
        var statement = connection.prepareStatement(sql);
        statement.setString(1, "%" + nome + "%");
        var result = statement.executeQuery();

        var lista =new ArrayList<Cliente>();  //Criando uma lista vazia

        //Metodo para popular a lista vazia que criei acima com os resultados  da "var result".
        while(result.next()) {
            var cliente = new Cliente(); //Criando um objeto da clase Cliente

            cliente.setId(result.getInt("id"));
            cliente.setNome(result.getString("nome"));
            cliente.setEmail(result.getString("email"));
            cliente.setTelefone(result.getString("telefone"));
            cliente.setDataCadastro(result.getTimestamp("datacadastro"));
            cliente.setAtivo(result.getInt("ativo"));

            lista.add(cliente); //Adicionando o cliente na lista
        }

        //Fechandoa conexão com o banco de dados
        connection.close();

        return lista; //Retornando a lista
    }
}