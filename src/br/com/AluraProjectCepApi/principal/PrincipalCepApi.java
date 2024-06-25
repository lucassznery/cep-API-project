package br.com.AluraProjectCepApi.principal;

import br.com.AluraProjectCepApi.modelo.Endereco;
import br.com.AluraProjectCepApi.modelo.EnderecoJSON;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalCepApi {
    public static void main(String[] args) throws IOException, InterruptedException {

        //Criando um arquivo Json que irá conter as informações do endereço procurado
        FileWriter escrita = new FileWriter("endereços.json");
        Scanner scanner = new Scanner(System.in);

        List<Endereco> listaEnderecos = new ArrayList<>();
        String buscaCep = "";

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        while (!buscaCep.equalsIgnoreCase("sair")) {

            if (buscaCep.equalsIgnoreCase("sair")){
                break;
            }

            try {
                System.out.println(String.format("""
                        *********************************
                        Bem Vindo ao Sistema de Busca :)!
                        Digite um CEP para pesquisar
                        Digite Sair para encerrar a pesquisa!
                        *********************************  
                        """));

                buscaCep = scanner.nextLine();

                //Endereco que contem o diretorio a ser buscado no servidor
                String endereco = "https://viacep.com.br/ws/" + buscaCep + "/json/";

                //Efetuando uma requisição ao servidor utilizando o 'endereco' definido
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();

                //Recebendo uma resposta do servidor
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                //Guardando a resposta do resultado em uma variavel
                String json = response.body();

                EnderecoJSON enderecoJSON = gson.fromJson(json, EnderecoJSON.class);

                Endereco meuEndereco = new Endereco(enderecoJSON);
                listaEnderecos.add(meuEndereco);

                //efetuando a impressão da resposta
                System.out.println("O Endereço Foi Adicionado com Sucesso");

            } catch (Exception e) {
                System.out.println("Aconteceu um erro!");

            } finally {
                System.out.println("O Programa Finalizou!");
            }
        }

        escrita.write(gson.toJson(listaEnderecos));
        escrita.close();
    }

}
