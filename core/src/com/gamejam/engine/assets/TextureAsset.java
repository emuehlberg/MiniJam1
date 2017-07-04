package com.gamejam.engine.assets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
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

	public TextureAsset(String name, Texture texture, Color transparentcolor)
	{
		super(name);
		this.texture = adjustTransparency(transparentcolor, texture);
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
	
	private Texture adjustTransparency(Color c, Texture t)
	{
		Texture texture = t;
		Color ct = c;
		ct.a = 0;
		Pixmap map = new Pixmap(t.getWidth(), t.getHeight(), t.getTextureData().getFormat());
		
		for(int x = 0; x<map.getWidth();x++)
		{
			for(int y = 0; y<map.getHeight();y++)
			{
				if(compareColor(c,map.getPixel(x,y)))
				{
					map.drawPixel(x, y, ct);
				}
			}
		}
		
		texture.draw(map, 0, 0);
		return texture;
	}
	
	private boolean compareColor(Color c, int pixel)
	{
		Color pcolor = new Color(pixel);
		if(c.toIntBits()==pcolor.toIntBits())
			return true;
		return false;
	}

}
