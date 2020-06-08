package br.com.daniellopes.pokemonpokeapi.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.daniellopes.pokemonpokeapi.R;
import br.com.daniellopes.pokemonpokeapi.adapter.PokemonAdapter;
import br.com.daniellopes.pokemonpokeapi.datasource.PokemonDataSource;
import br.com.daniellopes.pokemonpokeapi.model.Pokemon;
import br.com.daniellopes.pokemonpokeapi.presenter.PokemonPresenter;

import static br.com.daniellopes.pokemonpokeapi.ui.DetailPokemonActivity.KEY_ID;

public class MainActivity extends AppCompatActivity {

    private PokemonAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bind();

        List<Pokemon> pokemonList = new ArrayList<>();
        adapter = new PokemonAdapter(pokemonList, this);
        adapter.setOnItemClickListener((position, pokemon, view) -> {
            final Intent intent = new Intent(this, DetailPokemonActivity.class);
            intent.putExtra(KEY_ID, ((Pokemon) pokemon).getId());
            startActivity(intent);
        });

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(adapter);

        PokemonDataSource dataSource = new PokemonDataSource();
        PokemonPresenter presenter = new PokemonPresenter(this, dataSource);

        presenter.requestAll();
    }

    private void onClickAdapter() {
    }

    private void configRecycleAndAdapter() {
    }

    private void bind() {
        recyclerView = findViewById(R.id.recycle_pokemon);
    }

    public void showPokemon(List<Pokemon> pokemonList) {
        adapter.addAll(pokemonList);
        adapter.notifyDataSetChanged();
    }


    public void showFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void showProgressBar() {
        if (progress == null) {
            progress = new ProgressDialog(this);
            progress.setMessage(getString(R.string.loading));
            progress.setIndeterminate(true);
            progress.setCancelable(true);
        }
        progress.show();
    }

    public void hideProgressBar() {
        if (progress != null) {
            progress.hide();
        }
    }

}
