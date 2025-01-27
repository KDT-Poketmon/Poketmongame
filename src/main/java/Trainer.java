import lombok.Getter;

import java.util.*;

@Getter
public class Trainer implements ITrainer {
    List<Pokemon> capturedPokemonList = new ArrayList<>();
    Map<String, Pokemon> capturedPokemonByName = new HashMap<>();
    Scanner inputReader = new Scanner(System.in);

    @Override
    public void hunt(Pokemon wildPokemon) {
        // 야생의 포켓몬은 만나서 싸우거나 잡거나
        System.out.println("1:battle, 2:capture / else:pass");
        int battleOrCapture = inputReader.nextInt();
        inputReader.nextLine(); // 버퍼 비우기
        switch (battleOrCapture) {
            case 1:
                battle(wildPokemon);
                break;
            case 2:
                Pokemon capturedPokemon = capture(wildPokemon);
                if (capturedPokemon != null) {
                    capturedPokemonList.add(capturedPokemon);
                    capturedPokemonByName.put(capturedPokemon.getPokemonName(), capturedPokemon);
                }
                break;
            default:
                System.out.println("야생 포켓몬을 놓쳤습니다.");
                break;
        }
    }

    @Override
    public Pokemon capture(Pokemon wildPokemon) {
        // 포켓몬을 잡거나 놓치거나
        System.out.printf("%s 포켓몬을 잡았습니다!%n", wildPokemon.getPokemonName());
        return wildPokemon;
    }

    @Override
    public void battle(ITrainer enemyTrainer) {
        //
    }

    @Override
    public void battle(Pokemon wildPokemon) {
        List<Pokemon> myLineUp = this.getCapturedPokemonList();

        if (myLineUp.isEmpty()) {
            System.out.println("트레이너의 포켓몬이 없습니다!");
            return;
        }

        Pokemon myPokemon = myLineUp.get(0); // 첫 번째 포켓몬 사용
        System.out.printf("전투 시작! %s (HP: %d) vs %s (HP: %d)%n",
                myPokemon.getPokemonName(), myPokemon.getHp(),
                wildPokemon.getPokemonName(), wildPokemon.getHp());

        // 턴제 전투
        boolean isMyTurn = true; // 내 턴인지 여부
        while (myPokemon.getHp() > 0 && wildPokemon.getHp() > 0) {
            if (isMyTurn) {
                // 내 포켓몬 공격
                myPokemon.attack(wildPokemon);
            } else {
                // 상대 포켓몬 공격
                wildPokemon.attack(myPokemon);
            }
            isMyTurn = !isMyTurn;
        }

        // 전투 결과 출력
        if (wildPokemon.getHp() == 0) {
            System.out.printf("%s이(가) 쓰러졌습니다! 승리!%n", wildPokemon.getPokemonName());
        } else {
            System.out.printf("%s이(가) 쓰러졌습니다! 패배!%n", myPokemon.getPokemonName());
        }
    }

    @Override
    public Pokemon searchDex(String pokemonName) {
        return PokeDex.searchPokemon(pokemonName);
    }

    @Override
    public Map<String, Pokemon> searchDex(PokeDex.PokeCategory category) {
        return PokeDex.searchPokemon(category);
    }

    @Override
    public void showPokemons() {
        if (capturedPokemonList.isEmpty()) {
            System.out.println("트레이너가 소유한 포켓몬이 없습니다.");
            return;
        }

        System.out.println("트레이너가 소유한 포켓몬 리스트:");
        for (Pokemon pokemon : capturedPokemonList) {
            System.out.printf("이름: %s, HP: %d, Atk: %d, Def: %d%n",
                    pokemon.getPokemonName(), pokemon.getHp(), pokemon.getAtk(), pokemon.getDef());
        }
    }

    // === 새롭게 추가된 기능 ===
    public void tradePokemon(Trainer otherTrainer) {
        if (capturedPokemonList.isEmpty()) {
            System.out.println("교환 가능한 포켓몬이 없습니다.");
            return;
        }

        if (otherTrainer.capturedPokemonList.isEmpty()) {
            System.out.println("상대 트레이너가 교환 가능한 포켓몬이 없습니다.");
            return;
        }

        System.out.println("당신의 포켓몬:");
        showPokemons();

        System.out.println("상대 트레이너의 포켓몬:");
        otherTrainer.showPokemons();

        System.out.println("교환할 당신의 포켓몬 이름을 입력하세요:");
        String myPokemonName = inputReader.nextLine();
        Pokemon myPokemon = capturedPokemonByName.get(myPokemonName);

        if (myPokemon == null) {
            System.out.println("해당 이름의 포켓몬이 없습니다. 교환 취소.");
            return;
        }

        System.out.println("상대 트레이너가 교환할 포켓몬 이름을 입력하세요:");
        String otherPokemonName = inputReader.nextLine();
        Pokemon otherPokemon = otherTrainer.capturedPokemonByName.get(otherPokemonName);

        if (otherPokemon == null) {
            System.out.println("상대 트레이너가 해당 이름의 포켓몬을 가지고 있지 않습니다. 교환 취소.");
            return;
        }

        // 교환 수행
        capturedPokemonList.remove(myPokemon);
        capturedPokemonByName.remove(myPokemon.getPokemonName());
        capturedPokemonList.add(otherPokemon);
        capturedPokemonByName.put(otherPokemon.getPokemonName(), otherPokemon);

        otherTrainer.capturedPokemonList.remove(otherPokemon);
        otherTrainer.capturedPokemonByName.remove(otherPokemon.getPokemonName());
        otherTrainer.capturedPokemonList.add(myPokemon);
        otherTrainer.capturedPokemonByName.put(myPokemon.getPokemonName(), myPokemon);

        System.out.printf("교환 완료! 당신의 새로운 포켓몬: %s%n", otherPokemon.getPokemonName());

        // 진화 동작 수행
        evolveIfEligible(myPokemon, true);
        evolveIfEligible(otherPokemon, true);
    }

    private void evolveIfEligible(Pokemon pokemon, boolean isTradeEvolution) {
        // 통신 교환 진화 조건
        if (isTradeEvolution && isTradeEvolutionEligible(pokemon)) {
            System.out.printf("%s은(는) 통신 교환으로 인해 진화합니다!%n", pokemon.getPokemonName());
            pokemon.evolve();
        } else {
            System.out.printf("%s은(는) 진화할 수 없는 포켓몬입니다.%n", pokemon.getPokemonName());
        }
    }

    private boolean isTradeEvolutionEligible(Pokemon pokemon) {
        // 교환으로 진화 가능한 포켓몬 리스트
        Set<String> tradeEvolutionNames = Set.of("고오스", "윤겔라", "토게피");
        return tradeEvolutionNames.contains(pokemon.getPokemonName());
    }

    // === 테스트용 main 메서드 ===
    public static void main(String[] args) {
        Trainer kadan = new Trainer();
        Trainer kamen = new Trainer();

        // 테스트 데이터 설정
        Pokemon kadanPokemon = new Pokemon("고오스", 100, 50, 30);
        Pokemon kamenPokemon = new Pokemon("토게피", 120, 60, 40);

        kadan.capturedPokemonList.add(kadanPokemon);
        kadan.capturedPokemonByName.put(kadanPokemon.getPokemonName(), kadanPokemon);

        kamen.capturedPokemonList.add(kamenPokemon);
        kamen.capturedPokemonByName.put(kamenPokemon.getPokemonName(), kamenPokemon);

        System.out.println("=== 교환 전 상태 ===");
        System.out.println("kadan의 포켓몬:");
        kadan.showPokemons();
        System.out.println("kamen의 포켓몬:");
        kamen.showPokemons();

        System.out.println("\n=== 교환 실행 ===");
        kadan.tradePokemon(kamen);

        System.out.println("\n=== 교환 후 상태 ===");
        System.out.println("kadan의 포켓몬:");
        kadan.showPokemons();
        System.out.println("kamen의 포켓몬:");
        kamen.showPokemons();
    }
}
