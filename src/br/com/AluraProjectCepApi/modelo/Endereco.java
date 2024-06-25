package br.com.AluraProjectCepApi.modelo;

public class Endereco {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

    public Endereco(EnderecoJSON enderecoJSON){
        this.cep = enderecoJSON.cep();
        this.logradouro = enderecoJSON.logradouro();
        this.complemento = enderecoJSON.complemento();
        this.bairro = enderecoJSON.bairro();
        this.localidade = enderecoJSON.localidade();
        this.uf = enderecoJSON.uf();
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", localidade='" + localidade + '\'' +
                ", uf='" + uf + '\'' +
                '}';
    }
}
