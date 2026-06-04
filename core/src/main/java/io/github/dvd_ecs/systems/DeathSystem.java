package io.github.dvd_ecs.systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import io.github.dvd_ecs.components.HealthComponent;
import io.github.dvd_ecs.components.PositionComponent;
import io.github.dvd_ecs.components.VelocityComponent;

public class DeathSystem extends IteratingSystem{

    public ComponentMapper<HealthComponent> hc = ComponentMapper.getFor(HealthComponent.class);
    public ComponentMapper<PositionComponent> pc = ComponentMapper.getFor(PositionComponent.class);
    public ComponentMapper<VelocityComponent> vc = ComponentMapper.getFor(VelocityComponent.class);

    public DeathSystem(){
        super(Family.all(HealthComponent.class, PositionComponent.class, VelocityComponent.class).get());
    }
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        HealthComponent hp = hc.get(entity);
        PositionComponent pos = pc.get(entity);
        VelocityComponent vel = vc.get(entity);

        if (hp.hp <= 0){
            pos.x = 0;
            pos.y = 0;
            vel.vx -= 2;
            vel.vy -= 2;
            hp.hp = 100;
            vel.bounced = false;
        }
    }
}
