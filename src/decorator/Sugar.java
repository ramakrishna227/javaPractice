package decorator;

public class Sugar extends BeverageDecorator{

	
	public Sugar(Beverage beverage) {
this.beverage=beverage;
}
	@Override
	public String getDescritption() {
		return beverage.getDescritption()+" Sugar";
	}

	@Override
	public int cost() {
		return beverage.cost()+5;
	}

}
