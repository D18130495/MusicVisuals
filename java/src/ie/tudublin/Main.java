package ie.tudublin;

import D18130495.YushunVisual;
import D18130495.Control;

public class Main
{	
	Control control = new Control();

	public void control()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, control);		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.control();
		main.control.startUI();
	}
}