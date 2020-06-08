package br.com.daniellopes.pokemonpokeapi.datasource;

import java.util.List;

import br.com.daniellopes.pokemonpokeapi.interfaces.PokemonAPI;
import br.com.daniellopes.pokemonpokeapi.interfaces.PokemonCallback;
import br.com.daniellopes.pokemonpokeapi.model.Pokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PokemonDataSource {

    public void findPokemon(PokemonCallback callback) {
        HTTPClient.retrofit().create(PokemonAPI.class)
                .findAll().enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                }
                callback.onComplete();
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {
                callback.onError(t.getMessage());
                callback.onComplete();
            }
        });

    }

}
