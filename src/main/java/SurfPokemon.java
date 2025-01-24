class SurfPokemon extends Pokemon implements ISurfable {
    public SurfPokemon(String pokemonName, String customName, int Hp, int Atk, int Def) {
        super(pokemonName, customName, Hp, Atk, Def);
    }

    public SurfPokemon(String pokemonName) {
        super(pokemonName);
    }

    @Override
    public void surf(String tgCity) {

    }

    @Override
    public void crossOcean(String tgCity) {

    }
}
