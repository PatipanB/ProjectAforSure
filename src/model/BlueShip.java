package model;

public class BlueShip extends SHIP {

	public BlueShip() {
		super("view/resources/shipchooser/blue_ship.png", "view/resources/shipchooser/playerLife1_blue.png");
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getHp() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getShipSpeedFactor() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public String getShipInfo() {
		// TODO Auto-generated method stub
		return "Balanced\n ";
	}
}
