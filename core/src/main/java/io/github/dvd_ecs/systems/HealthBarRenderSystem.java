package io.github.dvd_ecs.systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import io.github.dvd_ecs.components.HealthBarComponent;
import io.github.dvd_ecs.components.HealthComponent;

public class HealthBarRenderSystem extends IteratingSystem {
    public Batch batch;

    public ComponentMapper<HealthBarComponent> hm =
        ComponentMapper.getFor(HealthBarComponent.class);

    public ComponentMapper<HealthComponent> hpm =
        ComponentMapper.getFor(HealthComponent.class);

    private Texture whiteTexture;
    private Texture pinkTexture;

    public HealthBarRenderSystem(Batch batch) {
        super(Family.all(HealthBarComponent.class, HealthComponent.class).get());
        this.batch = batch;

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        whiteTexture = new Texture(pixmap);
        pixmap.dispose();

        Pixmap pixmap2 = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap2.setColor(Color.PINK);
        pixmap2.fill();
        pinkTexture = new Texture(pixmap2);
        pixmap2.dispose();
    }

    @Override
    public void update(float deltaTime){
        batch.begin();
        super.update(deltaTime);
        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        HealthBarComponent hb = hm.get(entity);
        HealthComponent hp = hpm.get(entity);
        batch.draw(whiteTexture, hb.x, hb.y, hb.width, hb.height);

        int cubes = Math.min(10, (int)hp.hp / 10);
        float padding = 2;
        float cubeGap = 1;
        float cubeCount = 10;
        float cubeWidth = (hb.width - padding * 2 - cubeGap * 9) / cubeCount;
        float cubeHeight = hb.height - padding * 2;

        for (int i = 0; i < cubes; i++) {
            float x = hb.x + padding + i * (cubeWidth + cubeGap);
            float y = hb.y + padding;

            batch.draw(pinkTexture, x, y, cubeWidth, cubeHeight);
        }
    }
}
