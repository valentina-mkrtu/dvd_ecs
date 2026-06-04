package io.github.dvd_ecs.systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Batch;
import io.github.dvd_ecs.components.LogoComponent;
import io.github.dvd_ecs.components.PositionComponent;

public class RenderSystem extends IteratingSystem{

    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<LogoComponent> lm = ComponentMapper.getFor(LogoComponent.class);
    private Batch batch;


    public RenderSystem(Batch batch)
    {
        super(Family.all(PositionComponent.class, LogoComponent.class).get());
        this.batch = batch;
    }

    @Override
    public void update(float deltaTime){
        batch.begin();
        super.update(deltaTime);
        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = pm.get(entity);
        LogoComponent logo = lm.get(entity);

        batch.draw(logo.texture, position.x, position.y);
    }
}
