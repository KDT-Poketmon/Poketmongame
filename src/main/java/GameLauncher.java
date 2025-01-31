import java.util.Scanner;


public class GameLauncher {
    public static void main(String[] args) {
        PokeTown initTown = new PokeTown(
                "태초마을", "모험이 시작되는 마을입니다!", true,
                (trainer) -> {
                    // 태초마을에서 일어나는 특별한 이벤트 정의 가능
                    System.out.println("[미구현] 처음 게임을 시작하는 트레이너의 경우에만 포켓몬 선택을 수행합니다.");
                }
        );
        PokeTown moonHill = new PokeTown(
                "달맞이동산", "달 포켓몬들의 성지입니다!", false
                "달맞이동산", "달 포켓몬들의 성지입니다!", false,
                // 람다식으로 함수형 인터페이스에 인라인 로직 할당 (객체 생성 시 매번 변경 가능)
                (trainer) -> {
                    for (Pokemon pokemon : trainer.getCapturedPokemonList()) {
                        if (pokemon.getPokeCategory() == PokeDex.PokeCategory.MOON) {
                            System.out.println("달맞이 동산의 영향으로 " + pokemon.getPokemonName() + " 이 진화합니다!");
                            pokemon.evolve();  // TODO : 진화 상세로직 구현 필요
                        }
                    }
                }
        );

        PokeTown bossTown = new PokeTown(
                "암흑마을", "최종 보스와 결투하는 마을입니다!", true,
                (trainer) -> {
                    // 암흑마을에서 일어나는 특별한 이벤트 정의 가능
                    System.out.println("[미구현] 최종 보스 결투 조건을 갖춘 트레이너의 경우 즉시 보스와 전투합니다.");
                }
        );

        Trainer trainer1 = new Trainer("플레이어");
        Trainer trainer2 = new Trainer("라이벌플레이어");

        // 트레이너1의 포켓몬 설정 (PokeDex에서 검색하여 가져오기)
        Pokemon[] trainer1Pokemon = {
                PokeDex.searchPokemon("삐삐"),
                PokeDex.searchPokemon("푸린"),
                new FlyPokemon("피죤", "내 피죤", 10,20, 40, PokeDex.PokeCategory.SKY) // 별도 클래스 필요
        };

        // 트레이너1의 포켓몬 추가
        trainer1.addPokemons(trainer1Pokemon);

        // 트레이너2의 포켓몬 설정
        Pokemon[] trainer2Pokemon = {
                PokeDex.searchPokemon("삐삐"),
                PokeDex.searchPokemon("푸린"),
                new SurfPokemon("거북왕", "니 거북왕", 10, PokeDex.PokeCategory.WATER) // 별도 클래스 필요
        };

        // 트레이너2의 포켓몬 추가
        trainer2.addPokemons(trainer2Pokemon);

        // 트레이너 정보 출력
        System.out.println("\n트레이너 1의 포켓몬:");
        trainer1.showPokemons();

        System.out.println("\n트레이너 2의 포켓몬:");
        trainer2.showPokemons();


        // 더미데이터 생성 완료 확인!
        System.out.println(trainer1);
        System.out.println(trainer2);
//        System.out.println(trainer1);
//        System.out.println(trainer2);

        // TODO 2. 대륙 간 이동 방식으로 달맞이 동산 진입 후 동작 수행
        //   대륙 이동 가능한 포켓몬 필요 -> 1-1 에서 생성
        //   2-1. 달맞이 동산 대륙 이동 및 특수 동작 수행
        trainer1.townMove(moonHill);
        trainer2.townMove(moonHill);
        //   2-2. 태초마을 이동 및 특수 동작 수행
//        trainer1.townMove(initTown);
//        trainer1.townMove(bossTown);
//        trainer1.townMove(bossTown);

        // TODO 3. 트레이드 수행 및 특수한 이벤트 발생
        //   3-1. 트레이드 수행 (특수 이벤트 1 발생)
        //   3-2. 트레이드 수행 (특수 이벤트 2 발생)

        // TODO 4. 전설의 포켓몬 구현 및 확인
        //   4-1. 전설의 포켓몬 Unique 처리 및 Singleton 확인
    }
}

// 트레이너 생성
//        Trainer trainerKamen = new Trainer();
//        System.out.println("트레이너 Kamen이 생성되었습니다!");
//
//        // 1. 스타터 포켓몬 초기화
//        initializeStarterPokemon(trainerKamen);
//
//        // 2. 추가 포켓몬 등록
//        initializeAdditionalPokemon(trainerKamen);
//
//        // 현재 트레이너의 위치 확인
//        System.out.println("\n--- 현재 위치 확인 ---");
//        trainerKamen.myLocation();
//
//        // 3. 사용자 선택을 통해 이동
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            System.out.println("\n--- 메뉴 선택 ---");
//            System.out.println("1. 이동하기");
//            System.out.println("2. 현재 포켓몬 보기");
//            System.out.println("3. 야생 포켓몬 만나기");
//            System.out.println("4. 종료");
//            System.out.print("선택: ");
//
//            int choice;
//            try {
//                choice = Integer.parseInt(scanner.nextLine());
//            } catch (NumberFormatException e) {
//                System.out.println("유효한 숫자를 입력하세요.");
//                continue;
//            }
//
//            switch (choice) {
//                case 1:
//                    // 이동 처리
//                    trainerKamen.chooseDestinationAndMove();
//                    break;
//                case 2:
//                    // 포켓몬 리스트 출력
//                    System.out.println("\n--- 현재 보유 포켓몬 ---");
//                    trainerKamen.showPokemons();
//                    break;
//                case 3:
//                    // 야생 포켓몬 만나기
//                    encounterWildPokemon(trainerKamen);
//                    break;
//                case 4:
//                    // 종료
//                    System.out.println("게임을 종료합니다.");
//                    return;
//                default:
//                    System.out.println("올바른 메뉴를 선택하세요.");
//            }
//        }
//    }
//
//    public static void initializeStarterPokemon(Trainer trainer) {
//        Pokemon pikachu = PokeDex.searchPokemon("피카츄");
//
//        if (pikachu != null) trainer.getCapturedPokemonList().add(pikachu);
//
//
//        // 스타터 포켓몬 출력
//        System.out.println("\n--- 스타터 포켓몬 ---");
//        if (pikachu != null) {
//            System.out.printf("이름: %s, HP: %d, Atk: %d, Def: %d%n",
//                    pikachu.getPokemonName(), pikachu.getHp(), pikachu.getAtk(), pikachu.getDef());
//        }
//
//    }
//
//    // 초기 추가 포켓몬 등록
//    public static void initializeAdditionalPokemon(Trainer trainer) {
//        // FlyPokemon과 SurfPokemon 추가
//        FlyPokemon rayquaza = new FlyPokemon("레쿠자", "레쿠쟈", 105, 150, 90);
//        SurfPokemon gyarados = new SurfPokemon("갸라도스", "갸라도스", 95, 125, 79);
//
//        // 트레이너가 이 추가 포켓몬들을 얻음
//        trainer.getCapturedPokemonList().add(rayquaza);
//        trainer.getCapturedPokemonList().add(gyarados);
//
//        // 출력
//        System.out.println("\n--- 추가 포켓몬 등록 완료 ---");
//        System.out.printf("포켓몬: %s, HP: %d, Atk: %d, Def: %d%n",
//                rayquaza.getCustomName(), rayquaza.getHp(), rayquaza.getAtk(), rayquaza.getDef());
//        System.out.printf("포켓몬: %s, HP: %d, Atk: %d, Def: %d%n",
//                gyarados.getCustomName(), gyarados.getHp(), gyarados.getAtk(), gyarados.getDef());
//    }
//
//    // 야생 포켓몬 만남 처리 메서드
//    public static void encounterWildPokemon(Trainer trainer) {
//        Pokemon wildPokemon = PokeDex.getRandomWildPokemon();
//
//        // 야생 포켓몬 정보 출력
//        System.out.printf("\n야생의 %s(이)가 나타났다!%n", wildPokemon.getPokemonName());
//        System.out.printf("HP: %d, Atk: %d, Def: %d%n",
//                wildPokemon.getHp(), wildPokemon.getAtk(), wildPokemon.getDef());
//
//        // 포획 시도 (Trainer의 hunt)
//        trainer.hunt(wildPokemon);
//    }

