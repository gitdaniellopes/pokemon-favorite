package br.com.daniellopes.pokemonpokeapi.model;

public class Pokemon {

    private final int id;
    private final String name;
    private final String curlUrl;

    public Pokemon(int id, String name, String curlUrl) {
        this.id = id;
        this.name = name;
        this.curlUrl = curlUrl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCurlUrl() {
        return curlUrl;
    }
}
