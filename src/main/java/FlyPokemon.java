class FlyPokemon extends Pokemon implements IFlyable {
    public FlyPokemon(String pokemonName) {
        super(pokemonName);
    }

    public FlyPokemon(String pokemonName, String customName, int Hp, int Atk, int Def) {
        super(pokemonName, customName, Hp, Atk, Def);
    }

    @Override
    public void fly(String tgCity) {

    }

    @Override
    public void crossOcean(String tgCity) {
        fly(tgCity);
    }
}
