import java.util.*;

public class GameLauncher {
    public static void main(String[] args) {
        // 트레이너 생성
        Trainer trainerkamen = new Trainer();
        Trainer trainerkadan = new Trainer();

        // 트레이너와 야생 포켓몬 출력
        System.out.println("트레이너 Kamen와 Kadan이 생성되었습니다!");

        // PokeDex에서 스타터 포켓몬 가져오기
        Pokemon pikachu = PokeDex.searchPokemon("피카츄");
        Pokemon squirtle = PokeDex.searchPokemon("꼬부기");
        Pokemon charmander = PokeDex.searchPokemon("파이리");

        System.out.println("스타터 포켓몬:");
        System.out.printf("이름: %s, HP: %d, Atk: %d, Def: %d%n",
                pikachu.getPokemonName(), pikachu.getHp(), pikachu.getAtk(), pikachu.getDef());
        System.out.printf("이름: %s, HP: %d, Atk: %d, Def: %d%n",
                squirtle.getPokemonName(), squirtle.getHp(), squirtle.getAtk(), squirtle.getDef());
        System.out.printf("이름: %s, HP: %d, Atk: %d, Def: %d%n",
                charmander.getPokemonName(), charmander.getHp(), charmander.getAtk(), charmander.getDef());

        // 트레이너 이름 설정 및 스타터 포켓몬 추가
        trainerkamen.getCapturedPokemonList().add(pikachu); // Ash의 스타터 포켓몬
        trainerkadan.getCapturedPokemonList().add(squirtle); // Misty의 스타터 포켓몬

        System.out.println("\nkamen의 포켓몬:");
        trainerkamen.showPokemons();

        System.out.println("\nkadan의 포켓몬:");
        trainerkadan.showPokemons();

        // 레쿠쟈를 PokeDex에서 가져오기
        Pokemon rayquaza = PokeDex.searchPokemon("레쿠쟈");
        Pokemon fishKing = PokeDex.searchPokemon("잉어킹");

        // 레쿠쟈를 FlyPokemon으로 초기화
        FlyPokemon flyRayquaza = null; // flyRayquaza를 null로 초기화
        SurfPokemon surfFishKing = null;

        if (rayquaza != null) {
            flyRayquaza = new FlyPokemon(rayquaza.getPokemonName(), "레쿠쟈", rayquaza.getHp(), rayquaza.getAtk(), rayquaza.getDef());
             // fly() 메서드 사용
        } else {
            System.out.println("레쿠쟈를 소유하고 있지 않습니다.");
        }

        if (fishKing != null) {
            surfFishKing = new SurfPokemon(fishKing.getPokemonName(), "잉어킹", fishKing.getHp(), fishKing.getAtk(), fishKing.getDef());
            // fly() 메서드 사용
        } else {
            System.out.println("잉어킹을 소유하고 있지 않습니다.");
        }

        flyRayquaza.crossOcean("달맞이동산");
        surfFishKing.crossOcean("달맞이동산");
        // 3. 야생 포켓몬 랜덤 만남
        Pokemon wildPokemon = PokeDex.getRandomWildPokemon();
        System.out.printf("\n야생의 %s(이)가 나타났다!%n", wildPokemon.getPokemonName());
        trainerkamen.hunt(wildPokemon);

        // 포켓몬 진화 테스트 (주석 처리된 부분)
        // pikachu = pikachu.evolve();
        // System.out.printf("진화 후 이름: %s, HP: %d, Atk: %d, Def: %d%n",
        //         pikachu.getPokemonName(), pikachu.getHp(), pikachu.getAtk(), pikachu.getDef());
    }
}
