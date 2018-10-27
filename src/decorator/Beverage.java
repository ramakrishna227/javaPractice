package decorator;

public abstract class Beverage {
public String description = "Unknown Beverage";
public String getDescritption() {
	return description;
}
public abstract int cost();
}
