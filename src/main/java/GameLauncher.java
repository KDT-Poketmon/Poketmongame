public class GameLauncher {
    public static void main(String[] args) {
        // 트레이너 생성
        Trainer trainerkamen = new Trainer(); // 상대 플레이어 (NPC)
        Trainer trainerkadan = new Trainer(); // 플레이어 (나)

        // 트레이너 Kadan 생성 메시지 출력
        System.out.println("트레이너 Kadan이 생성되었습니다!");

        // Kamen의 포켓몬 자동 생성
        trainerkamen.getCapturedPokemonList().addAll(PokeDex.getKamenPokemons());
        System.out.println("트레이너 Kamen의 포켓몬:");
        trainerkamen.showPokemons();

        // Kadan의 스타팅 포켓몬 선택
        Pokemon starterPokemon = PokeDex.searchPokemon("피카츄"); // 예: 피카츄를 스타팅 포켓몬으로 선택
        if (starterPokemon != null) {
            trainerkadan.getCapturedPokemonList().add(starterPokemon);
        }
        System.out.println("트레이너 Kadan의 스타팅 포켓몬:");
        trainerkadan.showPokemons();

        // 야생 포켓몬 랜덤 만남
        Pokemon wildPokemon = PokeDex.getRandomWildPokemon();
        System.out.printf("\n야생의 %s(이)가 나타났다!%n", wildPokemon.getPokemonName());

        // Kadan이 야생 포켓몬과 상호작용
        trainerkadan.hunt(wildPokemon);

        // hunt 호출 후 상태 확인
        System.out.println("\nhunt 호출 후 Kadan의 포켓몬:");
        trainerkadan.showPokemons();
    }
}
