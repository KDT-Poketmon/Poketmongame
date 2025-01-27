class FlyPokemon extends Pokemon implements IFlyable {
    public FlyPokemon(String pokemonName) {
        super(pokemonName);
    }

    public FlyPokemon(String pokemonName, String customName, int Hp, int Atk, int Def) {
        super(pokemonName, customName, Hp, Atk, Def);
    }

    @Override
    public void fly(String tgCity) {
        System.out.println("공중날기 스킬을 사용합니다.");
    }

    @Override
    public void crossOcean(String tgCity) {
        System.out.println("공중날기 스킬을 사용하여 " + tgCity +"(대륙)으로 이동합니다.");

    }
}