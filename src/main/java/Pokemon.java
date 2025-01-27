import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//@NoArgsConstructor
public class Pokemon implements IPokemon {
    private String pokemonName;
    private String customName;
    private int Hp;
    private int Atk;
    private int Def;
    private PokeDex.PokeCategory pokeCategory;

    // 포켓몬 이름을 반드시 입력하도록 생성자 정의
    public Pokemon(String pokemonName, String customName, int Hp, int Atk, int Def) {
        if (pokemonName == null || pokemonName.isEmpty()) {
            throw new IllegalArgumentException("포켓몬 이름은 반드시 입력해야 합니다.");
        }
        this.pokemonName = pokemonName;
        this.customName = customName != null ? customName : pokemonName;
        this.Hp = Hp;
        this.Atk = Atk;
        this.Def = Def;
    }

    // 간소화된 생성자: 커스텀 이름과 능력치를 기본값으로 설정
    public Pokemon(String pokemonName) {
        this(pokemonName, pokemonName, 100, 50, 30);
    }

    public Pokemon(String pokemonName, int Hp, int Atk, int Def) {
        this(pokemonName, pokemonName, Hp, Atk, Def); // customName 기본값은 pokemonName으로 설정
    }

    //공격
    @Override
    public void attack(Pokemon tgpokemon) {
        //데이터 : tgPokemon.getHP(), tgPokemon.setHP()
        // 그 외 추가 동작
        int damage = this.Atk - tgpokemon.Def;
        if (damage <= 0) {
            damage = 1; // 최소 데미지 보장
        }
        int newHp = tgpokemon.getHp() - damage;
        tgpokemon.setHp(Math.max(newHp, 0)); // HP 음수 처리 X
        System.out.printf("%s이(가) %s을(를) 공격하여 %d의 데미지를 입혔습니다! 상대 남은 HP: %d%n",
                this.pokemonName, tgpokemon.getPokemonName(), damage, tgpokemon.getHp());
    }

    //도망
    @Override
    public void flee(int enemyLv) {
        // 기본 도망 확률 계산 (레벨 차이에 따라 성공률 변화)
        int fleeChance = 70 - (enemyLv * 5); // 기본 성공률 70%, 적 레벨당 5% 감소
        fleeChance = Math.max(fleeChance, 10); // 최소 성공 확률 10% 보장

        int randomValue = (int) (Math.random() * 100);
        if (randomValue < fleeChance) {
            System.out.printf("%s이(가) 도망쳤습니다!%n", this.pokemonName);
        } else {
            System.out.printf("%s이(가) 도망치지 못했습니다!%n", this.pokemonName);
        }
    }
    
    //진화
    @Override
    public Pokemon evolve() {
        System.out.printf("%s이(가) 진화합니다!%n", this.getPokemonName());

        // 진화 후 이름 설정
        String evolvedName = PokeDex.getEvolvedName(this.getPokemonName());
        this.setPokemonName(evolvedName);

        // 능력치 증가
        this.setHp(this.getHp() + 50);
        this.setAtk(this.getAtk() + 30);
        this.setDef(this.getDef() + 20);

        System.out.printf("진화 후 상태: 이름=%s, HP=%d, Atk=%d, Def=%d%n",
                this.getPokemonName(), this.getHp(), this.getAtk(), this.getDef());

        return this; // 현재 객체 반환
    }

    public Pokemon evolve(PokeDex.PokeCategory pokeCategory) {
        this.setPokeCategory(pokeCategory);
        if (pokeCategory == PokeDex.PokeCategory.MOON)
        {
            System.out.printf("달 속성 포켓몬 %s이(가) 진화합니다!%n", this.getPokemonName());

            // 진화 후 이름 설정
            String evolvedName = PokeDex.getEvolvedName(this.getPokemonName());
            this.setPokemonName(evolvedName);

            // 능력치 증가
            this.setHp(this.getHp() + 50);
            this.setAtk(this.getAtk() + 30);
            this.setDef(this.getDef() + 20);

            System.out.printf("진화 후 상태: 이름=%s, HP=%d, Atk=%d, Def=%d%n",
                    this.getPokemonName(), this.getHp(), this.getAtk(), this.getDef());

            return this; // 현재 객체 반환
        }
        return null;
    }
}

