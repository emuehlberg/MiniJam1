package com.gamejam.engine;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DisplayComponent implements Component
{
	public TextureRegion texreg;
	public double rotation;
	
	public DisplayComponent()
	{
		texreg = new TextureRegion();
		rotation = 0;
	}
	
	public DisplayComponent(Texture t)
	{
		this.texreg = new TextureRegion(t);
		rotation = 0;
	}
	
	public DisplayComponent(TextureRegion r)
	{
		this.texreg = r;
		rotation = 0;
	}
	
	public Texture getTexture()
	{
		return texreg.getTexture();
	}
	
	public TextureRegion getRegion()
	{
		return texreg;
	}
}
