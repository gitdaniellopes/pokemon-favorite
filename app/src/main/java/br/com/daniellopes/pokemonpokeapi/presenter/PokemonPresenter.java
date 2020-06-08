package br.com.daniellopes.pokemonpokeapi.presenter;

import java.util.List;

import br.com.daniellopes.pokemonpokeapi.ui.MainActivity;
import br.com.daniellopes.pokemonpokeapi.datasource.PokemonDataSource;
import br.com.daniellopes.pokemonpokeapi.interfaces.PokemonCallback;
import br.com.daniellopes.pokemonpokeapi.model.Pokemon;

public class PokemonPresenter implements PokemonCallback {

    private final MainActivity view;
    private final PokemonDataSource dataSource;

    public PokemonPresenter(MainActivity mainActivity, PokemonDataSource dataSource) {
        this.view = mainActivity;
        this.dataSource = dataSource;
    }

    public void requestAll() {
        this.view.showProgressBar();
        dataSource.findPokemon(this);
    }

    @Override
    public void onSuccess(List<Pokemon> pokemonList) {
        this.view.showPokemon(pokemonList);
    }

    @Override
    public void onError(String message) {
        this.view.showFailure(message);
    }

    @Override
    public void onComplete() {
        this.view.hideProgressBar();
    }

}
