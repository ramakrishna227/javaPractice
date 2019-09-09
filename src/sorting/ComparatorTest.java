package sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorTest {
	public static void main(String[] args) {
		Share ibm = new Share("IBM", 200d, 55000, 99.8d, 101.2d,"Equity");
		Share tcs = new Share("TCS", 100d, 55000, 99.8d, 101.2d,"Equity");
		Share infosys = new Share("INFOSYS", 100d, 55000, 99.8d, 101.2d,"Equity");
		List<Share> sharesList = Arrays.asList(ibm, tcs, infosys);

		System.out.println("Sort by Name");
		Collections.sort(sharesList);
		sharesList.forEach(System.out::println);

		System.out.println("Sort by Price");
		Collections.sort(sharesList, new Comparator<Share>() {
			// price comparator
			@Override
			public int compare(Share o1, Share o2) {
				if (o1.getPrice() > o2.getPrice()) {
					return 1;
				} else if (o1.getPrice() < o2.getPrice()) {
					return -1;
				}
				return 0;
			}

		});

		sharesList.forEach(System.out::println);
	}
}
class Asset{
	protected String assetType;

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
}
class Share extends Asset implements Comparable<Share> {
	String name;
	double price;
	double quantityTraded;
	double bidPrice;
	double askPrice;

	public Share(String name, double price, double quantityTraded, double bidPrice, double askPrice, String assetType) {
		this.name = name;
		this.price = price;
		this.quantityTraded = quantityTraded;
		this.bidPrice = bidPrice;
		this.askPrice = askPrice;
		this.assetType=assetType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getQuantityTraded() {
		return quantityTraded;
	}

	public void setQuantityTraded(double quantityTraded) {
		this.quantityTraded = quantityTraded;
	}

	public double getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(double bidPrice) {
		this.bidPrice = bidPrice;
	}

	public double getAskPrice() {
		return askPrice;
	}

	public void setAskPrice(double askPrice) {
		this.askPrice = askPrice;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name is ").append(getName()).append("; Price is ").append(getPrice()).append("; Quantity traded is ")
				.append(getQuantityTraded()).append("; AskPrice is ").append(getAskPrice()).append("; BidPrice is ")
				.append(getBidPrice());
		return sb.toString();
	}

	@Override
	public int compareTo(Share o) {

		return this.getName().compareTo(o.getName());
	}
}