package io.github.dvd_ecs.systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import io.github.dvd_ecs.components.HealthComponent;
import io.github.dvd_ecs.components.LogoComponent;
import io.github.dvd_ecs.components.PositionComponent;
import io.github.dvd_ecs.components.VelocityComponent;

public class DamageSystem extends IteratingSystem{

    public ComponentMapper<HealthComponent> hc = ComponentMapper.getFor(HealthComponent.class);
    public ComponentMapper<PositionComponent> pc = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<LogoComponent> lm = ComponentMapper.getFor(LogoComponent.class);
    private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);
    private float width;
    private float height;
    public float dmg;

    public DamageSystem(float width, float height, float dmg) {
        super(Family.all(HealthComponent.class, PositionComponent.class, LogoComponent.class, VelocityComponent.class).get());
        this.width = width;
        this.height = height;
        this.dmg = dmg;
    }
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        HealthComponent hp = hc.get(entity);
        PositionComponent pos = pc.get(entity);
        LogoComponent logo = lm.get(entity);
        VelocityComponent v = vm.get(entity);

        if(pos.x >= width-logo.width || pos.x <= logo.width/10 || pos.y >= height-logo.height || pos.y <= logo.height/10) {
            if (v.bounced) {
                hp.hp -= dmg;
            }
        }

    }
}
