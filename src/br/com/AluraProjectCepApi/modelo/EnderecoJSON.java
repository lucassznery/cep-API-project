package br.com.AluraProjectCepApi.modelo;

public record EnderecoJSON(String cep, String logradouro, String complemento, String bairro, String localidade, String uf) {
}
