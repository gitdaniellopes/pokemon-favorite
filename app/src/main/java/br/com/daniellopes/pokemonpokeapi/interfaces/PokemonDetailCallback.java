package br.com.daniellopes.pokemonpokeapi.interfaces;

import br.com.daniellopes.pokemonpokeapi.model.PokemonDetail;

public interface PokemonDetailCallback {

    void onSuccess(PokemonDetail pokemonDetail);

    void onError(String message);

    void onComplete();
}

