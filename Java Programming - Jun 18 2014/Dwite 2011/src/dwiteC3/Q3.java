package dwiteC3;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Q3
{
	public static void main(String[] args)
	{
		BufferedReader reader;
		PrintWriter writer;
		try {
			reader = new BufferedReader(new FileReader(new File("c3/DATA3.TXT")));
			writer = new PrintWriter(new FileWriter(new File("c3/OUT3.TXT")));
			Scanner scan = new Scanner(reader);
			for(int i = 0; i < 5; i++) {
				String dealNumLine = scan.nextLine();
				int dealCount = Integer.parseInt(dealNumLine);
				ArrayList<DisCount> discounts = new ArrayList<DisCount>();
				ArrayList<Order> orders = new ArrayList<Order>();
				for(int j = 0; j < dealCount; j++) {
					discounts.add(new DisCount(scan.nextLine()));
				}
				String orderNumLine = scan.nextLine();
				int orderCount = Integer.parseInt(orderNumLine);
				for(int j = 0; j < orderCount; j++) {
					orders.add(new Order(scan.nextLine()));
				}
				for(Order order : orders) {
					ArrayList<DisCount> discountVals = new ArrayList<DisCount>();
					if(discounts.size() == 0) writer.println(0);
					else {
						for(DisCount discount : discounts) {
							if(discount.isElegible(order)) {
								discountVals.add(discount);
							}
						}
						if(discountVals.size() == 1) {
							writer.println(discountVals.get(0).getDiscountVal());
						}
						else {
							ArrayList<Integer> maxDiscount = new ArrayList<Integer>();
							int disc = 0;
							for(int x = 0; x < discountVals.size(); x++) {
								if(maxDiscount.size() == 0) maxDiscount.add(x);
								else {
									ArrayList<String> orderItems = order.getItems();
									for(int j = 0; j < discountVals.size(); j++) {
										if(discountVals.get(maxDiscount.get(j)).getDiscountVal() < discountVals.get(x).getDiscountVal()) {
											maxDiscount.add(j, x);
											break;
										}
									}
									for(int j = 0; j < maxDiscount.size(); j++) {
										if(discountVals.get(maxDiscount.get(j)).isElegible(order)) {
											ArrayList<String> itemList = discountVals.get(maxDiscount.get(j)).getItems();
											for(String str : itemList) {
												orderItems.remove(orderItems.indexOf(str));
											}
											disc += discountVals.get(maxDiscount.get(j)).getDiscountVal();
										}
									}
								}
							}
							writer.println(disc);
						}
					}
					writer.flush();
				}
			}
		} catch (IOException ioe) {
			System.out.println("IO Error!");
		}
	}
}

class DisCount {
	private int discountVal;
	private int itemNum;
	private ArrayList<String> items;
	DisCount(String str) {
		items = new ArrayList<String>();
		String[] strings = str.split(" ");
		setDiscountVal(Integer.parseInt(strings[0]));
		setItemNum(Integer.parseInt(strings[1]));
		for(int i = 0; i < itemNum; i++) {
			items.add(strings[2+i]);
		}
	}
	public int getDiscountVal()
	{
		return discountVal;
	}
	public void setDiscountVal(int discountVal)
	{
		this.discountVal = discountVal;
	}
	public int getItemNum()
	{
		return itemNum;
	}
	public void setItemNum(int itemNum)
	{
		this.itemNum = itemNum;
	}
	public ArrayList<String> getItems()
	{
		return items;
	}
	public void setItems(ArrayList<String> items)
	{
		this.items = items;
	}
	public boolean isElegible(Order order) {
		for(int i = 0; i < itemNum; i++) {
			if(!order.getItems().contains(items.get(i))) {
				return false;
			}
		}
		return true;
	}
}
class Order {
	private int itemNum;
	private ArrayList<String> items;
	Order(String str) {
		items = new ArrayList<String>();
		Scanner scan = new Scanner(str);
		String[] strings = str.split(" ");
		setItemNum(Integer.parseInt(strings[0]));
		for(int i = 1; i <= itemNum; i++) {
			items.add(strings[i]);
		}
	}
	public ArrayList<String> getItems()
	{
		return items;
	}
	public void setItems(ArrayList<String> items)
	{
		this.items = items;
	}
	public int getItemNum()
	{
		return itemNum;
	}
	public void setItemNum(int itemNum)
	{
		this.itemNum = itemNum;
	}
}