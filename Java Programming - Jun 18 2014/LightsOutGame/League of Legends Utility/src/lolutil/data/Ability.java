package lolutil.data;

public class Ability
{
	int cost;
	float baseCooldown;
	Resource resource;
	DamageType damage;
	DamageScaling scaling;
	int range;
	int binding;	//0 = Innate, 1 = Q, 2 = W, 3 = E, 4 = R, 5 = Item
}
