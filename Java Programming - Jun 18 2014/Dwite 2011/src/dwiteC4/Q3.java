package dwiteC4;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Q3
{
	public static void main(final String[] args) throws Exception
	{
		long time = System.currentTimeMillis();
		BufferedReader reader = new BufferedReader(new FileReader(new File("c4/DATA3.TXT")));
		PrintWriter writer = new PrintWriter(new FileWriter(new File("c4/OUT3.TXT")));
		Scanner scan = new Scanner(reader);
		for(int i = 0; i < 5; i++)
		{
			Scanner scan1 = new Scanner(scan.nextLine());
			int size = scan1.nextInt();
			int bonds = scan1.nextInt();
			int broken = 0;
			Compound compound = new Compound(size, bonds);
			for(int j = 0; j < bonds; j++)
			{
				Scanner scan2 = new Scanner(scan.nextLine());
				int carbon1 = scan2.nextInt();
				int carbon2 = scan2.nextInt();
				compound.getBondList().add(new Bond(carbon1, carbon2));
			}
			for(int k = 0; k < compound.getBonds(); k++)
			{
				Bond bond = compound.getBondList().get(k);
				compound.remove(k);
				if(!Compound.isConnected(compound)) broken++;
				compound.add(k, bond);
			}
			writer.println(broken);
			writer.flush();
		}
		long timeDur = System.currentTimeMillis()-time;
		System.out.println(timeDur);
		System.exit((int) timeDur);
	}
}

class Bond
{
	private int carbon1;
	private int carbon2;
	Bond(int carbon1, int carbon2)
	{
		setCarbon1(carbon1);
		setCarbon2(carbon2);
	}
	public int getCarbon2()
	{
		return carbon2;
	}
	public void setCarbon2(int carbon2)
	{
		this.carbon2 = carbon2;
	}
	public int getCarbon1()
	{
		return carbon1;
	}
	public void setCarbon1(int carbon1)
	{
		this.carbon1 = carbon1;
	}
}

class Compound
{
	private ArrayList<Bond> bondList;
	private int size;
	private int bonds;
	
	
	Compound(int size, int bonds)
	{
		setBonds(bonds);
		setSize(size);
		setBondList(new ArrayList<Bond>());
	}
	Compound(int size, int bonds, ArrayList<Bond> bondList)
	{
		setBonds(bonds);
		setSize(size);
		setBondList(bondList);
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	public ArrayList<Bond> getBondList()
	{
		return bondList;
	}

	public void setBondList(ArrayList<Bond> bondList)
	{
		this.bondList = bondList;
	}

	public int getBonds()
	{
		return bonds;
	}

	public void setBonds(int bonds)
	{
		this.bonds = bonds;
	}
	
	public void add(int index, Bond bond)
	{
		bondList.add(index, bond);
		bonds++;
	}
	public void remove(int index)
	{
		bondList.remove(index);
		bonds--;
	}
	public void removeBond(int carbon1, int carbon2)
	{
		for(int i = 0; i < bonds; i++)
		{
			Bond bond = bondList.get(i);
			if(carbon1 == bond.getCarbon1())
			{
				if(carbon2 == bond.getCarbon2())
				{
					bondList.remove(i);
					bonds--;
					return;
				}
			} else if(carbon1 == bond.getCarbon2())
			{
				if(carbon2 == bond.getCarbon1())
				{
					bondList.remove(i);
					bonds--;
					return;
				}
			}
		}
	}
	public static boolean isConnected(Compound compound)
	{
		ArrayList<Integer> numList = new ArrayList<Integer>();
		numList.add(1);
		for(int n = 0; n < numList.size(); n++)
		{
			int integ = numList.get(n);
			for(int i = 0; i < compound.getBonds(); i++)
			{
				if(compound.getBondList().get(i).getCarbon1() == integ)
				{
					if(!numList.contains(new Integer(compound.getBondList().get(i).getCarbon2()))) {
						numList.add(compound.getBondList().get(i).getCarbon2());
					}
				}
				if(compound.getBondList().get(i).getCarbon2() == integ)
				{
					if(!numList.contains(new Integer(compound.getBondList().get(i).getCarbon1()))) {
						numList.add(compound.getBondList().get(i).getCarbon1());
					}
				}
			}
		}
		if(numList.size() != compound.getSize()) return false;
		return true;
		
		/*
		boolean[] connected = new boolean[compound.getSize()];
		connected[0] = true;
		ArrayList<Bond> tempList = new ArrayList<Bond>();
		for(Bond bond : compound.getBondList())
		{
			if(connected[bond.getCarbon1()-1] || connected[bond.getCarbon2()-1])
			{
				connected[bond.getCarbon1()-1] = true;
				connected[bond.getCarbon2()-1] = true;
			} else if(!connected[bond.getCarbon1()-1] && !connected[bond.getCarbon2()-1])
			{
				tempList.add(bond);
			}
		}	
		for(int i = tempList.size()-1; i >= 0; i--)
		{
			Bond bond = tempList.get(i);
			if(connected[bond.getCarbon1()-1] || connected[bond.getCarbon2()-1])
			{
				connected[bond.getCarbon1()-1] = true;
				connected[bond.getCarbon2()-1] = true;
			} else if(!connected[bond.getCarbon1()-1] && !connected[bond.getCarbon2()-1])
			{
				tempList.add(bond);
			}
		}
		
		/*boolean changed = true;		
		Bond bond1 = compound.getBondList().get(0);
		connected[bond1.getCarbon1()-1] = true;
		connected[bond1.getCarbon2()-1] = true;
		
		while(changed) {
			changed = false;
			for(int i = 0; i < compound.bonds; i++)
			{
				Bond bond = compound.getBondList().get(i);
				if(!(connected[bond.getCarbon1()-1] && connected[bond.getCarbon2()-1])) {
					for(int j = 0; j < compound.getSize(); j++)
					{
						if(connected[j] && (j == bond.getCarbon1()-1 && connected[bond.getCarbon1()-1] && !connected[bond.getCarbon2()-1]) || 
						(j == bond.getCarbon2()-1 && connected[bond.getCarbon2()-1] && !connected[bond.getCarbon1()-1]))
						{
							connected[bond.getCarbon1()-1] = true;
							connected[bond.getCarbon2()-1] = true;
							changed = true;
						}
					}
				}
			}
			for(boolean bool : connected)
			{
				if(!bool && !changed) 
					return false;
			}
			if(!changed) 
				return true;
		}*//*
		for(boolean bool : connected)
		{
			if(!bool) 
				return false;
		}
		return true;*/
	}
}
