package pl.ue.oops.game.universe.level;

import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.entities.*;
import pl.ue.oops.game.universe.entities.general.ActiveGridEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.entities.general.Projectile;
import pl.ue.oops.game.universe.utils.GridPosition;

import javax.swing.text.Position;
import java.util.*;

public class Pathfinder {//bfs from player to all entities

    int count;
    Map<ActiveGridEntity, Signal> possible;

    Level level;
    Pathfinder(Level level) {
        this.level = level;
    }


    public void findPathMelee() {//bfs from player

        Queue<GridPosition> q = new LinkedList<>() ;
        possible = new HashMap<>();
        boolean[][] visited = new boolean[level.getDimensions().columnCount()][level.getDimensions().rowCount()];

        for (int i = 0; i < level.getDimensions().columnCount(); i++) {
            Arrays.fill(visited[i], false);
        }
        visited[level.player.getPosition().getColumn()][level.player.getPosition().getRow()] = true;
        GridPosition p1 = new GridPosition(),p2 = new GridPosition(),p3 = new GridPosition(),p4 = new GridPosition();
        p1.set(level.player.getPosition().getRow() + 1, level.player.getPosition().getColumn());
        setupAttacks(visited, p1, q);
        p2.set(level.player.getPosition().getRow(), level.player.getPosition().getColumn() + 1);
        setupAttacks(visited, p2, q);
        p3.set(level.player.getPosition().getRow() - 1, level.player.getPosition().getColumn());
        setupAttacks(visited, p3, q);
        p4.set(level.player.getPosition().getRow(), level.player.getPosition().getColumn() - 1);
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

            GridPosition adj1=new GridPosition(),adj2=new GridPosition(),adj3=new GridPosition(),adj4=new GridPosition();
            taken = false;
            Collections.shuffle(per);
            adj1.set(p1.getRow(),p1.getColumn()-1);
            adj2.set(p1.getRow(),p1.getColumn()+1);
            adj3.set(p1.getRow()-1,p1.getColumn());
            adj4.set(p1.getRow()+1,p1.getColumn());
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
        makeShootersShoot(possible);




        System.out.println(possible);
    }

    private void makeShootersShoot(Map<ActiveGridEntity, Signal> possible){
        GridPosition up= new GridPosition(level.player.getPosition().getRow()+1,level.player.getPosition().getColumn());
        GridPosition down= new GridPosition(level.player.getPosition().getRow()-1,level.player.getPosition().getColumn());
        GridPosition right= new GridPosition(level.player.getPosition().getRow(),level.player.getPosition().getColumn()+1);
        GridPosition left= new GridPosition(level.player.getPosition().getRow(),level.player.getPosition().getColumn()-1);

        while(up.getRow()<16&&canShoot(up)){
            if(getActive(up) instanceof Shooter){
                possible.put(getActive(up), Signal.REQUESTED_DOWN_ATTACK);
            }
            up.set(up.getRow()+1, up.getColumn());
        }
        while(down.getRow()>=0 && canShoot(down)){
            if(getActive(down) instanceof Shooter){
                possible.put(getActive(down), Signal.REQUESTED_UP_ATTACK);
            }
            down.set(down.getRow()-1, down.getColumn());
        }
        while(right.getColumn()<24 && canShoot(right)){
            if(getActive(right) instanceof Shooter){
                possible.put(getActive(right), Signal.REQUESTED_LEFT_ATTACK);
            }
            right.set(right.getRow(), right.getColumn()+1);
        }
        while(left.getColumn()>=0 && canShoot(left)){
            if(getActive(left) instanceof Shooter){
                possible.put(getActive(left), Signal.REQUESTED_RIGHT_ATTACK);
            }
            left.set(left.getRow(), left.getColumn()-1);
        }
    }

    private boolean goForIt(Queue<GridPosition> q, boolean[][] visited, boolean taken, GridPosition adj, GridPosition p) {
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

    private void setupAttacks(boolean[][] visited, GridPosition p, Queue<GridPosition> q) {
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

    private boolean setupMove(boolean[][] visited, GridPosition p, Queue<GridPosition> q, GridPosition last) {
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
    private boolean canMove(GridPosition p){
        for(var i : level.passiveEntities){
            if(i.getPosition().equals(p) && (i instanceof LakeEntity || i instanceof RockEntity)){
                return false;
            }
        }
        return true;
    }
    private boolean canShoot(GridPosition p){
        for(var i : level.passiveEntities){
            if(i.getPosition().equals(p) && (i instanceof RockEntity)){
                return false;
            }
        }
        return true;
    }

    private ActiveGridEntity getActive(GridPosition p) {
        List<GridEntity> a;
        ActiveGridEntity go;
        a = level.getGridEntitiesAtPosition(p);
        go = null;
        for (var i : a) {
            if (!i.getClass().isAssignableFrom(Projectile.class) && (i instanceof Clueless || i instanceof Shooter)) {
                go = (ActiveGridEntity) i;
                break;
            }
        }
        return go;
    }
}
