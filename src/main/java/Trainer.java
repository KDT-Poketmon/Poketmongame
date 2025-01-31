import lombok.Getter;

import java.util.*;
@Getter
public class Trainer implements ITrainer {
    private int currentLocation = 11;
    private List<Pokemon> capturedPokemonList = new ArrayList<>(); // 트레이너가 소유한 포켓몬 리스트
    private String name;

    Map<String, Pokemon> capturedPokemonByName = new HashMap<>();
    Scanner inputReader = new Scanner(System.in);

    // 트레이너가 여러 마리의 포켓몬을 추가하는 메서드
    public void addPokemons(Pokemon... pokemons) {
        for (Pokemon pokemon : pokemons) {
            if (pokemon != null) {
                capturedPokemonList.add(pokemon);
                capturedPokemonByName.put(pokemon.getPokemonName(), pokemon);
            }
        }
    }

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

    @Override
    public void townMove(PokeTown tgTown) {

    }


    @Override
    public String toString() {
        return "Trainer : " + name + '\n' +
                "currentLocation=" + currentLocation + '\n' +
                "capturedPokemonList=" + '\n' + capturedPokemonList;
    }
}

//
//@Override
//    public void myLocation() {
//
//    }
//
//    @Override
//    public void movelocation(int destination) {
//
//    }

//    // 현재 위치를 출력하는 메서드
//    public void myLocation() {
//        if (Continent.Map.containsKey(currentLocation)) {
//            System.out.println("현재 위치: " + Continent.Map.get(currentLocation));
//        } else {
//            System.out.println("현재 위치를 알 수 없습니다.");
//        }
//    }

//    // 모든 이동 가능한 장소를 보여주고, 사용자가 선택한 위치로 이동
//    public void chooseDestinationAndMove() {
//        Scanner scanner = new Scanner(System.in);
//
//        // Continent 클래스의 목적지 목록 사용
//        System.out.println("\n--- 이동 가능한 목적지 리스트 ---");
//        for (Map.Entry<Integer, String> entry : Continent.Map.entrySet()) {
//            System.out.printf("%d. %s%n", entry.getKey(), entry.getValue());
//        }
//
//        System.out.print("\n이동할 목적지를 선택하세요 (숫자 입력): ");
//        int destinationChoice;
//
//        // 사용자로부터 목적지 선택
//        try {
//            destinationChoice = Integer.parseInt(scanner.nextLine());
//            if (!Continent.Map.containsKey(destinationChoice)) {
//                System.out.println("유효한 숫자를 입력하세요.");
//                return;
//            }
//        } catch (NumberFormatException e) {
//            System.out.println("숫자를 입력하세요.");
//            return;
//        }
//
//        // 선택된 목적지
//        String destination = Continent.Map.get(destinationChoice);
//        System.out.println("선택한 목적지: " + destination);
//
//        // 현재 보유한 Fly, Surf 포켓몬 확인
//        FlyPokemon flyPokemon = null;
//        SurfPokemon surfPokemon = null;
//
//        for (Pokemon pokemon : this.getCapturedPokemonList()) {
//            if (pokemon instanceof FlyPokemon && flyPokemon == null) {
//                flyPokemon = (FlyPokemon) pokemon;
//            } else if (pokemon instanceof SurfPokemon && surfPokemon == null) {
//                surfPokemon = (SurfPokemon) pokemon;
//            }
//        }
//
//        // 이동 방법 선택 메뉴 표시
//        System.out.println("\n--- 이동 방법 선택 ---");
//        if (flyPokemon != null) {
//            System.out.println("1. 공중날기 사용 (포켓몬: " + flyPokemon.getCustomName() + ")");
//        }
//        if (surfPokemon != null) {
//            System.out.println("2. 파도타기 사용 (포켓몬: " + surfPokemon.getCustomName() + ")");
//        }
//        System.out.println("3. 이동 취소");
//
//        System.out.print("선택: ");
//        int methodChoice;
//
//        // 이동 방법 입력
//        try {
//            methodChoice = Integer.parseInt(scanner.nextLine());
//        } catch (NumberFormatException e) {
//            System.out.println("숫자를 입력해주세요.");
//            return;
//        }
//
//        // 선택에 따라 이동 실행
//        switch (methodChoice) {
//            case 1: // 공중날기
//                if (flyPokemon != null) {
//                    System.out.println(flyPokemon.getCustomName() + "가 공중날기를 사용하여 " + destination + "으로 이동했습니다!");
//                } else {
//                    System.out.println("공중날기 가능한 포켓몬이 없습니다!");
//                }
//                break;
//            case 2: // 파도타기
//                if (surfPokemon != null) {
//                    System.out.println(surfPokemon.getCustomName() + "가 파도타기를 사용하여 " + destination + "으로 이동했습니다!");
//                } else {
//                    System.out.println("파도타기 가능한 포켓몬이 없습니다!");
//                }
//                break;
//            case 3: // 이동 취소
//                System.out.println("이동을 취소했습니다.");
//                break;
//            default:
//                System.out.println("올바른 선택을 해주세요.");
//        }
//    }


//    // 위치 이동 메서드
//    public void movelocation(int destination) {
//        // 현재 위치와 목적지를 대륙 이름으로 매핑
//        String currentPlace = Continent.Map.get(currentLocation);
//        String destinationPlace = Continent.Map.get(destination);
//
//        System.out.printf("\n현재 위치: %s, 이동 시도: %s%n", currentPlace, destinationPlace);
//
//        // 1. 같은 대륙 내 이동인 경우 -> 제한
//        if (isSameContinent(currentLocation, destination)) {
//            System.out.println("같은 대륙에서는 이동할 수 없습니다!");
//            return;
//        }
//
//        // 2. 대륙 간 이동인 경우 (Fly 또는 Surf 포켓몬 확인 필요)
//        FlyPokemon flyPokemon = getFlyPokemon();
//        SurfPokemon surfPokemon = getSurfPokemon();
//
//        if (flyPokemon != null) { // Fly 포켓몬 존재
//            flyPokemon.crossOcean(destinationPlace);
//            currentLocation = destination;
//            System.out.printf("위치가 %s(으)로 변경되었습니다.%n", destinationPlace);
//        } else if (surfPokemon != null) { // Surf 포켓몬 존재
//            surfPokemon.crossOcean(destinationPlace);
//            currentLocation = destination;
//            System.out.printf("위치가 %s(으)로 변경되었습니다.%n", destinationPlace);
//        } else { // Fly/Surf 포켓몬 없음 -> 사용자 확인 필요
//            System.out.println("대륙 간 이동에 Fly 또는 Surf 타입의 포켓몬이 필요합니다.");
//            Scanner scanner = new Scanner(System.in);
//            System.out.print("대륙 간 이동을 강행하시겠습니까? (yes/no): ");
//            String response = scanner.nextLine().trim().toLowerCase();
//
//            if (response.equals("yes")) {
//                System.out.println("대륙 간 이동을 강행합니다.");
//                currentLocation = destination;
//                System.out.printf("위치가 %s(으)로 변경되었습니다.%n", destinationPlace);
//            } else {
//                System.out.println("이동이 취소되었습니다.");
//            }
//        }
//    }
//
//
//    // 같은 대륙에 속하는지 확인하는 메서드
//    private boolean isSameContinent(int currentLocation, int destination) {
//        String currentContinent = Continent.Map.get(currentLocation);
//        String destinationContinent = Continent.Map.get(destination);
//
//        return currentContinent != null && currentContinent.equals(destinationContinent);
//    }
//
//    // Fly 타입 포켓몬 가져오기
//    private FlyPokemon getFlyPokemon() {
//        for (Pokemon pokemon : capturedPokemonList) {
//            if (pokemon instanceof FlyPokemon) {
//                return (FlyPokemon) pokemon;
//            }
//        }
//        return null;
//    }
//
//    // Surf 타입 포켓몬 가져오기
//    private SurfPokemon getSurfPokemon() {
//        for (Pokemon pokemon : capturedPokemonList) {
//            if (pokemon instanceof SurfPokemon) {
//                return (SurfPokemon) pokemon;
//            }
//        }
//        return null;
//    }

