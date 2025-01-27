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
        System.out.println("1: battle, 2: capture / else: pass");
        int battleOrCapture = inputReader.nextInt();
        switch (battleOrCapture) {
            case 1:
                // battle 호출
                battle(wildPokemon);
                break;

            case 2: // 포켓몬 캡처
                Pokemon capturedPokemon = capture(wildPokemon);
                if (capturedPokemon != null) {
                    capturedPokemonList.add(capturedPokemon);
                    capturedPokemonByName.put(capturedPokemon.getPokemonName(), capturedPokemon);
                    System.out.printf("%s(이)가 성공적으로 잡혔습니다!%n", capturedPokemon.getPokemonName());
                }
                break;

            default: // 무시
                System.out.println("포켓몬을 무시하고 지나쳤습니다.");
                break;
        }
    }


    @Override
    public Pokemon capture(Pokemon wildPokemon) {
        //포켓몬을 잡거나 놓치거나
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
                System.out.printf("%s이(가) %s을(를) 공격했습니다! %s의 남은 HP: %d%n",
                        myPokemon.getPokemonName(), wildPokemon.getPokemonName(),
                        wildPokemon.getPokemonName(), wildPokemon.getHp());
            } else {
                // 상대 포켓몬 공격
                wildPokemon.attack(myPokemon);
                System.out.printf("%s이(가) %s을(를) 공격했습니다! %s의 남은 HP: %d%n",
                        wildPokemon.getPokemonName(), myPokemon.getPokemonName(),
                        myPokemon.getPokemonName(), myPokemon.getHp());
            }

            // 야생 포켓몬 HP가 30% 이하로 떨어지면 `hunt` 메서드 호출
            if (wildPokemon.getHp() > 0 && wildPokemon.getHp() <= (wildPokemon.getMaxHp() * 0.3)) {
                System.out.println("야생 포켓몬의 체력이 30% 이하로 떨어졌습니다!");
                hunt(wildPokemon); // 새로운 선택지를 제공
                return; // 선택지 이후 전투 종료
            }

            // 턴 교체
            isMyTurn = !isMyTurn;
        }

        // 전투 결과 출력
        if (wildPokemon.getHp() == 0) {
            System.out.printf("%s이(가) 쓰러졌습니다! 승리!%n", wildPokemon.getPokemonName());
        } else if (myPokemon.getHp() == 0) {
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

    public Pokemon findWildPokemon() {
        return PokeDex.getRandomWildPokemon();
    }
}
