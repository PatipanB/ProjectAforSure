package model;

public abstract class SHIP {

	protected String urlShip;
	protected String urlLife;
	
<<<<<<< HEAD
	private String urlShip; 
	private String urlLife;
||||||| merged common ancestors
	private String urlShip;
	private String urlLife;
=======
>>>>>>> b4460accb094c5afa28ad3846c98f91e254aa13d
	
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
	public abstract String getShipInfo();
}
