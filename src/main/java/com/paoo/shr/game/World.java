package com.paoo.shr.game;

import com.paoo.shr.engine.graphics.Drawable;
import com.paoo.shr.game.camera.Camera;
import com.paoo.shr.game.entities.Actor;
import com.paoo.shr.game.entities.Entity;
import com.paoo.shr.game.entities.enemies.Enemy;
import com.paoo.shr.game.entities.enemies.TestEnemy;
import com.paoo.shr.game.entities.npcs.Npc;
import com.paoo.shr.game.entities.npcs.TestNpc;
import com.paoo.shr.game.entities.player.Player;
import com.paoo.shr.game.map.Map;
import com.paoo.shr.math.Compare;
import com.paoo.shr.math.Rng;
import com.paoo.shr.math.Vector2;
import com.paoo.shr.engine.core.Window;

import java.util.*;

public class World implements Drawable {

    // Members
    private Player player;
    private Map map;
    private Camera camera;

    private TreeSet<Actor> actors = new TreeSet<>(Comparator.comparingDouble(Entity::getY));

    public World() {
        super();

        this.map = new Map();
        map.loadLevel1Map();

        this.player = new Player(100, 0, 5, new Vector2(50/2.0, 20/2.0), 1);
        actors.add(player);
        this.camera = new Camera();

        spawnTestNpc();
        spawnTestEnemy();
    }

    public Player getPlayer() {
        return player;
    }

    public Map getMap() {
        return map;
    }


    @Override
    public void draw(javafx.scene.canvas.GraphicsContext gc) {
        var camPos = camera.getCamPos();

        gc.translate(-camPos.getX(),-camPos.getY());
        map.draw(gc);

        for (var actor : actors) {
            actor.draw(gc);
        }

        player.draw(gc);

    }

    public void moveCamera(Vector2 playerDir) {
        camera.moveBy(playerDir);
    }

    // TODO: NPC FACTORY

    public void spawnTestNpc() {
        float randX = Math.abs((Rng.randFloat() % Map.MAX_X) - TestNpc.SIZE.getX());
        float randY = Math.abs((Rng.randFloat() % Map.MAX_Y) - TestNpc.SIZE.getY());

        actors.add(new TestNpc(1, new Vector2(randX, randY)));
    }

    public void spawnTestEnemy() {
        float randX = Math.abs((Rng.randFloat() % Map.MAX_X) - TestEnemy.SIZE.getX());
        float randY = Math.abs((Rng.randFloat() % Map.MAX_Y) - TestEnemy.SIZE.getY());

        actors.add(new TestEnemy(1, new Vector2(randX, randY)));
    }

}