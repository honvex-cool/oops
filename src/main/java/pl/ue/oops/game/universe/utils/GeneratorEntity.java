package pl.ue.oops.game.universe.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneratorEntity {
    private String name;
    private Integer probability =1;
    private List<String> edges;

    private boolean reachableFromStart=false;

    public String getName() {
        return name;
    }

    public GeneratorEntity(String name,String ... edges){
        this.name = name;
        this.edges = new ArrayList<>(Arrays.asList(edges));
    }

    public GeneratorEntity(GeneratorEntity generatorEntity) {
        this.name = new String(generatorEntity.name);
        this.edges = new ArrayList<>(generatorEntity.edges);
    }
    public List<String> getUpEdge(){
        return new ArrayList<>(Arrays.asList(edges.get(0),edges.get(1),edges.get(2)));
    }
    public List<String> getRightEdge(){
        return new ArrayList<>(Arrays.asList(edges.get(3),edges.get(4),edges.get(5)));
    }
    public List<String> getDownEdge(){
        return new ArrayList<>(Arrays.asList(edges.get(6),edges.get(7),edges.get(8)));
    }
    public List<String> getLeftEdge(){
        return new ArrayList<>(Arrays.asList(edges.get(9),edges.get(10),edges.get(11)));
    }

    public GeneratorEntity setProbability(Integer probability){
        this.probability = probability;
        return this;
    }

    public int getProbability(){
        return probability;
    }
    public String toString(){
        return name;
    }

    public boolean isReachableFromStart() {
        return reachableFromStart;
    }

    public void setReachableFromStart(boolean reachableFromStart) {
        this.reachableFromStart = reachableFromStart;
    }
}
