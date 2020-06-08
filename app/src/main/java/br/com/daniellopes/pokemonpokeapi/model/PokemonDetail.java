package br.com.daniellopes.pokemonpokeapi.model;

public class PokemonDetail {

    private final int id;
    private final String name;
    private final String curlUrl;
    private final String description;
    private final String type;
    private final String numberSerial;

    public PokemonDetail(int id, String name, String curlUrl, String description, String type, String numberSerial, String nameResults) {
        this.id = id;
        this.name = name;
        this.curlUrl = curlUrl;
        this.description = description;
        this.type = type;
        this.numberSerial = numberSerial;
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

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getNumberSerial() {
        return numberSerial;
    }
}
