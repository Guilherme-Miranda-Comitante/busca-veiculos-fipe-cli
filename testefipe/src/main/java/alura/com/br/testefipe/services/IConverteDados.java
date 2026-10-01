package alura.com.br.testefipe.services;

import tools.jackson.core.type.TypeReference;

import java.util.List;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);

    <T> List<T> obterLista(String json, Class<T> classe);

}
