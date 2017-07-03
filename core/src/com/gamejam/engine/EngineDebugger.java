package com.gamejam.engine;

import java.lang.reflect.Field;

public class EngineDebugger
{
	public EngineDebugger()
	{
		
	}
	
	public void Inspect(Object o)
	{
		Inspect(o,1);
	}
	
	public static void Inspect(Object o, int depth)
	{
		for(int x = 0;x<depth;x++)
		{
			System.out.print("-");
		}
		System.out.println(">"+o.getClass().getSimpleName()+":");
		for(Field f:o.getClass().getFields())
		{
			try
			{
					for(int x = 0;x<depth+1;x++)
					{
						System.out.print("-");
					}
					System.out.println(">"+f.getName()+": "+f.get(o));

			} catch (IllegalArgumentException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
