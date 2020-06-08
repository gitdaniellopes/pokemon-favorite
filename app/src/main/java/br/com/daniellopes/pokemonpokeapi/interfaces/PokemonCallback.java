package br.com.daniellopes.pokemonpokeapi.interfaces;

import java.util.List;

import br.com.daniellopes.pokemonpokeapi.model.Pokemon;


public interface PokemonCallback {

    void onSuccess(List<Pokemon> pokemonList);

    void onError(String message);

    void onComplete();
}
