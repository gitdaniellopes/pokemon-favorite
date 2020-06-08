package br.com.daniellopes.pokemonpokeapi.presenter;

import br.com.daniellopes.pokemonpokeapi.datasource.DetailPokemonDataSource;
import br.com.daniellopes.pokemonpokeapi.interfaces.PokemonDetailCallback;
import br.com.daniellopes.pokemonpokeapi.model.PokemonDetail;
import br.com.daniellopes.pokemonpokeapi.ui.DetailPokemonActivity;

public class PokemonDetailPresenter implements PokemonDetailCallback {

    private DetailPokemonActivity view;
    private DetailPokemonDataSource dataSource;

    public PokemonDetailPresenter(DetailPokemonActivity view,
                                  DetailPokemonDataSource dataSource) {
        this.view = view;
        this.dataSource = dataSource;

    }

    public void findPokemonBy(int id) {
        this.view.showProgressBar();
        dataSource.findByPokemon(this, id);
    }

    @Override
    public void onSuccess(PokemonDetail pokemonDetail) {
        this.view.showPokemon(pokemonDetail);
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
