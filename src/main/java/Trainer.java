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
        switch (battleOrCapture) {
            case 1:
                battle(wildPokemon);
                break;
            case 2:
                capture(wildPokemon);
                Pokemon capturedPokemon = capture(wildPokemon);
                if (capturedPokemon != null) {
                    capturedPokemonList.add(capturedPokemon);
                    capturedPokemonByName.put(
                            capturedPokemon.getPokemonName(), capturedPokemon
                    );
                }
                break;
            default:
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
            } else {
                // 상대 포켓몬 공격
                wildPokemon.attack(myPokemon);
            }

            // 턴 교체
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
}
