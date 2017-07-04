package com.gamejam.engine.assets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
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
		Color ct = c;
		t.getTextureData().prepare();
		Pixmap map = t.getTextureData().consumePixmap();
		Pixmap tmap = new Pixmap(t.getWidth(),t.getHeight(),Format.RGBA8888);
		
		for(int x = 0; x<map.getWidth();x++)
		{
			for(int y = 0; y<map.getHeight();y++)
			{
				if(!compareColor(c,map.getPixel(x,y)))
				{
					tmap.drawPixel(x, y, map.getPixel(x,y));
				}
			}
		}
		
		map.dispose();
		t.dispose();
		Texture ret = new Texture(tmap, Format.RGBA8888, false);
		tmap.dispose();
		return ret;
	}
	
	private boolean compareColor(Color c, int pixel)
	{
		Color pcolor = new Color(pixel);
		if(c.equals(pcolor))
			return true;
		return false;
	}

}
