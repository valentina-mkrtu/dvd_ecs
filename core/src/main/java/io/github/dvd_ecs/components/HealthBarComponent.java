package io.github.dvd_ecs.components;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

public class HealthBarComponent implements Component{
    public float x;
    public float y;
    public float width;
    public float height;

    public HealthBarComponent(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
