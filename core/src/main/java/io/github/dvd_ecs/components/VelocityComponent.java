package io.github.dvd_ecs.components;
import com.badlogic.ashley.core.Component;

public class VelocityComponent implements Component {
    public float vx;
    public float vy;
    public boolean bounced;

    public VelocityComponent(float vx, float vy){
        this.vx = vx;
        this.vy = vy;
        this.bounced = false;
    }
}
