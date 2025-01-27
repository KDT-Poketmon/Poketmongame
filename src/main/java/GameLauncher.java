import java.util.Scanner;

public class GameLauncher {
    public static void main(String[] args) {
//         // 트레이너 생성
// <<<<<<< feature/Trade-Poket/Trade-Poket_main
//         Trainer trainerkamen = new Trainer(); // 상대 플레이어 (NPC)
//         Trainer trainerkadan = new Trainer(); // 플레이어 (나)

//         // 트레이너 Kadan 생성 메시지 출력
//         System.out.println("트레이너 Kadan이 생성되었습니다!");

//         // Kamen의 포켓몬 자동 생성
//         trainerkamen.getCapturedPokemonList().addAll(PokeDex.getKamenPokemons());
//         System.out.println("트레이너 Kamen의 포켓몬:");
//         trainerkamen.showPokemons();

//         // Kadan의 스타팅 포켓몬 선택
//         Pokemon starterPokemon = PokeDex.searchPokemon("피카츄"); // 예: 피카츄를 스타팅 포켓몬으로 선택
//         if (starterPokemon != null) {
//             trainerkadan.getCapturedPokemonList().add(starterPokemon);
//         }
//         System.out.println("트레이너 Kadan의 스타팅 포켓몬:");
//         trainerkadan.showPokemons();

//         // 야생 포켓몬 랜덤 만남
//         Pokemon wildPokemon = PokeDex.getRandomWildPokemon();
//         System.out.printf("\n야생의 %s(이)가 나타났다!%n", wildPokemon.getPokemonName());

//         // Kadan이 야생 포켓몬과 상호작용
//         trainerkadan.hunt(wildPokemon);

//         // hunt 호출 후 상태 확인
//         System.out.println("\nhunt 호출 후 Kadan의 포켓몬:");
//         trainerkadan.showPokemons();
//     }
// =======
//         Trainer trainerKamen = new Trainer();
//         System.out.println("트레이너 Kamen이 생성되었습니다!");

//         // 1. 스타터 포켓몬 초기화
//         initializeStarterPokemon(trainerKamen);

//         // 2. 추가 포켓몬 등록
//         initializeAdditionalPokemon(trainerKamen);

//         // 현재 트레이너의 위치 확인
//         System.out.println("\n--- 현재 위치 확인 ---");
//         trainerKamen.myLocation();

//         // 3. 사용자 선택을 통해 이동
//         Scanner scanner = new Scanner(System.in);
//         while (true) {
//             System.out.println("\n--- 메뉴 선택 ---");
//             System.out.println("1. 이동하기");
//             System.out.println("2. 현재 포켓몬 보기");
//             System.out.println("3. 야생 포켓몬 만나기");
//             System.out.println("4. 종료");
//             System.out.print("선택: ");

//             int choice;
//             try {
//                 choice = Integer.parseInt(scanner.nextLine());
//             } catch (NumberFormatException e) {
//                 System.out.println("유효한 숫자를 입력하세요.");
//                 continue;
//             }

//             switch (choice) {
//                 case 1:
//                     // 이동 처리
//                     trainerKamen.chooseDestinationAndMove();
//                     break;
//                 case 2:
//                     // 포켓몬 리스트 출력
//                     System.out.println("\n--- 현재 보유 포켓몬 ---");
//                     trainerKamen.showPokemons();
//                     break;
//                 case 3:
//                     // 야생 포켓몬 만나기
//                     encounterWildPokemon(trainerKamen);
//                     break;
//                 case 4:
//                     // 종료
//                     System.out.println("게임을 종료합니다.");
//                     return;
//                 default:
//                     System.out.println("올바른 메뉴를 선택하세요.");
//             }
//         }
//     }

//     public static void initializeStarterPokemon(Trainer trainer) {
//         Pokemon pikachu = PokeDex.searchPokemon("피카츄");

//         if (pikachu != null) trainer.getCapturedPokemonList().add(pikachu);


//         // 스타터 포켓몬 출력
//         System.out.println("\n--- 스타터 포켓몬 ---");
//         if (pikachu != null) {
//             System.out.printf("이름: %s, HP: %d, Atk: %d, Def: %d%n",
//                     pikachu.getPokemonName(), pikachu.getHp(), pikachu.getAtk(), pikachu.getDef());
//         }

//     }

//     // 초기 추가 포켓몬 등록
//     public static void initializeAdditionalPokemon(Trainer trainer) {
//         // FlyPokemon과 SurfPokemon 추가
//         FlyPokemon rayquaza = new FlyPokemon("레쿠자", "레쿠쟈", 105, 150, 90);
//         SurfPokemon gyarados = new SurfPokemon("갸라도스", "갸라도스", 95, 125, 79);

//         // 트레이너가 이 추가 포켓몬들을 얻음
//         trainer.getCapturedPokemonList().add(rayquaza);
//         trainer.getCapturedPokemonList().add(gyarados);

//         // 출력
//         System.out.println("\n--- 추가 포켓몬 등록 완료 ---");
//         System.out.printf("포켓몬: %s, HP: %d, Atk: %d, Def: %d%n",
//                 rayquaza.getCustomName(), rayquaza.getHp(), rayquaza.getAtk(), rayquaza.getDef());
//         System.out.printf("포켓몬: %s, HP: %d, Atk: %d, Def: %d%n",
//                 gyarados.getCustomName(), gyarados.getHp(), gyarados.getAtk(), gyarados.getDef());
//     }

//     // 야생 포켓몬 만남 처리 메서드
//     public static void encounterWildPokemon(Trainer trainer) {
//         Pokemon wildPokemon = PokeDex.getRandomWildPokemon();

//         // 야생 포켓몬 정보 출력
//         System.out.printf("\n야생의 %s(이)가 나타났다!%n", wildPokemon.getPokemonName());
//         System.out.printf("HP: %d, Atk: %d, Def: %d%n",
//                 wildPokemon.getHp(), wildPokemon.getAtk(), wildPokemon.getDef());

//         // 포획 시도 (Trainer의 hunt)
//         trainer.hunt(wildPokemon);
//     }
// >>>>>>> main
}
