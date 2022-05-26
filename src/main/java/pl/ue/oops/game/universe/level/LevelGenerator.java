package pl.ue.oops.game.universe.level;

import pl.ue.oops.Config;
import pl.ue.oops.game.universe.entities.RockEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.utils.AdjacencyRules;
import pl.ue.oops.game.universe.utils.GeneratorEntity;
import pl.ue.oops.game.universe.utils.GridPosition;

import java.util.*;

public class LevelGenerator {
    public static int DEFAULT_ROW_COUNT=16;
    public static int DEFAULT_COLUMN_COUNT=24;

    private static List<GeneratorEntity> allTypes = new ArrayList<>();

    static {
        for(int i=0;i<800;i++) //default terrain
            allTypes.add(AdjacencyRules.getGeneratorEntity("grass_0"));
        for(int i=0;i<20;i++) { //lakes
            allTypes.add(AdjacencyRules.getGeneratorEntity("lake_0_0"));
            allTypes.add(AdjacencyRules.getGeneratorEntity("lake_0_1"));
            allTypes.add(AdjacencyRules.getGeneratorEntity("lake_0_2"));
            allTypes.add(AdjacencyRules.getGeneratorEntity("lake_0_3"));
            allTypes.add(AdjacencyRules.getGeneratorEntity("lake_3_0"));
        }
        for(int i=0;i<15;i++){
            allTypes.add(AdjacencyRules.getGeneratorEntity("lake_2_0"));
            allTypes.add(AdjacencyRules.getGeneratorEntity("lake_2_1"));
            allTypes.add(AdjacencyRules.getGeneratorEntity("lake_2_2"));
            allTypes.add(AdjacencyRules.getGeneratorEntity("lake_2_3"));
        }
        for(int i=0;i<5;i++){
            allTypes.add(AdjacencyRules.getGeneratorEntity("lake_1_0"));
            allTypes.add(AdjacencyRules.getGeneratorEntity("lake_1_1"));
            allTypes.add(AdjacencyRules.getGeneratorEntity("lake_1_2"));
            allTypes.add(AdjacencyRules.getGeneratorEntity("lake_1_3"));
        }
        allTypes.add(AdjacencyRules.getGeneratorEntity("lake_4_0"));
        allTypes.add(AdjacencyRules.getGeneratorEntity("lake_4_1"));
        allTypes.add(AdjacencyRules.getGeneratorEntity("r"));
    }

    public LevelGenerator(){}

    public static Map<GridPosition,GeneratorEntity> generateLevel(){
        return generateLevel(DEFAULT_ROW_COUNT,DEFAULT_COLUMN_COUNT);
    }
    public static Map<GridPosition,GeneratorEntity> generateLevel(int rowCount, int columnCount){
        Random random = new Random();
        Map<GridPosition,List<GeneratorEntity>> freePositions = new HashMap<>();
        Map<GridPosition,GeneratorEntity> generatedPositions = new HashMap<>();

        for(int i=0;i<rowCount;i++){
            for(int j=0;j<columnCount;j++){
                freePositions.put(new GridPosition(i,j),new ArrayList<>(allTypes));
            }
        }

        while(!freePositions.isEmpty()){
            GridPosition p = getClosestToCollapse(freePositions,random);
            GeneratorEntity type = getRandomFrom(freePositions.get(p),random);
            try{
                List<GeneratorEntity> list = new ArrayList<>();
                for(var x:freePositions.get(new GridPosition(p.getRow()+1,p.getColumn()))){
                    if(AdjacencyRules.isValidPairing(type.getUpEdge(),x.getDownEdge())) {
                        list.add(x);
                    }
                }
                freePositions.put(new GridPosition(p.getRow()+1,p.getColumn()),list);
            }catch(Exception e){};

            try{
                List<GeneratorEntity> list = new ArrayList<>();
                for(var x:freePositions.get(new GridPosition(p.getRow()-1,p.getColumn()))){
                    if(AdjacencyRules.isValidPairing(type.getDownEdge(),x.getUpEdge()))
                        list.add(x);
                }
                freePositions.put(new GridPosition(p.getRow()-1,p.getColumn()),list);
            }catch (Exception e){};

            try{
                List<GeneratorEntity> list = new ArrayList<>();
                for(var x:freePositions.get(new GridPosition(p.getRow(),p.getColumn()+1))){
                    if(AdjacencyRules.isValidPairing(type.getRightEdge(),x.getLeftEdge()))
                        list.add(x);
                }
                freePositions.put(new GridPosition(p.getRow(),p.getColumn()+1),list);
            }catch(Exception e){};

            try{
                List<GeneratorEntity> list = new ArrayList<>();
                for(var x:freePositions.get(new GridPosition(p.getRow(),p.getColumn()-1))){
                    if(AdjacencyRules.isValidPairing(type.getLeftEdge(),x.getRightEdge()))
                        list.add(x);
                }
                freePositions.put(new GridPosition(p.getRow(),p.getColumn()-1),list);
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
    private static GridPosition getClosestToCollapse(Map<GridPosition,List<GeneratorEntity>> map,Random random){
        int currentMin = 100000;
        GridPosition gridPosition=null;

        for (var x:map.keySet()) {
            var temp = new HashSet<String>();
            for(var y:map.get(x))
                temp.add(y.getName());
            if(temp.size() < currentMin){
                gridPosition = x;
            }
        }

        if(gridPosition == null)
            return getRandomFrom(map,random);
        return gridPosition;
    }
    private static GeneratorEntity getRandomFrom(List<GeneratorEntity> list,Random random){
        if(list.isEmpty())
            return new GeneratorEntity("SUS");
        var temp = random.nextInt()%list.size();
        if(temp<0)
            temp += list.size();
        return list.get(temp);
    }

}
