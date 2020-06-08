package br.com.daniellopes.pokemonpokeapi.interfaces;

import java.util.List;

import br.com.daniellopes.pokemonpokeapi.model.Pokemon;
import br.com.daniellopes.pokemonpokeapi.model.PokemonDetail;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonAPI {

    String BASE_URL = "https://pokemon-favorite.herokuapp.com/";

    @GET("pokemon")
    Call<List<Pokemon>> findAll();

    @GET("pokemon/{id}")
    Call<PokemonDetail> findPokemonBy(@Path("id") int id);
}
