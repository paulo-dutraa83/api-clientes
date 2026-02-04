package br.com.cotiinformatica.api_clientes.dtos;

/*registro para defnir os dados que a API ira
 receber para cadastrar ou atualizar um cliente*/
public record ClienteRequest(
        String nome,
        String email,
        String telefone
) {
}
