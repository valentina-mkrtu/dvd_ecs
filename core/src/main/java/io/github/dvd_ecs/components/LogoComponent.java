package io.github.dvd_ecs.components;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

public class LogoComponent implements Component{
    public String path;
    public Texture texture;
    public float width;
    public float height;

    public LogoComponent(String path){
        this.path = path;
        this.texture = new Texture(path);
        this.width = texture.getWidth();
        this.height = texture.getHeight();
    }
}
