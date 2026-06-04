package io.github.dvd_ecs.components;
import com.badlogic.ashley.core.Component;

public class HealthComponent implements Component{
    public float hp;

    public HealthComponent(float hp){
        this.hp = hp;
    }
}
