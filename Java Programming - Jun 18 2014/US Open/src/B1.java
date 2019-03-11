import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class B1
{
	public static void main(final String[] args) throws IOException
	{
		final String fileN = "ballet";
		BufferedReader reader = new BufferedReader(new FileReader(new File(fileN+ ".in")));
		int steps = Integer.parseInt(reader.readLine());
		Dance dance = new Dance();
		PrintWriter writer = new PrintWriter(new FileWriter(new File(fileN +".out")));
		for(int stepN = 0; stepN < steps; stepN++)
		{
			String instr = reader.readLine();
			//System.out.println(dance.toString() + "\nX(" + dance.maxX + "," + dance.minX + ")" +"Y(" + dance.maxY + "," + dance.minY + ")");
			if(!dance.makeStep(instr))
			{
				System.out.println(dance.toString());
				writer.print("-1");
				writer.flush();
				writer.close();
				return;
			}
		}
		//System.out.println(dance.toString() + "\nX(" + dance.maxX + "," + dance.minX + ")" +"Y(" + dance.maxY + "," + dance.minY + ")");
		int area = Math.abs((dance.maxX-dance.minX+1)*(dance.maxY-dance.minY+1));
		writer.print(area);
		writer.flush();
		writer.close();
	}
}

class Dance {
	int flx;
	int fly;
	int frx;
	int fry;
	int blx;
	int bly;
	int brx;
	int bry;
	int dir;
	
	int maxX;
	int maxY;
	int minX;
	int minY;
	
	int[][] UP = {
			{0,1},{1,0},{0,-1},{-1,0}	
	};
	int[][] LF = {
			{1,0},{0,-1},{-1,0},{0,1}
	};
	int[][] DN = {
			{0,-1},{-1,0},{0,1},{1,0}
	};
	int[][] RT = {
			{1,0},{0,-1},{-1,0},{0,1}
	};
	
	public Dance()
	{
		flx = 0;
		fly = 1;
		frx = 1;
		fry = 1;
		blx = 0;
		bly = 0;
		brx = 1;
		bry = 0;
		
		dir = 0;
		maxX = 1;
		maxY = 1;
		minX = 0;
		minY = 0;
	}
	
	public boolean makeStep(String step)
	{
		boolean isfront = step.charAt(0) == 'F';
		boolean isleft = step.charAt(1) == 'L';
		char inst = step.charAt(2);
		if(inst == 'P')
		{
			if(isfront)
			{
				if(isleft)
					pivot(flx, fly);
				else
					pivot(frx, fry);
			}
			else 
			{

				if(isleft)
					pivot(blx, bly);
				else
					pivot(brx, bry);
			}
		} else
		{
			moveFoot(isfront, isleft, inst);
		}
		if((flx == frx && fly == fry) || (flx == blx && fly == bly) || (flx == brx && fly == bry)
				|| (frx == blx && fry == bly) || (frx == brx && fry == bry) || (blx == brx && bly == bry))
			return false;
		updateBounds();
		return true;
	}
	private void pivot(int x, int y)
	{
		int nflx = x+(fly-y);
		int nfly = y-(flx-x);
		flx = nflx;
		fly = nfly;

		int nfrx = x+(fry-y);
		int nfry = y-(frx-x);
		frx = nfrx;
		fry = nfry;

		int nblx = x+(bly-y);
		int nbly = y-(blx-x);
		blx = nblx;
		bly = nbly;

		int nbrx = x+(bry-y);
		int nbry = y-(brx-x);
		brx = nbrx;
		bry = nbry;
		
		dir = (dir+1)%4;
	}

	private void updateBounds()
	{
		maxX = Math.max(maxX, Math.max(Math.max(flx, frx), Math.max(blx, brx)));
		maxY = Math.max(maxY, Math.max(Math.max(fly, fry), Math.max(bly, bry)));
		minX = Math.min(minX, Math.min(Math.min(flx, frx), Math.min(blx, brx)));
		minY = Math.min(minY, Math.min(Math.min(fly, fry), Math.min(bly, bry)));
	}

	void moveFoot(boolean isFront, boolean isLeft, char dir)
	{
		if(isFront)
		{
			if(isLeft)
			{
				if(dir == 'F')
				{
					flx+= UP[this.dir][0];
					fly+= UP[this.dir][1];
				}
				else if(dir == 'R')
				{
					flx+= RT[this.dir][0];
					fly+= RT[this.dir][1];
				}
				else if(dir == 'L')
				{
					flx+= LF[this.dir][0];
					fly+= LF[this.dir][1];
				}
				else if(dir == 'B')
				{
					flx+= DN[this.dir][0];
					fly+= DN[this.dir][1];
				}
			}
			else
			{
				if(dir == 'F')
				{
					frx+= UP[this.dir][0];
					fry+= UP[this.dir][1];
				}
				else if(dir == 'R')
				{
					frx+= RT[this.dir][0];
					fry+= RT[this.dir][1];
				}
				else if(dir == 'L')
				{
					frx+= LF[this.dir][0];
					fry+= LF[this.dir][1];
				}
				else if(dir == 'B')
				{
					frx+= DN[this.dir][0];
					fry+= DN[this.dir][1];
				}
			}
		} else
		{
			if(isLeft)
			{
				if(dir == 'F')
				{
					blx+= UP[this.dir][0];
					bly+= UP[this.dir][1];
				}
				else if(dir == 'R')
				{
					blx+= RT[this.dir][0];
					bly+= RT[this.dir][1];
				}
				else if(dir == 'L')
				{
					blx+= LF[this.dir][0];
					bly+= LF[this.dir][1];
				}
				else if(dir == 'B')
				{
					blx+= DN[this.dir][0];
					bly+= DN[this.dir][1];
				}
			}
			else
			{
				if(dir == 'F')
				{
					brx+= UP[this.dir][0];
					bry+= UP[this.dir][1];
				}
				else if(dir == 'R')
				{
					brx+= RT[this.dir][0];
					bry+= RT[this.dir][1];
				}
				else if(dir == 'L')
				{
					brx+= LF[this.dir][0];
					bry+= LF[this.dir][1];
				}
				else if(dir == 'B')
				{
					brx+= DN[this.dir][0];
					bry+= DN[this.dir][1];
				}
			}
		}
	}
	public String toString()
	{
		String output = "FL(" + flx + "," + fly + ")" +"FR(" + frx + "," + fry + ")" + "BL(" + blx + "," + bly + ")" + "BR(" + brx + "," + bry + ")";
		return output;
	}
}
