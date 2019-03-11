package toh.com;

import java.io.Console;

public class StackController {
	static Towers start;
	static Towers mid;
	static Towers end;
	private static final int maxHeight = 15;
	
	public static void main(String[] args)
	{
		int size = maxHeight;
		start = new Towers(size, true);
		mid = new Towers(size, false);
		end = new Towers(size, false);
		int s = 1;
		for(int i = 0; i < size; i++){
			s *= 2;
		}
		s -= 1;
		for(;;){
			String a; String b; String e;
			int c = 0; int d = 0; int f = 0;
			int u = 0; int v = 0; int w = 0; int x = 0; int y = 0; int z = 0;
			Boolean playing = true;
			Console console = System.console();
			System.out.println("Towers of Hanoi. Game starting now with " + size + " layers.");
			for(int r = 0;playing;) {
				x = start.getHeight();
				y = mid.getHeight();
				z = end.getHeight();
				u = start.getSize();
				v = mid.getSize();
				w = end.getSize();
				System.out.println("Towers\t\t-Tower 1-\t-Tower 2-\t-Tower 3-\n" +
						"Layers\t\t-" + x + "-\t\t-" + y + "-\t\t-" + z + "-\n" +
						"Size of Top\t-" + u + "-\t\t-" + v + "-\t\t-" + w + "-");
				a = console.readLine("\nPlease Enter the Number of the Tower you wish to move from: ");
				try {
					c = Integer.parseInt(a);
					b = console.readLine("\nand the Number of the Tower you wish to move to: ");
					try {
						d = Integer.parseInt(b);
					} catch (Exception g) {
						d = 0;
					}
				} catch (Exception g) {
					c = 0;
				}
				if(c == 0 || d == 0 || (c == d)){
					System.out.println("Incorrect Tower Number(s)!!!");
				} else {
					if(moveLayer(c, d)) {
						System.out.println("Layer moved.");
						r++;
					}
				}
				if(end.getHeight() == size){
					System.out.println("Game over!!! You have won the game in " + r + " moves!\n" +
							"The shortest possible solution for your tower size is: " + s);
					e = console.readLine("Continue??? 1 = Yes, Anything else =  No: ");
					try {
						f = Integer.parseInt(e);
					} catch (Exception g) {
						f = 0;
					}
					if(f != 1) {
						System.exit(-1);
					}
				}
			}
		}
	}
	
	static Boolean moveLayer(int a, int b)
	{
		int c = 0;
		Boolean success = false;
		switch(a) {
		case 1:
			c = start.takeItem();
			break;
		case 2:
			c = mid.takeItem();
			break;
		case 3:
			c = end.takeItem();
			break;
		}
		switch(b) {
		case 1:
			success = start.putItem(c);
			break;
		case 2:
			success = mid.putItem(c);
			break;
		case 3:
			success = end.putItem(c);
			break;
		}
		if(success != true) {
			System.out.println("Illegal move.");
			switch(a) {
			case 1:
				start.putItem(c);
				break;
			case 2:
				mid.putItem(c);
				break;
			case 3:
				end.putItem(c);
				break;
			}
		}
		return success;
	}
}
