package pl.ue.oops.game.universe.level;

import pl.ue.oops.Config;
import pl.ue.oops.game.universe.entities.*;
import pl.ue.oops.game.universe.entities.general.TemporaryGroundEntity;
import pl.ue.oops.game.universe.utils.Dimensions;
import pl.ue.oops.game.universe.utils.GridPosition;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.abs;

public class LevelLoader {
    private LevelLoader() {
    }

    public static Level loadFromFile(String path) {
        if(!path.endsWith(".oopslvl"))
            throw new RuntimeException("Not a level file: " + path);
        try {
            return loadFromReadable(new FileReader(path));
        } catch(FileNotFoundException e) {
            throw new RuntimeException("No file named " + path + ", level could not be loaded");
        }
    }

    public static Level loadFromReadable(Readable readable) {
        final var scanner = new Scanner(readable);
        final var dimensions = new Dimensions(scanner.nextInt(), scanner.nextInt());
        final var level = new Level(dimensions);
        while(scanner.hasNextInt()) {
            final int row = scanner.nextInt(), column = scanner.nextInt();
            final var symbol = scanner.next();
            switch(symbol) {
                case "@" -> level.substitutePlayer(new Player(row, column, level));
                case "?" -> level.requestSpawn(new Clueless(row, column, level));
                case "r" -> {
                    var temp = new RockEntity(level, row, column);
                    level.requestSpawn(temp);
                }
                case "l" -> {
                    var temp = new LakeEntity(level, row, column);
                    level.requestSpawn(temp);
                }
            }
        }
        return level;
    }

    public static Level loadFromGenerator(long seed) {
        return loadFromGenerator(Config.DEFAULT_ROW_COUNT,Config.DEFAULT_COLUMN_COUNT,seed);
    }
    public static Level loadFromGenerator(int rowCount,int collumnCount,long seed) {
        final var dimensions = new Dimensions(rowCount, collumnCount);
        final var level = new Level(dimensions);
        var generatedPositions = LevelGenerator.generateLevel(rowCount,collumnCount,seed);
        for (var x:generatedPositions.keySet()) {
            final int row = x.getRow(), column = x.getColumn();
            final var symbol = generatedPositions.get(x).getName();
            if(symbol.equals("r")){
                var possible = new ArrayList<String>();
                possible.add("rock_0_0");
                possible.add("rock_0_1");
                var temp = new RockEntity(level, row, column, possible.get(positiveModulo(new Random().nextInt(),possible.size())));
                level.requestSpawn(temp);
            }
            else if(symbol.equals("grass_0")){
                var possible = new ArrayList<String>();
                possible.add("grass_0");
                possible.add("grass_1");
                possible.add("grass_2");
                possible.add("grass_3");
                level.addGroundObject(new TemporaryGroundEntity(level, row, column,possible.get(positiveModulo(new Random().nextInt(),possible.size()))));
            }
            else if(symbol.equals("SUS")){
                level.requestSpawn(new SUS(level,new GridPosition(x)));
            }
            else {
                level.requestSpawn(new LakeEntity(level, row, column,symbol));
            }
        }


        var enemies = LevelGenerator.fillWithEnemies(generatedPositions,seed);
        System.out.println(enemies);
        enemies.remove(new GridPosition(rowCount-1,collumnCount-1));
        for (var x:enemies.keySet()) {
            final int row = x.getRow(), column = x.getColumn();
            final var symbol = enemies.get(x);
            System.out.println(symbol);
            switch(symbol) {
                case "?" -> level.requestSpawn(new Clueless(row, column, level));
                case "+" -> level.requestSpawn(new MedKit(row, column, level));
                case "*" -> level.requestSpawn(new Mag(row, column, level));
                case "s" -> level.requestSpawn(new Shooter(row,column,level));
                case "nest" -> level.requestSpawn(new SnakeNest(row,column,level));
            }
        }

        level.substitutePlayer(new Player(0, 0, level));
        level.requestSpawn(new DoorEntity(level,rowCount-1,collumnCount-1));
        return level;
    }

    private static int positiveModulo(int val,int mod){
        val = val%mod;
        if(val<0)
            val+=mod;
        return val;
    }
}
