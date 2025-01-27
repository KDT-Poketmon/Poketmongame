public class GameLauncher {
    public static void main(String[] args) {
        // 트레이너 생성
        Trainer trainerkamen = new Trainer(); // 플레이어(나)
        Trainer trainerkadan = new Trainer(); // 상대 플레이어

        // 트레이너와 야생 포켓몬 출력
        System.out.println("트레이너 Kadan이 생성되었습니다!");

        // PokeDex에서 스타터 포켓몬 가져오기
        // 이게 여기 필요한가?
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

        // 플레이어 스타터 포켓몬 추가
        trainerkadan.getCapturedPokemonList().add(pikachu); // Kadan의 스타터 포켓몬

        // 상대 트레이너의 포켓몬 추가
        trainerkamen.getCapturedPokemonList().addAll(PokeDex.getKamenPokemons());

        System.out.println("\nkadan의 포켓몬:");
        trainerkadan.showPokemons();

        // 3. 야생 포켓몬 랜덤 만남
        // 누가? 야생 포켓몬을 만나는가?
        Pokemon wildPokemon = PokeDex.getRandomWildPokemon();
        System.out.printf("\n야생의 %s(이)가 나타났다!%n", wildPokemon.getPokemonName());

        // 플레이어가 야생 포켓몬과 상호작용
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

