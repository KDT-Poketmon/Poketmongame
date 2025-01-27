import lombok.Getter;

import java.util.*;

@Getter
public class Trainer implements ITrainer {
    Map<String, Pokemon> capturedPokemonByName = new HashMap<>();
    Scanner inputReader = new Scanner(System.in);

    private int currentLocation = 11;

    private List<Pokemon> capturedPokemonList = new ArrayList<>(); // 트레이너가 소유한 포켓몬 리스트


    @Override
    public void hunt(Pokemon wildPokemon) {
//        System.out.println("hunt 호출한 트레이너: " + this.toString());
//        System.out.println("hunt 호출 전 트레이너 포켓몬:");
//        this.showPokemons();

        System.out.println("1:battle, 2:capture / else:pass");
        int battleOrCapture = inputReader.nextInt();
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

//        System.out.println("hunt 종료 후 트레이너 포켓몬:");
//        this.showPokemons();
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
//            System.out.println(pokemon.getPokemonName() );
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

        /* 테스트 데이터 설정
        Pokemon kadanPokemon = new Pokemon("고오스", 100, 50, 30);
        Pokemon kamenPokemon = new Pokemon("토게피", 120, 60, 40);

        kadan.capturedPokemonList.add(kadanPokemon);
        kadan.capturedPokemonByName.put(kadanPokemon.getPokemonName(), kadanPokemon);

        kamen.capturedPokemonList.add(kamenPokemon);
        kamen.capturedPokemonByName.put(kamenPokemon.getPokemonName(), kamenPokemon);
        */

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

    // 현재 위치를 출력하는 메서드
    public void myLocation() {
        if (Continent.Map.containsKey(currentLocation)) {
            System.out.println("현재 위치: " + Continent.Map.get(currentLocation));
        } else {
            System.out.println("현재 위치를 알 수 없습니다.");
        }
    }

    // 포켓몬 리스트 반환 메서드
    public List<Pokemon> getCapturedPokemonList() {
        return capturedPokemonList;
    }

    // 모든 이동 가능한 장소를 보여주고, 사용자가 선택한 위치로 이동
    public void chooseDestinationAndMove() {
        Scanner scanner = new Scanner(System.in);

        // Continent 클래스의 목적지 목록 사용
        System.out.println("\n--- 이동 가능한 목적지 리스트 ---");
        for (Map.Entry<Integer, String> entry : Continent.Map.entrySet()) {
            System.out.printf("%d. %s%n", entry.getKey(), entry.getValue());
        }

        System.out.print("\n이동할 목적지를 선택하세요 (숫자 입력): ");
        int destinationChoice;

        // 사용자로부터 목적지 선택
        try {
            destinationChoice = Integer.parseInt(scanner.nextLine());
            if (!Continent.Map.containsKey(destinationChoice)) {
                System.out.println("유효한 숫자를 입력하세요.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력하세요.");
            return;
        }

        // 선택된 목적지
        String destination = Continent.Map.get(destinationChoice);
        System.out.println("선택한 목적지: " + destination);

        // 현재 보유한 Fly, Surf 포켓몬 확인
        FlyPokemon flyPokemon = null;
        SurfPokemon surfPokemon = null;

        for (Pokemon pokemon : this.getCapturedPokemonList()) {
            if (pokemon instanceof FlyPokemon && flyPokemon == null) {
                flyPokemon = (FlyPokemon) pokemon;
            } else if (pokemon instanceof SurfPokemon && surfPokemon == null) {
                surfPokemon = (SurfPokemon) pokemon;
            }
        }

        // 이동 방법 선택 메뉴 표시
        System.out.println("\n--- 이동 방법 선택 ---");
        if (flyPokemon != null) {
            System.out.println("1. 공중날기 사용 (포켓몬: " + flyPokemon.getCustomName() + ")");
        }
        if (surfPokemon != null) {
            System.out.println("2. 파도타기 사용 (포켓몬: " + surfPokemon.getCustomName() + ")");
        }
        System.out.println("3. 이동 취소");

        System.out.print("선택: ");
        int methodChoice;

        // 이동 방법 입력
        try {
            methodChoice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return;
        }

        // 선택에 따라 이동 실행
        switch (methodChoice) {
            case 1: // 공중날기
                if (flyPokemon != null) {
                    System.out.println(flyPokemon.getCustomName() + "가 공중날기를 사용하여 " + destination + "으로 이동했습니다!");
                } else {
                    System.out.println("공중날기 가능한 포켓몬이 없습니다!");
                }
                break;
            case 2: // 파도타기
                if (surfPokemon != null) {
                    System.out.println(surfPokemon.getCustomName() + "가 파도타기를 사용하여 " + destination + "으로 이동했습니다!");
                } else {
                    System.out.println("파도타기 가능한 포켓몬이 없습니다!");
                }
                break;
            case 3: // 이동 취소
                System.out.println("이동을 취소했습니다.");
                break;
            default:
                System.out.println("올바른 선택을 해주세요.");
        }
    }


    // 위치 이동 메서드
    public void movelocation(int destination) {
        // 현재 위치와 목적지를 대륙 이름으로 매핑
        String currentPlace = Continent.Map.get(currentLocation);
        String destinationPlace = Continent.Map.get(destination);

        System.out.printf("\n현재 위치: %s, 이동 시도: %s%n", currentPlace, destinationPlace);

        // 1. 같은 대륙 내 이동인 경우 -> 제한
        if (isSameContinent(currentLocation, destination)) {
            System.out.println("같은 대륙에서는 이동할 수 없습니다!");
            return;
        }

        // 2. 대륙 간 이동인 경우 (Fly 또는 Surf 포켓몬 확인 필요)
        FlyPokemon flyPokemon = getFlyPokemon();
        SurfPokemon surfPokemon = getSurfPokemon();

        if (flyPokemon != null) { // Fly 포켓몬 존재
            flyPokemon.crossOcean(destinationPlace);
            currentLocation = destination;
            System.out.printf("위치가 %s(으)로 변경되었습니다.%n", destinationPlace);
        } else if (surfPokemon != null) { // Surf 포켓몬 존재
            surfPokemon.crossOcean(destinationPlace);
            currentLocation = destination;
            System.out.printf("위치가 %s(으)로 변경되었습니다.%n", destinationPlace);
        } else { // Fly/Surf 포켓몬 없음 -> 사용자 확인 필요
            System.out.println("대륙 간 이동에 Fly 또는 Surf 타입의 포켓몬이 필요합니다.");
            Scanner scanner = new Scanner(System.in);
            System.out.print("대륙 간 이동을 강행하시겠습니까? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();

            if (response.equals("yes")) {
                System.out.println("대륙 간 이동을 강행합니다.");
                currentLocation = destination;
                System.out.printf("위치가 %s(으)로 변경되었습니다.%n", destinationPlace);
            } else {
                System.out.println("이동이 취소되었습니다.");
            }
        }
    }


    // 같은 대륙에 속하는지 확인하는 메서드
    private boolean isSameContinent(int currentLocation, int destination) {
        String currentContinent = Continent.Map.get(currentLocation);
        String destinationContinent = Continent.Map.get(destination);

        return currentContinent != null && currentContinent.equals(destinationContinent);
    }

    // Fly 타입 포켓몬 가져오기
    private FlyPokemon getFlyPokemon() {
        for (Pokemon pokemon : capturedPokemonList) {
            if (pokemon instanceof FlyPokemon) {
                return (FlyPokemon) pokemon;
            }
        }
        return null;
    }

    // Surf 타입 포켓몬 가져오기
    private SurfPokemon getSurfPokemon() {
        for (Pokemon pokemon : capturedPokemonList) {
            if (pokemon instanceof SurfPokemon) {
                return (SurfPokemon) pokemon;
            }
        }
        return null;
    }

}
