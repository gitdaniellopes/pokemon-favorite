package br.com.daniellopes.pokemonpokeapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.daniellopes.pokemonpokeapi.R;
import br.com.daniellopes.pokemonpokeapi.model.Pokemon;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    private List<Pokemon> pokemons;
    private Context context;

    public PokemonAdapter(List<Pokemon> pokemons, Context context) {
        this.pokemons = pokemons;
        this.context = context;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons.clear();
        this.pokemons.addAll(pokemons);
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCreated = LayoutInflater.from(context)
                .inflate(R.layout.item_main, parent, false);
        return new PokemonViewHolder(viewCreated);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        final Pokemon pokemon = pokemons.get(position);
        holder.bind(pokemon);
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    static class PokemonViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewPokemon;
        private TextView textViewNamePokemon;

        PokemonViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewPokemon = itemView.findViewById(R.id.image_pokemon);
            textViewNamePokemon = itemView.findViewById(R.id.text_view_pokemon_name);
        }

        void bind(Pokemon pokemon) {
            textViewNamePokemon.setText(pokemon.getName());
        }
    }
}
