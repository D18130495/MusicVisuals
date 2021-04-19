package ie.tudublin;

import D18130495.YushunVisual;
import D18130495.Control;

public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new YushunVisual());		
	}

	public void control()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Control());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.startUI();
		main.control();			
	}
}