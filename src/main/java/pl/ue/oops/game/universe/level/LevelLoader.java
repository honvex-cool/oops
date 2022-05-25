package pl.ue.oops.game.universe.level;

import pl.ue.oops.game.universe.entities.Clueless;
import pl.ue.oops.game.universe.entities.LakeEntity;
import pl.ue.oops.game.universe.entities.Player;
import pl.ue.oops.game.universe.entities.RockEntity;
import pl.ue.oops.game.universe.utils.Dimensions;
import pl.ue.oops.game.universe.utils.GridPosition;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

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
                case "@" -> level.setPlayer(new Player(row, column, level));
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



    public static Level loadFromGenerator() {
        final var dimensions = new Dimensions(16, 24);
        final var level = new Level(dimensions);
        var generatedPositions = LevelGenerator.generateLevel();
        for (var x:generatedPositions.keySet()) {
            final int row = x.getRow(), column = x.getColumn();
            final var symbol = generatedPositions.get(x);
            System.out.println(symbol);
            if(symbol.equals('@'))
                level.setPlayer(new Player(row, column, level));
            else if(symbol.equals('?'))
                level.requestSpawn(new Clueless(row, column, level));
            else if(symbol.equals('r')){
                var temp = new RockEntity(level, row, column);
                level.requestSpawn(temp);
            }
            else if(symbol.equals('l')){
                var temp = new LakeEntity(level, row, column);
                level.requestSpawn(temp);
            }
        }
        level.setPlayer(new Player(0, 0, level));
        return level;
    }
}
