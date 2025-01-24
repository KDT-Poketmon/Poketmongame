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
        trainerkamen.getCapturedPokemonList().add(PokeDex.searchPokemon("피카츄")); // Ash의 스타터 포켓몬
        trainerkadan.getCapturedPokemonList().add(PokeDex.searchPokemon("꼬부기")); // Misty의 스타터 포켓몬

        System.out.println("\nkamen의 포켓몬:");
        trainerkamen.showPokemons();

        System.out.println("\nkadan의 포켓몬:");
        trainerkadan.showPokemons();

        // 3. 야생 포켓몬 랜덤 만남
        // 누가? 야생 포켓몬을 만나는가?
        Pokemon wildPokemon = PokeDex.getRandomWildPokemon();
        System.out.printf("\n야생의 %s(이)가 나타났다!%n", wildPokemon.getPokemonName());
        trainerkamen.hunt(wildPokemon);


//        // 3. 포켓몬 진화 테스트
//        pikachu = pikachu.evolve();
//        System.out.printf("진화 후 이름: %s, HP: %d, Atk: %d, Def: %d%n",
//                pikachu.getPokemonName(), pikachu.getHp(), pikachu.getAtk(), pikachu.getDef());
    }


        //1단계: 리턴이 자식타입인 EvovledPokemon 인데 Pokemon 타입 변수에 할당 가능

        // EvolvedPokemon evolvedPokemon1 = pokemon1.evolve();
        // pokemon1 = evolvedPokemon1;
        //2단계: 리턴타입 자체를 다형성 타입으로 핸들링
        //pokemon1 = pokemon1.evolve();

    }

