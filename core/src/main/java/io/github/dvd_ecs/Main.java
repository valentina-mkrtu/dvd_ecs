package io.github.dvd_ecs;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.ComponentMapper;

import com.badlogic.ashley.systems.IteratingSystem;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.dvd_ecs.components.*;
import io.github.dvd_ecs.systems.*;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {

    private Engine engine;
    private Batch batch;
    private String logo;
    private OrthographicCamera camera;
    private Viewport viewport;
    public static final float WORLD_WIDTH = 1280;
    public static final float WORLD_HEIGHT = 720;

    @Override
    public void create() {
        engine = new Engine();
        batch = new SpriteBatch();
        logo = "dvd.png";
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        viewport.apply();

        engine.addSystem(new DeathSystem());
        engine.addSystem(new DamageSystem(WORLD_WIDTH, WORLD_HEIGHT, 10));
        engine.addSystem(new BounceSystem(WORLD_WIDTH, WORLD_HEIGHT));
        engine.addSystem(new RenderSystem(batch));
        engine.addSystem(new HealthBarRenderSystem(batch));

        Entity dvd = new Entity();
        dvd.add(new LogoComponent(logo));
        dvd.add(new PositionComponent(0, 0));
        dvd.add(new VelocityComponent(100, 100));
        dvd.add(new HealthComponent(100));
        dvd.add(new HealthBarComponent(WORLD_WIDTH - 250, WORLD_HEIGHT - 70, 200, 20));

        engine.addEntity(dvd);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0,0,0,1);
        viewport.apply();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        engine.update(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }
}
