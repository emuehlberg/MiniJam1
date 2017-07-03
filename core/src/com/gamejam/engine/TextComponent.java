package com.gamejam.engine;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Align;

public class TextComponent implements Component
{
	public String text;
	public float width;
	public Color c;
	public int align;
	
	public TextComponent(String t, float width)
	{
		this.text = t;
		this.width = width;
		align = Align.left;
		c = Color.BLACK;
	}
	
	public TextComponent(String t, float width, Color c)
	{
		this.text = t;
		this.c = c;
		this.width = width;
		align = Align.left;
	}
}
