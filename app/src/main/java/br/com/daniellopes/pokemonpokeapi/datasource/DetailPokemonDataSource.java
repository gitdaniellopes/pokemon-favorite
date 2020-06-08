package br.com.daniellopes.pokemonpokeapi.datasource;

import br.com.daniellopes.pokemonpokeapi.interfaces.PokemonAPI;
import br.com.daniellopes.pokemonpokeapi.interfaces.PokemonDetailCallback;
import br.com.daniellopes.pokemonpokeapi.model.PokemonDetail;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPokemonDataSource {

    public void findByPokemon(PokemonDetailCallback callback, int id) {
        HTTPClient.retrofit().create(PokemonAPI.class)
                .findPokemonBy(id).enqueue(new Callback<PokemonDetail>() {
            @Override
            public void onResponse(Call<PokemonDetail> call, Response<PokemonDetail> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                }
                callback.onComplete();
            }

            @Override
            public void onFailure(Call<PokemonDetail> call, Throwable t) {
                callback.onError(t.getMessage());
                callback.onComplete();
            }
        });
    }
}
