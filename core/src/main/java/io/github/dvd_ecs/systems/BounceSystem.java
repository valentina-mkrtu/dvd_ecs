package io.github.dvd_ecs.systems;
import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import io.github.dvd_ecs.components.LogoComponent;
import io.github.dvd_ecs.components.PositionComponent;
import io.github.dvd_ecs.components.VelocityComponent;

public class BounceSystem extends IteratingSystem {
    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);
    private ComponentMapper<LogoComponent> lm = ComponentMapper.getFor(LogoComponent.class);
    private float width;
    private float height;


    public BounceSystem(float WORLD_WIDTH, float WORLD_HEIGHT) {
        super(Family.all(PositionComponent.class, VelocityComponent.class, LogoComponent.class).get());
        this.width = WORLD_WIDTH;
        this.height = WORLD_HEIGHT;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = pm.get(entity);
        VelocityComponent vel = vm.get(entity);
        LogoComponent logo = lm.get(entity);

        position.x+=vel.vx * deltaTime * 100;
        position.y+=vel.vy * deltaTime * 100;
        if (position.x >= width-logo.width){
            vel.vx = -vel.vx;
            vel.bounced = true;
        }
        else if(position.x <= logo.width/10){
            vel.vx = 2;
        }
        if(position.y >= height-logo.height){
            vel.vy = -vel.vy;
            vel.bounced = true;
        }
        else if(position.y <= logo.height/10){
            vel.vy = 2;
        }
    }
}

