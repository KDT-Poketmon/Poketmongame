import java.util.Scanner;

public class GameLauncher {
    public static void main(String[] args) {
        // 트레이너 생성
        Trainer trainerKamen = new Trainer();
        System.out.println("트레이너 Kamen이 생성되었습니다!");

        // 1. 스타터 포켓몬 초기화
        initializeStarterPokemon(trainerKamen);

        // 2. 추가 포켓몬 등록
        initializeAdditionalPokemon(trainerKamen);

        // 현재 트레이너의 위치 확인
        System.out.println("\n--- 현재 위치 확인 ---");
        trainerKamen.myLocation();

        // 3. 사용자 선택을 통해 이동
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- 메뉴 선택 ---");
            System.out.println("1. 이동하기");
            System.out.println("2. 현 위치 확인하기");
            System.out.println("3. 현재 포켓몬 보기");
            System.out.println("4. 야생 포켓몬 만나기");
            System.out.println("5. 종료");
            System.out.print("선택: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("유효한 숫자를 입력하세요.");
                continue;
            }

            switch (choice) {
                case 1:
                    // 이동 처리
                    trainerKamen.chooseDestinationAndMove();
                    break;
                case 2:
                    System.out.print("트레이너의 ");
                    trainerKamen.myLocation();
                    break;
                case 3:
                    // 포켓몬 리스트 출력
                    System.out.println("\n--- 현재 보유 포켓몬 ---");
                    trainerKamen.showPokemons();
                    break;
                case 4:
                    // 야생 포켓몬 만나기
                    encounterWildPokemon(trainerKamen);
                    break;
                case 5:
                    // 종료
                    System.out.println("게임을 종료합니다.");
                    return;
                default:
                    System.out.println("올바른 메뉴를 선택하세요.");
            }
        }
    }

    public static void initializeStarterPokemon(Trainer trainer) {
        Pokemon pikachu = PokeDex.searchPokemon("피카츄");

        if (pikachu != null) trainer.getCapturedPokemonList().add(pikachu);


        // 스타터 포켓몬 출력
        System.out.println("\n--- 스타터 포켓몬 ---");
        if (pikachu != null) {
            System.out.printf("이름: %s, HP: %d, Atk: %d, Def: %d%n",
                    pikachu.getPokemonName(), pikachu.getHp(), pikachu.getAtk(), pikachu.getDef());
        }

    }

    // 초기 추가 포켓몬 등록
    public static void initializeAdditionalPokemon(Trainer trainer) {
        // FlyPokemon과 SurfPokemon 추가
        FlyPokemon rayquaza = new FlyPokemon("레쿠자", "레쿠쟈", 105, 150, 90);
        SurfPokemon gyarados = new SurfPokemon("갸라도스", "갸라도스", 95, 125, 79);

        // 트레이너가 이 추가 포켓몬들을 얻음
        trainer.getCapturedPokemonList().add(rayquaza);
        trainer.getCapturedPokemonList().add(gyarados);

        // 출력
        System.out.println("\n--- 추가 포켓몬 등록 완료 ---");
        System.out.printf("포켓몬: %s, HP: %d, Atk: %d, Def: %d%n",
                rayquaza.getCustomName(), rayquaza.getHp(), rayquaza.getAtk(), rayquaza.getDef());
        System.out.printf("포켓몬: %s, HP: %d, Atk: %d, Def: %d%n",
                gyarados.getCustomName(), gyarados.getHp(), gyarados.getAtk(), gyarados.getDef());
    }

    // 야생 포켓몬 만남 처리 메서드
    public static void encounterWildPokemon(Trainer trainer) {
        Pokemon wildPokemon = PokeDex.getRandomWildPokemon();

        // 야생 포켓몬 정보 출력
        System.out.printf("\n야생의 %s(이)가 나타났다!%n", wildPokemon.getPokemonName());
        System.out.printf("HP: %d, Atk: %d, Def: %d%n",
                wildPokemon.getHp(), wildPokemon.getAtk(), wildPokemon.getDef());

        // 포획 시도 (Trainer의 hunt)
        trainer.hunt(wildPokemon);
    }
}
