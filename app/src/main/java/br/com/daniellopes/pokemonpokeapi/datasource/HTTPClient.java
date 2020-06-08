package br.com.daniellopes.pokemonpokeapi.datasource;

import br.com.daniellopes.pokemonpokeapi.interfaces.PokemonAPI;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HTTPClient {
    static Retrofit retrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(PokemonAPI.BASE_URL)
                .build();
    }
}
