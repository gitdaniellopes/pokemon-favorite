package br.com.daniellopes.pokemonpokeapi.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.daniellopes.pokemonpokeapi.R;
import br.com.daniellopes.pokemonpokeapi.adapter.PokemonAdapter;
import br.com.daniellopes.pokemonpokeapi.model.Pokemon;
import br.com.daniellopes.pokemonpokeapi.util.PokemonTask;

public class MainActivity extends AppCompatActivity implements PokemonTask.PokemonLoader{

    private PokemonAdapter adapter;
    private RecyclerView recyclerView;
    public static final String BASE_URL ="https://pokeapi.co/api/v2/pokemon?offset=0&limit=20";
    //public static final String BASE_URL ="https://pokeapi.co/api/v2/";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bind();

        List<Pokemon> pokemons = new ArrayList<>();
        adapter = new PokemonAdapter(pokemons, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        PokemonTask pokemonTask = new PokemonTask(this);
        pokemonTask.execute(BASE_URL);
        pokemonTask.setPokemonLoader(this);
    }

    private void bind() {
        recyclerView = findViewById(R.id.recycle_pokemon);
    }

    @Override
    public void onResult(List<Pokemon> pokemons) {
        adapter.setPokemons(pokemons);
        adapter.notifyDataSetChanged();
    }
}
