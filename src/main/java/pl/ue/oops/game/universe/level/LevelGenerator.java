package pl.ue.oops.game.universe.level;

import pl.ue.oops.Config;
import pl.ue.oops.game.universe.entities.RockEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.utils.GridPosition;

import java.util.*;

public class LevelGenerator {
    public static int DEFAULT_ROW_COUNT=16;
    public static int DEFAULT_COLUMN_COUNT=24;

    private static List<Character> allTypes = new ArrayList<>();
    private static Map<Character,List<Character>> notNextTo = new HashMap<>();
    private static Map<Character,List<Character>> nextTo = new HashMap<>();

    static {
        for(int i=0;i<40;i++) //default terrain
            allTypes.add('0');
        for(int i=0;i<4;i++) //rocks
            allTypes.add('r');
        for(int i=0;i<1;i++) //lakes
            allTypes.add('l');
        for(int i=0;i<1;i++) //enemies
            allTypes.add('?');
        nextTo.put('r',new ArrayList<>());
        nextTo.put('l',new ArrayList<>());

        for(int i=0;i<60;i++) {
            nextTo.get('l').add('l');
            nextTo.get('r').add('r');
        }
    }

    public LevelGenerator(){}

    public static Map<GridPosition,Character> generateLevel(){
        return generateLevel(DEFAULT_ROW_COUNT,DEFAULT_COLUMN_COUNT);
    }
    public static Map<GridPosition,Character> generateLevel(int rowCount, int columnCount){
        Random random = new Random(333);
        Map<GridPosition,List<Character>> freePositions = new HashMap<>();
        Map<GridPosition,Character> generatedPositions = new HashMap<>();

        for(int i=0;i<rowCount;i++){
            for(int j=0;j<columnCount;j++){
                freePositions.put(new GridPosition(i,j),new ArrayList<>(allTypes));
            }
        }

        while(!freePositions.isEmpty()){
            GridPosition p = getRandomFrom(freePositions,random);
            Character type = getRandomFrom(freePositions.get(p),random);
            try{
                freePositions.get(new GridPosition(p.getRow()+1,p.getColumn())).addAll(nextTo.get(type));
            }catch(Exception e){};
            try{
                freePositions.get(new GridPosition(p.getRow()-1,p.getColumn())).addAll(nextTo.get(type));
            }catch (Exception e){};
            try{
                freePositions.get(new GridPosition(p.getRow(),p.getColumn()+1)).addAll(nextTo.get(type));
            }catch(Exception e){};
            try{
                freePositions.get(new GridPosition(p.getRow(),p.getColumn()-1)).addAll(nextTo.get(type));
            }catch(Exception e){};
            generatedPositions.put(p,type);
            freePositions.remove(p);
        }
        return generatedPositions;
    }

    private static <T> GridPosition getRandomFrom(Map<GridPosition,T> map,Random random){
        var temp = random.nextInt() % map.keySet().toArray().length;
        if(temp<0)
            temp += map.keySet().toArray().length;
        return (GridPosition) map.keySet().toArray()[temp];
    }
    private static Character getRandomFrom(List<Character> list,Random random){
        var temp = random.nextInt()%list.size();
        if(temp<0)
            temp += list.size();
        return list.get(temp);
    }

}
