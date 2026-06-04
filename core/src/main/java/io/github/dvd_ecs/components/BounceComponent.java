package io.github.dvd_ecs.components;
import com.badlogic.ashley.core.Component;

public class BounceComponent implements Component{
    public boolean Bounces;

    public BounceComponent(boolean Bounces){
        this.Bounces = Bounces;
    }
}
