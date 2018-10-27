package decorator;

public class CoffeeShop {
public static void main(String[] args) {
	Beverage beverage=new Espresso();
	
	
	beverage=new Milk(beverage);
	beverage = new Milk(beverage);
	
	
	System.out.println(beverage.getDescritption()+"     "+beverage.cost());
	
	
	Beverage beverage2=new Espresso();
	
	beverage2=new Sugar(new Milk(beverage2));
	
	
	System.out.println(beverage2.getDescritption()+"     "+beverage2.cost());
}
}
