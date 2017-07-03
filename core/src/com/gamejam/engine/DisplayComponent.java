package com.gamejam.engine;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DisplayComponent implements Component
{
	public TextureRegion texreg;
	
	public DisplayComponent()
	{
		texreg = new TextureRegion();
		
	}
	
	public DisplayComponent(Texture t)
	{
		this.texreg = new TextureRegion(t);
	}
	
	public DisplayComponent(TextureRegion r)
	{
		this.texreg = r;
	}
	
	public TextureRegion getTexture()
	{
		return texreg;
	}
}
