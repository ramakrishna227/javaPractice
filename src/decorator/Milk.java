package decorator;

public class Milk extends BeverageDecorator {

	public Milk(Beverage beverage){
		this.beverage=beverage;
	}
	public int cost() {
		return beverage.cost() + 10;
	}

	public String getDescritption() {
		return beverage.getDescritption() + " Added milk";
	}
}
