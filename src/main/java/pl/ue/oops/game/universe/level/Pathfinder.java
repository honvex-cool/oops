package pl.ue.oops.game.universe.level;

import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.entities.general.ActiveGridEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.entities.general.Projectile;
import pl.ue.oops.game.universe.utils.Position;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Pathfinder {//bfs from player to all entities

    int count;
    Map<ActiveGridEntity, Signal> possible;
    Level level;
    Pathfinder(Level level) {
        this.level = level;
    }


    public void findPathMelee() {//bfs from player

        Queue<Position> q = new LinkedList<>() {
        };
        possible = new HashMap<>();
        boolean[][] visited = new boolean[level.getDimensions().columnCount()][level.getDimensions().rowCount()];

        for (int i = 0; i < level.getDimensions().columnCount(); i++) {
            Arrays.fill(visited[i], false);
        }
        visited[level.player.getPosition().getColumn()][level.player.getPosition().getRow()] = true;
        Position p1 = new Position(),p2 = new Position(),p3 = new Position(),p4 = new Position();
        p1.setGridPosition(level.player.getPosition().getRow() + 1, level.player.getPosition().getColumn());
        setupAttacks(visited, p1, q);
        p2.setGridPosition(level.player.getPosition().getRow(), level.player.getPosition().getColumn() + 1);
        setupAttacks(visited, p2, q);
        p3.setGridPosition(level.player.getPosition().getRow() - 1, level.player.getPosition().getColumn());
        setupAttacks(visited, p3, q);
        p4.setGridPosition(level.player.getPosition().getRow(), level.player.getPosition().getColumn() - 1);
        setupAttacks(visited, p4, q);
        boolean taken;
        ArrayList<Integer>per=new ArrayList<>();
        per.add(0);per.add(1);per.add(2);per.add(3);
        while (!q.isEmpty()) {
            p1 = q.remove();
            if(!level.getDimensions().contain(p1)){
                continue;
            }
            visited[p1.getColumn()][p1.getRow()]=true;

            Position adj1=new Position(),adj2=new Position(),adj3=new Position(),adj4=new Position();
            taken = false;
            Collections.shuffle(per);
            adj1.setGridPosition(p1.getRow(),p1.getColumn()-1);
            adj2.setGridPosition(p1.getRow(),p1.getColumn()+1);
            adj3.setGridPosition(p1.getRow()-1,p1.getColumn());
            adj4.setGridPosition(p1.getRow()+1,p1.getColumn());
            for(int i=0; i<4; i++){
                switch (per.get(i)){
                    case 0 -> {
                        taken = goForIt(q, visited, taken, adj1, p1);
                    }
                    case 1 -> {
                        taken = goForIt(q, visited, taken, adj2, p1);
                    }
                    case 2 -> {
                        taken = goForIt(q, visited, taken, adj3, p1);
                    }
                    case 3 -> {
                        taken = goForIt(q, visited, taken, adj4, p1);
                    }
                }
            }


        }

        System.out.println(possible);
    }

    private boolean goForIt(Queue<Position> q, boolean[][] visited, boolean taken, Position adj, Position p) {
        if(canMove(adj)){
            if(taken && getActive(adj)!= null)
            {
                q.offer(adj);
            }
            else{
                taken=setupMove(visited, adj, q, p);
            }
        }
        return taken;
    }

    private void setupAttacks(boolean[][] visited, Position p, Queue<Position> q) {
        if (!level.getDimensions().contain(p) || !canMove(p)) return;
        ActiveGridEntity go =getActive(p);
        visited[p.getColumn()][p.getRow()] = true;
        if (go != null) {
            if (p.getColumn() - 1 == level.player.getPosition().getColumn()) {
                possible.put(go, Signal.REQUESTED_LEFT_ATTACK);
            } else if (p.getColumn() + 1 == level.player.getPosition().getColumn()) {
                possible.put(go, Signal.REQUESTED_RIGHT_ATTACK);
            } else if (p.getRow() - 1 == level.player.getPosition().getRow()) {
                possible.put(go, Signal.REQUESTED_DOWN_ATTACK);
            } else if (p.getRow() + 1 == level.player.getPosition().getRow()) {
                possible.put(go, Signal.REQUESTED_UP_ATTACK);
            }
        }
        q.offer(p);

    }

    private boolean setupMove(boolean[][] visited, Position p, Queue<Position> q, Position last) {
        if (!level.getDimensions().contain(p)|| visited[p.getColumn()][p.getRow()]) return false;
        ActiveGridEntity go=getActive(p);
        visited[p.getColumn()][p.getRow()] = true;
        if (go != null) {
            if (p.getColumn() - 1 == last.getColumn()) {
                possible.put(go, Signal.REQUESTED_LEFT_MOVEMENT);
            } else if (p.getColumn() + 1 == last.getColumn()) {
                possible.put(go, Signal.REQUESTED_RIGHT_MOVEMENT);
            } else if (p.getRow() - 1 == last.getRow()) {
                possible.put(go, Signal.REQUESTED_DOWN_MOVEMENT);
            } else if (p.getRow() + 1 == last.getRow()) {
                possible.put(go, Signal.REQUESTED_UP_MOVEMENT);
            }
        }
        q.offer(p);
        return go!=null;
    }
    private boolean canMove(Position p){
        for(var i : level.passiveEntities){
            if(i.getPosition().equals(p)){
                return false;
            }
        }
        return true;
    }

    private ActiveGridEntity getActive(Position p) {
        List<GridEntity> a;
        ActiveGridEntity go;
        a = level.getGridEntitiesAtPosition(p);
        go = null;
        for (var i : a) {
            if (!i.getClass().isAssignableFrom(Projectile.class)&& i instanceof ActiveGridEntity ) {
                go = (ActiveGridEntity) i;
                break;
            }
        }
        return go;
    }
}