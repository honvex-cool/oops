package pl.ue.oops.game.universe.level;

import pl.ue.oops.Config;
import pl.ue.oops.game.universe.entities.Clueless;
import pl.ue.oops.game.universe.entities.general.ActiveGridEntity;
import pl.ue.oops.game.universe.utils.AdjacencyRules;
import pl.ue.oops.game.universe.utils.GeneratorEntity;
import pl.ue.oops.game.universe.utils.GridPosition;

import java.util.*;

public class LevelGenerator {
    private static List<GeneratorEntity> possibleTerrains = new ArrayList<>();
    private static List<GeneratorEntity> possibleEnemies = new ArrayList<>();

    static {
//TERRAIN
        possibleTerrains.add(AdjacencyRules.getGeneratorEntity("grass_0").setProbability(600));

        possibleTerrains.add(AdjacencyRules.getGeneratorEntity("lake_0_0").setProbability(20));
        possibleTerrains.add(AdjacencyRules.getGeneratorEntity("lake_0_1").setProbability(20));
        possibleTerrains.add(AdjacencyRules.getGeneratorEntity("lake_0_2").setProbability(20));
        possibleTerrains.add(AdjacencyRules.getGeneratorEntity("lake_0_3").setProbability(20));
        possibleTerrains.add(AdjacencyRules.getGeneratorEntity("lake_3_0").setProbability(20));

        possibleTerrains.add(AdjacencyRules.getGeneratorEntity("lake_2_0").setProbability(15));
        possibleTerrains.add(AdjacencyRules.getGeneratorEntity("lake_2_1").setProbability(15));
        possibleTerrains.add(AdjacencyRules.getGeneratorEntity("lake_2_2").setProbability(15));
        possibleTerrains.add(AdjacencyRules.getGeneratorEntity("lake_2_3").setProbability(15));

        possibleTerrains.add(AdjacencyRules.getGeneratorEntity("lake_1_0").setProbability(5));
        possibleTerrains.add(AdjacencyRules.getGeneratorEntity("lake_1_1").setProbability(5));
        possibleTerrains.add(AdjacencyRules.getGeneratorEntity("lake_1_2").setProbability(5));
        possibleTerrains.add(AdjacencyRules.getGeneratorEntity("lake_1_3").setProbability(5));

        possibleTerrains.add(AdjacencyRules.getGeneratorEntity("lake_4_0").setProbability(1));
        possibleTerrains.add(AdjacencyRules.getGeneratorEntity("lake_4_1").setProbability(1));
        possibleTerrains.add(AdjacencyRules.getGeneratorEntity("r").setProbability(10));


//ENEMIES
        possibleEnemies.add(new GeneratorEntity("none").setProbability(100));
        possibleEnemies.add(new GeneratorEntity("?").setProbability(2));
        possibleEnemies.add(new GeneratorEntity("s").setProbability(1));

        possibleEnemies.add(new GeneratorEntity("+").setProbability(1));
        possibleEnemies.add(new GeneratorEntity("*").setProbability(1));
    }

    public LevelGenerator(){}

    public static Map<GridPosition,GeneratorEntity> generateLevel(int rowCount, int columnCount,long seed){
        Random random = new Random(seed);
        Map<GridPosition,List<GeneratorEntity>> freePositions = new HashMap<>();
        Map<GridPosition,GeneratorEntity> generatedPositions = new HashMap<>();

        for(int i=0;i<rowCount;i++){
            for(int j=0;j<columnCount;j++){
                freePositions.put(new GridPosition(i,j),new ArrayList<>(possibleTerrains));
            }
        }


        GridPosition currentPathPosition = new GridPosition(0,0);
        GridPosition targetPathPosition = new GridPosition(rowCount-1,columnCount-1);
        freePositions.remove(targetPathPosition);
        GeneratorEntity pathGroundEntity = AdjacencyRules.getGeneratorEntity("grass_0");

        while(!currentPathPosition.equals(targetPathPosition)){
            updateNeighbouringPositions(currentPathPosition,pathGroundEntity,freePositions);
            generatedPositions.put(currentPathPosition,pathGroundEntity);
            freePositions.remove(currentPathPosition);
            if(currentPathPosition.getRow()<rowCount-1 && currentPathPosition.getColumn()<columnCount-1){
                if(random.nextInt()%2==0){
                    currentPathPosition = currentPathPosition.shifted(1,0);
                }
                else{
                    currentPathPosition = currentPathPosition.shifted(0,1);
                }
            }
            else if(currentPathPosition.getRow()<rowCount-1){
                currentPathPosition = currentPathPosition.shifted(1,0);
            }
            else{
                currentPathPosition = currentPathPosition.shifted(0,1);
            }
        }

        while(!freePositions.isEmpty()){
            GridPosition p = getClosestToCollapse(freePositions,random);
            GeneratorEntity type = getRandomFromPossibleStates(freePositions.get(p),random);
            if(type == null)
                return generateLevel(rowCount,columnCount,random.nextLong());
            updateNeighbouringPositions(p,type,freePositions);
            generatedPositions.put(p,type);
            freePositions.remove(p);
        }
        return generatedPositions;
    }

    private static <T> GridPosition getRandomFromFreePositions(Map<GridPosition,T> map, Random random){
        var temp = random.nextInt() % map.keySet().toArray().length;
        if(temp<0)
            temp += map.keySet().toArray().length;
        return (GridPosition) map.keySet().toArray()[temp];
    }
    private static GridPosition getClosestToCollapse(Map<GridPosition,List<GeneratorEntity>> map,Random random){
        int currentMin = 100000;
        GridPosition gridPosition=null;
        for (var x:map.keySet()) {
            if(map.get(x).size() < currentMin)
                gridPosition = x;
        }
        if(gridPosition == null)
            return getRandomFromFreePositions(map,random);
        return gridPosition;
    }
    private static GeneratorEntity getRandomFromPossibleStates(List<GeneratorEntity> list, Random random){
        if(list.isEmpty())
            return null;
        Integer sum=0;
        for(var x:list){
            sum+= x.getProbability();
        }
        int target = random.nextInt()%sum;
        if(target<0)
            target+=sum;
        for(var x:list){
            if(x.getProbability()>target)
                return new GeneratorEntity(x);
            else target-=x.getProbability();
        }
        return null; //we should never get here but if we do we're fucked
    }

    private static void updateNeighbouringPositions(GridPosition p,GeneratorEntity type, Map<GridPosition,List<GeneratorEntity>> freePositions){
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
    }

    public static Map<GridPosition,GeneratorEntity> fillWithEnemies(Map<GridPosition,GeneratorEntity> terrainMap,long seed){
        final Random random = new Random(seed);
        var possibleEntityPositions = getReachableFromStart(terrainMap);
        Map<GridPosition,GeneratorEntity> enemies = new HashMap<>();

        for(var x:possibleEntityPositions){
            if(x.getRow()> Config.ENEMIES_MINIMAL_DISTANCE_TO_PLAYER && x.getColumn()>Config.ENEMIES_MINIMAL_DISTANCE_TO_PLAYER){
                GeneratorEntity type = getRandomFromPossibleStates(possibleEnemies,random);
                if(!type.getName().equals("none")){
                    enemies.put(x,new GeneratorEntity(type));
                }
            }
        }
        return enemies;
    }
    private static List<GridPosition> getReachableFromStart(Map<GridPosition,GeneratorEntity> terrainMap){
        List<GridPosition> reachable=new ArrayList<>();
        dfs(new GridPosition(0,0),new HashMap<>(terrainMap),reachable);
        return reachable;
    }

    private static void dfs(GridPosition position,Map<GridPosition,GeneratorEntity> terrainMap,List<GridPosition> reachable){
        reachable.add(position);
        terrainMap.remove(position);
        try{
            if(terrainMap.get(position.shifted(1,0)).getName().equals("grass_0")){
                dfs(position.shifted(1,0),terrainMap,reachable);
            }
        }catch (Exception ignored){}
        try{
            if(terrainMap.get(position.shifted(-1,0)).getName().equals("grass_0")){
                dfs(position.shifted(-1,0),terrainMap,reachable);
            }
        }catch (Exception ignored){}
        try{
            if(terrainMap.get(position.shifted(0,1)).getName().equals("grass_0")){
                dfs(position.shifted(0,1),terrainMap,reachable);
            }
        }catch (Exception ignored){}
        try{
            if(terrainMap.get(position.shifted(0,-1)).getName().equals("grass_0")){
                dfs(position.shifted(0,-1),terrainMap,reachable);
            }
        }catch (Exception ignored){}
    }
}
