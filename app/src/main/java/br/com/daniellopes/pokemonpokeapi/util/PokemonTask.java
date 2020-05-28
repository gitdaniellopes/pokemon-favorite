package br.com.daniellopes.pokemonpokeapi.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import br.com.daniellopes.pokemonpokeapi.model.Pokemon;

public class PokemonTask extends AsyncTask<String, Void, List<Pokemon>> {

    private final WeakReference<Context> context;
    private ProgressDialog dialog;
    private PokemonLoader pokemonLoader;

    public PokemonTask(Context context) {
        this.context = new WeakReference<>(context);
    }

    public void setPokemonLoader(PokemonLoader pokemonLoader) {
        this.pokemonLoader = pokemonLoader;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        final Context context = this.context.get();
        if (context != null) {
            dialog = ProgressDialog.show(context, "Carregando",
                    "", false);
        }
    }

    @Override
    protected List<Pokemon> doInBackground(String... params) {

        String url = params[0];

        try {

            URL requestUrl = new URL(url);

            final HttpsURLConnection urlConnection = (HttpsURLConnection)
                    requestUrl.openConnection();
            urlConnection.setReadTimeout(2000);
            urlConnection.setConnectTimeout(2000);

            final int responseCode = urlConnection.getResponseCode();
            if (responseCode > 400) {
                throw new IOException("Erro de comunicação");
            }

            final InputStream inputStream = urlConnection.getInputStream();
            final BufferedInputStream in =
                    new BufferedInputStream(urlConnection.getInputStream());

            String jsonAstring = toString(in);

            List<Pokemon> pokemonRequests =
                    getPokemons(new JSONObject(jsonAstring));

            inputStream.close();
            return pokemonRequests;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<Pokemon> getPokemons(JSONObject json) throws JSONException {

        List<Pokemon> pokemons = new ArrayList<>();
        JSONArray pokemonArray = json.getJSONArray("results");
        for (int i = 0; i < pokemonArray.length(); i++) {
            final JSONObject pokemon = pokemonArray.getJSONObject(i);
            final String name = pokemon.getString("name");
            final String url = pokemon.getString("url");


            Pokemon pokemonObj = new Pokemon();
            pokemonObj.setName(name);
            pokemonObj.setUrl(url);

            pokemons.add(pokemonObj);
        }

        return pokemons;
    }

    @Override
    protected void onPostExecute(List<Pokemon> pokemons) {
        super.onPostExecute(pokemons);
        dialog.dismiss();

        if (pokemonLoader != null) {
            pokemonLoader.onResult(pokemons);
        }
    }

    private String toString(InputStream is) throws IOException {
        final byte[] bytes = new byte[1024];
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int lidos;
        while ((lidos = is.read(bytes)) > 0) {
            baos.write(bytes, 0, lidos);
        }
        return new String(baos.toByteArray());
    }

    public interface PokemonLoader {
        void onResult(List<Pokemon> pokemons);
    }
}
