class SurfPokemon extends Pokemon implements ISurfable {
    public SurfPokemon(String pokemonName, String customName, int Hp, int Atk, int Def) {
        super(pokemonName, customName, Hp, Atk, Def);
    }

    public SurfPokemon(String pokemonName) {
        super(pokemonName);
    }

    @Override
    public void surf(String tgCity) {
        System.out.println("파도타기 스킬을 사용합니다.");
    }

    @Override
    public void crossOcean(String tgCity) {
        System.out.println( "파도타기를 사용하여 " + tgCity + "(대륙)으로 이동합니다.");
    }
}
