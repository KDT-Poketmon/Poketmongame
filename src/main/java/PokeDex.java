import java.util.*;

public class PokeDex {
    //데이터 타입 필드 추가
    static Map<String, Pokemon> pokemonByName = new HashMap<>();
    static Map<PokeCategory, Map<String, Pokemon>> pokemonByCategory = new HashMap<>();
    static List<Pokemon> starterPokemons = new ArrayList<>(); // 스타터 포켓몬 리스트

    private static final Map<String, String> EVOLUTION_MAP = new HashMap<>();

    static {
        EVOLUTION_MAP.put("피카츄", "라이츄");
        EVOLUTION_MAP.put("꼬부기", "어니부기");
        EVOLUTION_MAP.put("어니부기", "거북왕");
        EVOLUTION_MAP.put("이상해씨", "이상해풀");
        EVOLUTION_MAP.put("꼬마돌", "딱구리");
        EVOLUTION_MAP.put("미뇽", "망나뇽");
        EVOLUTION_MAP.put("나무지기", "나무돌이");
        EVOLUTION_MAP.put("리아코", "엘리게일");
        EVOLUTION_MAP.put("잉어킹", "갸라도스");
        // 필요하면 더 추가
    }

    public static String getEvolvedName(String currentName) {
        return EVOLUTION_MAP.getOrDefault(currentName, currentName); // 진화 대상 없으면 기존 이름 유지
    }

    // static 초기화 블록
    static {
        // 3. 포켓몬 사전 더미데이터 생성
        // 카테고리별 초기화
        for (PokeCategory category : PokeCategory.values()) {
            pokemonByCategory.put(category, new HashMap<>());
        }
        // 아래에 더미데이터 생성(예 객체 생성시 해당 객체로 pokemonByName, pokemonByCategory 둘다 업데이트해야함
        // 스타터 포켓몬 생성
        // 스타터 포켓몬과 카테고리를 정의
        // 스타터 포켓몬 정의
        // TODO : 리스트로 변경하기
        Map<Pokemon, PokeCategory> starterData = Map.of(
                new Pokemon("피카츄"), PokeCategory.ELECTRIC,
                new Pokemon("꼬부기"), PokeCategory.WATER,
                new Pokemon("파이리"), PokeCategory.FIRE
        );

        // 반복문으로 등록
        for (Map.Entry<Pokemon, PokeCategory> entry : starterData.entrySet()) {
            Pokemon pokemon = entry.getKey();
            PokeCategory category = entry.getValue();

            pokemonByName.put(pokemon.getPokemonName(), pokemon);
            pokemonByCategory.get(category).put(pokemon.getPokemonName(), pokemon);
            starterPokemons.add(pokemon);
        }

        // 추가 포켓몬 등록
        Map<Pokemon, PokeCategory> additionalData = Map.ofEntries(
                Map.entry(new Pokemon("이상해씨", 100, 50, 55), PokeCategory.EARTH),
                Map.entry(new Pokemon("리자몽", 150, 84, 78), PokeCategory.FIRE),
                Map.entry(new Pokemon("갸라도스", 190, 125, 79), PokeCategory.WATER),
                Map.entry(new Pokemon("잠만보", 250, 110, 65), PokeCategory.NORMAL),
                Map.entry(new Pokemon("루기아", 300, 150, 120), PokeCategory.LEGENDARY),
                Map.entry(new Pokemon("썬더", 270, 140, 100), PokeCategory.LEGENDARY),
                Map.entry(new Pokemon("뮤츠", 280, 160, 90), PokeCategory.MYSTIC),
                Map.entry(new Pokemon("꼬렛", 50, 20, 15), PokeCategory.NORMAL),
                Map.entry(new Pokemon("레쿠쟈", 310, 180, 120), PokeCategory.SKY),
                Map.entry(new Pokemon("마그마", 110, 60, 50), PokeCategory.FIRE),
                Map.entry(new Pokemon("전룡", 140, 75, 60), PokeCategory.ELECTRIC),
                // 추가된 포켓몬
                Map.entry(new Pokemon("삐삐", 100, 55, 45), PokeCategory.NORMAL),
                Map.entry(new Pokemon("망나뇽", 150, 90, 85), PokeCategory.SKY),
                Map.entry(new Pokemon("나무지기", 100, 45, 50), PokeCategory.EARTH),
                Map.entry(new Pokemon("리아코", 100, 48, 55), PokeCategory.WATER),
                Map.entry(new Pokemon("잉어킹", 60, 10, 20), PokeCategory.WATER)
        );


        for (Map.Entry<Pokemon, PokeCategory> entry : additionalData.entrySet()) {
            Pokemon pokemon = entry.getKey();
            PokeCategory category = entry.getValue();

            pokemonByName.put(pokemon.getPokemonName(), pokemon);
            pokemonByCategory.get(category).put(pokemon.getPokemonName(), pokemon);
        }

    }

    //kamen 포켓몬 리스트 정의
    public static List<Pokemon> createPokemonList(List<String> pokemonNames) {
        return pokemonNames.stream()
                .map(name -> {
                    Pokemon pokemon = searchPokemon(name);
                    if (pokemon == null) {
                        System.out.printf("PokeDex.searchPokemon(): %s 포켓몬을 찾을 수 없습니다.%n", name);
                    }
                    return pokemon;
                })
                .filter(Objects::nonNull)
                .toList();
    }

    // 상대 트레이너의 포켓몬 리스트 제공
    public static List<Pokemon> getKamenPokemons() {
        // Kamen의 포켓몬 이름 리스트
        List<String> pokemonNames = List.of("삐삐", "망나뇽", "나무지기", "리아코", "잉어킹");

        // 이름을 기반으로 포켓몬 리스트 생성
        List<Pokemon> kamenPokemons = createPokemonList(pokemonNames);

        if (kamenPokemons.isEmpty()) {
            throw new IllegalStateException("Kamen의 포켓몬 리스트가 비어 있습니다. 데이터를 확인하세요.");
        }

        return kamenPokemons;
    }



    // 야생의 포켓몬 랜덤 생성
    static List<Pokemon> wildPokemons = new ArrayList<>();

    static {
        wildPokemons.addAll(pokemonByName.values());
        wildPokemons.removeAll(starterPokemons);
    }

    public static Pokemon getRandomWildPokemon() {
        if (wildPokemons.isEmpty()) {
            throw new IllegalStateException("야생 포켓몬이 없습니다.");
        }

        Random random = new Random();
        return wildPokemons.get(random.nextInt(wildPokemons.size()));
    }


    public enum PokeCategory {
        WATER, FIRE, EARTH, SKY, LEGENDARY, MYSTIC, NORMAL, ELECTRIC
    }

    public static Pokemon searchPokemon(String name) {
        //서치 로직 추가
        return pokemonByName.get(name);
    }

    ;

    public static Map<String, Pokemon> searchPokemon(PokeCategory category) {
        return pokemonByCategory.get(category);
    }

    ;
}