package model;

public class OrangeShip extends SHIP {

	public OrangeShip() {
		super("view/resources/shipchooser/orange_ship.png", "view/resources/shipchooser/playerLife1_orange.png");
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getHp() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getShipSpeedFactor() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public String getShipInfo() {
		// TODO Auto-generated method stub
		return "Nothing good";
	}

}