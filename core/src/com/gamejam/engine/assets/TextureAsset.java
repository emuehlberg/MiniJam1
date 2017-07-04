package com.gamejam.engine.assets;

import com.badlogic.gdx.graphics.Texture;

public class TextureAsset extends Asset
{
	
	private Texture texture;
	
	public TextureAsset(String name, Texture texture)
	{
		super(name);
		this.texture = texture;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Texture getAsset()
	{
		refCount++;
		return texture;
	}

	@Override
	public void dispose()
	{
		texture.dispose();
	}

}
