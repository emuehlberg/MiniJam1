package com.gamejam.engine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureMap
{
	public TextureRegion[][] map;

	public TextureMap()
	{
		map = null;
	}
	
	public TextureMap(Texture t, int rw, int rh, int columns,int rows)
	{
		map = new TextureRegion[columns][rows];
		
		for(int x = 0; x<columns; x++)
		{
			for(int y = 0; y<rows; y++)
			{
				map[x][y]=new TextureRegion(t, x*rw, y*rh, rw, rh);
			}
		}
		
	}
	
	public TextureRegion getRegion(int x, int y)
	{
		return map[x][y];
	}
	
}
