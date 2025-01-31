import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PokeTown {
    // 필드 멤버
    private String name;
    private String townIntroduction;
    private boolean walkable;
    public PokeTown(String name, String townIntroduction, boolean walkable) {
        this.name = name;
        this.townIntroduction = townIntroduction;
        this.walkable = walkable;
    }
    // 메서드 선언
    // TODO : 진입 시 특수 이벤트를 발생시키는 동작 필요!
}