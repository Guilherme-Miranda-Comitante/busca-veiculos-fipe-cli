# Consulta Tabela FIPE - CLI Application

Uma aplicação de linha de comando (CLI) desenvolvida em **Java** que consome a API da Tabela FIPE para buscar preços médios de carros, motos e caminhões. Este projeto foi o desafio final do curso **"Java: trabalhando com lambdas, streams e Spring Framework"** da Alura.

O principal objetivo foi consolidar o conhecimento em recursos modernos do Java, como manipulação de coleções com Streams, expressões Lambda, concorrência orientada a objetos (Generics) e desserialização de JSON.

## Funcionalidades

O programa funciona de forma interativa no console seguindo o fluxo:
- **Escolha do tipo de veículo:** O usuário escolhe entre Carro, Moto ou Caminhão.
- **Listagem de Marcas:** Busca e exibe todas as marcas disponíveis na categoria escolhida.
- **Filtro de Modelos:** Após escolher a marca pelo código, o usuário pode digitar um trecho do nome do veículo (ex: "Palio" ou "Civic") para filtrar os modelos.
- **Histórico de Preços por Ano:** Exibe uma lista com os valores avaliados do veículo escolhido em todos os anos disponíveis no histórico da FIPE.

## Tecnologias e Conceitos Utilizados

- **Java 17** 
- **Spring Boot** (Estrutura base do projeto/CommandLineRunner)
- **Jackson (ObjectMapper):** Utilizado para mapeamento de dados e desserialização do JSON retornado pela API.
- **Java HttpClient:** Para realizar requisições HTTP assíncronas e síncronas.
- **Java Streams & Lambdas:** Para filtragem dinâmica de modelos, ordenação e mapeamento eficiente de coleções.
- **Java Records:** Utilizados para criar DTOs (Data Transfer Objects) imutáveis e limpos para representar os dados da API.

## Arquitetura e Estrutura do Código

O projeto foi dividido seguindo boas práticas de organização:
- `model/`: Contém os *Records* que mapeiam as respostas da API (`DadosVeiculo`, `Modelos`, etc.).
- `service/`: Classes responsáveis pela infraestrutura do sistema:
  - `ConsumoApi`: Faz a requisição HTTP e retorna a String JSON.
  - `ConverteDados`: Implementa a interface genérica para transformar JSON em objetos Java usando o Jackson.
- `principal/`: Classe `Principal` que gerencia toda a regra de negócio, fluxo de perguntas/respostas e interação no terminal.

## Como Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/Guilherme-Miranda-Comitante/busca-veiculos-fipe-cli.git
   cd busca-veiculos-fipe-cli/testefipe
   ```
2. Abra o projeto na sua IDE favorita (IntelliJ IDEA, Eclipse, VS Code).
3. Certifique-se de ter o **Maven** configurado para baixar as dependências do Spring e Jackson automaticamente.
4. Execute a classe principal da aplicação (`TabelaFipeApplication`).

## Exemplo de Uso (Terminal)

```text
*** OPÇÕES ***
Carro
Moto
Caminhão

Digite uma das opções para buscar:
> carro

Cód: 21 - Marca: Fiat
Cód: 22 - Marca: Ford
Cód: 23 - Marca: Chevrolet
...
Informe o código da marca para consulta:
> 21

Modelos encontrados:
Cód: 4321 - Nome: Palio 1.0 Economy Fire Flex 8V 4p
Cód: 5432 - Nome: Uno Mille 1.0 Fire Flex 8V 2p
...
Digite um trecho do nome do carro para busca:
> Palio

Modelos filtrados:
Cód: 4321 - Nome: Palio 1.0 Economy Fire Flex 8V 4p

Digite o código do modelo para consultar os valores por ano:
> 4321

Valores por ano para o modelo selecionado:
Valor: R\$ 22.500,00 - Ano: 2012 Gasolina - Combustível: Gasolina
Valor: R\$ 20.100,00 - Ano: 2011 Gasolina - Combustível: Gasolina
```

---
Desafio desenvolvido com fins educacionais como parte do ecossistema de aprendizado da **Alura**.
