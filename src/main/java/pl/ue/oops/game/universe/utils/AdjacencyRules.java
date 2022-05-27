package pl.ue.oops.game.universe.utils;

import com.badlogic.gdx.maps.MapObject;
import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.entities.LakeEntity;

import java.lang.reflect.Type;
import java.sql.Types;
import java.util.*;

public final class AdjacencyRules {
    private static Set<AbstractMap.SimpleEntry<String,String>> rules = new HashSet<>(); //Set with legal pairings
    private static Map<String,GeneratorEntity> entities = new HashMap<>();

    static {
        rules.add(new AbstractMap.SimpleEntry<>("g","g"));
        rules.add(new AbstractMap.SimpleEntry<>("l","l"));
        rules.add(new AbstractMap.SimpleEntry<>("g","gb"));
        rules.add(new AbstractMap.SimpleEntry<>("dl","dl"));
        rules.add(new AbstractMap.SimpleEntry<>("gb","gb"));

        rules.add(new AbstractMap.SimpleEntry<>("g","r"));
        rules.add(new AbstractMap.SimpleEntry<>("gb","r"));
        //rules.add(new AbstractMap.SimpleEntry<>("l","r"));
        //rules.add(new AbstractMap.SimpleEntry<>("dl","r"));
        rules.add(new AbstractMap.SimpleEntry<>("r","r"));



        entities.put("grass_0",new GeneratorEntity("grass_0",
                "g","g","g"
                ,"g","g","g",
                "g","g","g",
                "g","g","g"));
        entities.put("lake_0_0",new GeneratorEntity("lake_0_0",
                "gb","gb","gb"
                ,"gb","l","dl",
                "gb","l","dl",
                "gb","gb","gb"));
        entities.put("lake_0_1",new GeneratorEntity("lake_0_1",
                "gb","gb","gb"
                ,"gb","gb","gb",
                "dl","l","gb",
                "gb","l","dl"));
        entities.put("lake_0_2",new GeneratorEntity("lake_0_2",
                "dl","l","gb"
                ,"gb","gb","gb",
                "gb","gb","gb",
                "dl","l","gb"));
        entities.put("lake_0_3",new GeneratorEntity("lake_0_3",
                "gb","l","dl"
                ,"dl","l","gb",
                "gb","gb","gb",
                "gb","gb","gb"));
        entities.put("lake_1_0",new GeneratorEntity("lake_1_0",
                "gb","gb","gb"
                ,"gb","l","dl",
                "dl","dl","dl",
                "gb","l","dl"));
        entities.put("lake_1_1",new GeneratorEntity("lake_1_1",
                "dl","l","gb"
                ,"gb","gb","gb",
                "dl","l","gb",
                "dl","dl","dl"));
        entities.put("lake_1_2",new GeneratorEntity("lake_1_2",
                "dl","dl","dl"
                ,"dl","l","gb",
                "gb","gb","gb",
                "dl","l","gb"));
        entities.put("lake_1_3",new GeneratorEntity("lake_1_3",
                "gb","l","dl"
                ,"dl","dl","dl",
                "gb","l","dl",
                "gb","gb","gb"));
        entities.put("lake_2_0",new GeneratorEntity("lake_2_0",
                "dl","l","gb"
                ,"gb","l","dl",
                "dl","dl","dl",
                "dl","dl","dl"));
        entities.put("lake_2_1",new GeneratorEntity("lake_2_1",
                "dl","dl","dl"
                ,"dl","l","gb",
                "dl","l","gb",
                "dl","dl","dl"));
        entities.put("lake_2_2",new GeneratorEntity("lake_2_2",
                "dl","dl","dl"
                ,"dl","dl","dl",
                "gb","l","dl",
                "dl","l","gb"));
        entities.put("lake_2_3",new GeneratorEntity("lake_2_3",
                "gb","l","dl"
                ,"dl","dl","dl",
                "dl","dl","dl",
                "gb","l","dl"));
        entities.put("lake_3_0",new GeneratorEntity("lake_3_0",
                "dl","dl","dl"
                ,"dl","dl","dl",
                "dl","dl","dl",
                "dl","dl","dl"));
        entities.put("lake_4_0",new GeneratorEntity("lake_4_0",
                "gb","l","dl"
                ,"dl","l","gb",
                "dl","l","gb",
                "gb","l","dl"));
        entities.put("lake_4_1",new GeneratorEntity("lake_4_1",
                "dl","l","gb"
                ,"gb","l","dl",
                "gb","l","dl",
                "dl","l","gb"));
        entities.put("r",new GeneratorEntity("r",
                "r","r","r"
                ,"r","r","r",
                "r","r","r",
                "r","r","r"));
    }


    public static boolean isValidPairing(List<String> s1,List<String> s2){
        for(int i=0;i<3;i++) {
            if((!rules.contains(new AbstractMap.SimpleEntry<>(s1.get(i),s2.get(i)))) && (!rules.contains(new AbstractMap.SimpleEntry<>(s2.get(i),s1.get(i))))){
                return false;
            }
        }
        return true;
    }

    public static GeneratorEntity getGeneratorEntity(String name){
        return new GeneratorEntity(entities.get(name));
    }
}
