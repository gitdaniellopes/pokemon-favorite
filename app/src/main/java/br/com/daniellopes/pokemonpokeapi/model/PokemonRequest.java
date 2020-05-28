package br.com.daniellopes.pokemonpokeapi.model;

import java.util.List;

public class PokemonRequest {

    private List<Pokemon> pokemons;

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }
}
