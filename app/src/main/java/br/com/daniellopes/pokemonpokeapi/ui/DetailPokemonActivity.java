package br.com.daniellopes.pokemonpokeapi.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import br.com.daniellopes.pokemonpokeapi.R;
import br.com.daniellopes.pokemonpokeapi.datasource.DetailPokemonDataSource;
import br.com.daniellopes.pokemonpokeapi.model.PokemonDetail;
import br.com.daniellopes.pokemonpokeapi.presenter.PokemonDetailPresenter;

public class DetailPokemonActivity extends AppCompatActivity {

    public static final String KEY_ID = "id";
    private int id;
    private ProgressDialog progress;
    private Toolbar toolbar;

    private ImageView imagePokemon;
    private TextView txtName;
    private TextView txtType;
    private TextView txtDescription;
    private TextView txtNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pokemon);
        bind();

        setSupportActionBar(toolbar);
        configToolbar();
        getPokemonSent();

        DetailPokemonDataSource dataSource = new DetailPokemonDataSource();
        PokemonDetailPresenter presenter = new PokemonDetailPresenter(this, dataSource);

        presenter.findPokemonBy(id);
    }

    private void configToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
            getSupportActionBar().setTitle(null);
        }
    }

    private void getPokemonSent() {
        if (getIntent().getExtras() != null) {
            id = getIntent().getExtras().getInt(KEY_ID);
        }
    }

    public void showPokemon(PokemonDetail pokemonDetail) {
        Picasso.get().load(pokemonDetail.getCurlUrl()).into(imagePokemon);
        txtName.setText(pokemonDetail.getName());
        txtDescription.setText(pokemonDetail.getDescription());
        txtType.setText(pokemonDetail.getType());
        txtNumber.setText(pokemonDetail.getNumberSerial());
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void bind() {
        imagePokemon = findViewById(R.id.image_view_pokemon_detail);
        txtName = findViewById(R.id.text_view_name_pokemon_detail);
        txtType = findViewById(R.id.text_view_type_pokemon_detail);
        txtDescription = findViewById(R.id.text_view_description_pokemon_detail);
        txtNumber = findViewById(R.id.text_view_number_pokemon_detail);
        toolbar = findViewById(R.id.toolbar);
    }
}