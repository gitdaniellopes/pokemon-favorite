package br.com.daniellopes.pokemonpokeapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.daniellopes.pokemonpokeapi.R;
import br.com.daniellopes.pokemonpokeapi.model.Pokemon;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    private List<Pokemon> pokemons;
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onClick(int position, Pokemon pokemon, View view);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public PokemonAdapter(List<Pokemon> pokemons, Context context) {
        this.pokemons = pokemons;
        this.context = context;
    }

    public void addAll(List<Pokemon> pokemons) {
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

        holder.viewConstraint.setOnClickListener(v -> {
            if (listener == null) return;
            listener.onClick(position, pokemon, v);
        });
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }


    static class PokemonViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewPokemon;
        private TextView textViewNamePokemon;
        private View viewConstraint;

        PokemonViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewPokemon = itemView.findViewById(R.id.image_pokemon);
            textViewNamePokemon = itemView.findViewById(R.id.text_view_pokemon_name);
            viewConstraint = itemView.findViewById(R.id.constraint_parent);
        }

        void bind(Pokemon pokemon) {
            textViewNamePokemon.setText(pokemon.getName());
            Picasso.get().load(pokemon.getCurlUrl()).into(imageViewPokemon);
        }
    }
}
