package model;

public abstract class SHIP {

	protected String urlShip;
	protected String urlLife;
	
	
	protected SHIP(String urlShip, String urlLife ) {
		this.urlShip = urlShip;
		this.urlLife = urlLife;
	}
	public String getUrl() {
		return urlShip;
	}
	
	public String getUrlLife() {
		return urlLife;
	}
	
	public abstract int getHp();
	public abstract int getShipSpeedFactor();
}
