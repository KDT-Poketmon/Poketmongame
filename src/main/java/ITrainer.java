import java.util.Map;

public interface ITrainer {
   public String currentLocation = "태초마을";
   void hunt(Pokemon wildPokemon);
   Pokemon capture(Pokemon wildPokemon);
   void battle(ITrainer enemyTrainer);
   void battle(Pokemon wildPokemon);
   Pokemon searchDex(String pokemonName);
   Map<String, Pokemon> searchDex(PokeDex.PokeCategory category);
   void showPokemons();
//   void myLocation();
//   void movelocation(int destination);
   void townMove(PokeTown tgTown);
}
