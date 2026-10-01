package alura.com.br.testefipe.principal;

import alura.com.br.testefipe.models.Dados;
import alura.com.br.testefipe.models.Modelos;
import alura.com.br.testefipe.models.Veiculo;
import alura.com.br.testefipe.services.ConsumoApi;
import alura.com.br.testefipe.services.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);

    private ConsumoApi consumo = new ConsumoApi();

    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";

    private ConverteDados conversor = new ConverteDados();

    public void exibirMenu() {
        System.out.println(
                "\n-------" +
                        "\nOPCOES:" +
                        "\nCarros" +
                        "\nMotos" +
                        "\nCaminhoes"
        );
        System.out.println("Digite a opção que deseja buscar: ");
        String opcao = leitura.nextLine().toLowerCase();
        String endereco;

        if (opcao.toLowerCase().contains("carr")) {
            endereco = ENDERECO + "carros/marcas";
        } else if (opcao.toLowerCase().contains("mot")) {
            endereco = ENDERECO + "motos/marcas";
        } else {
            endereco = ENDERECO + "caminhoes/marcas";
        }

        var json = consumo.obterDados(endereco);
        System.out.println(json);

        var marcas = conversor.obterLista(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("Informe o código da marca que deseja buscar: ");
        var codigoMarca = leitura.nextLine();

        endereco = endereco + "/" + codigoMarca + "/modelos";
        json = consumo.obterDados(endereco);
        var modeloLista = conversor.obterDados(json, Modelos.class);

        System.out.println("\nModelos dessa marca: ");
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("\nDigite um trecho do nome do carro a ser buscado: ");
        var nomeVeiculo = leitura.nextLine();

        List<Dados> modelosFiltrados = modeloLista.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\nModelos Filtrados: ");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("Digite o código do modelo para buscar os valores de avaliação: ");
        var codigoModelo = leitura.nextLine();

        endereco = endereco + "/" + codigoModelo + "/anos";
        json = consumo.obterDados(endereco);
        List<Dados> anos = conversor.obterLista(json, Dados.class);
        List<Veiculo> veiculos = new ArrayList<>();
        for (int i = 0; i < anos.size(); i++) {
            var enderecoAnos = endereco + "/" + anos.get(i).codigo();
            json = consumo.obterDados(enderecoAnos);
            Veiculo veiculo = conversor.obterDados(json, Veiculo.class);
            veiculos.add(veiculo);
        }

        System.out.println("\nTodos os veículos filtrados com avaliações por ano: ");
        veiculos.forEach(System.out::println);


    }
}


