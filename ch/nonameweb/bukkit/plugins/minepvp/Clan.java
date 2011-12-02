package ch.nonameweb.bukkit.plugins.minepvp;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Clan {
	
	private String name;
	
	// Base
	private Integer baseX = null;
	private Integer baseY = null;
	private Integer baseZ = null;
	
	// Stufe
	private Integer stufe = null;
	private Integer radius = null;
	
	// Punkte
	private Integer points = null;
	
	private Player playerHasFlag = null;
	
	/**
	 * 
	 */
	public Clan() {
		
	}
	
	/**
	 * 
	 * @param clanName
	 * @param location
	 * @return
	 */
	public Boolean create( String clanName, Location location ) {
		
		// Name
		setName(clanName);
		
		// Location
		setBaseX(location.getBlockX());
		setBaseY(location.getBlockY());
		setBaseZ(location.getBlockZ());
		
		setStufe(0);
		setPoints(0);
		
		return true;
	}
	
	/**
	 * 
	 * @param clan
	 */
	public void load( String clan, FileConfiguration config ) {
		
		setName(clan);
		
		// Base
		setBaseX( config.getInt("Clans." + getName() + ".Base.X") );
		setBaseY( config.getInt("Clans." + getName() + ".Base.Y") );
		setBaseZ( config.getInt("Clans." + getName() + ".Base.Z") );
		
		// Stufe
		setStufe( config.getInt("Clans." + getName() + ".Stufe", 0) );
		
		// Punkte
		setPoints( config.getInt("Clans." + getName() + ".Punkte", 0) );
		
	}
	
	/**
	 * 
	 * @param config
	 */
	public void save( FileConfiguration config ) {
		
		// Base
		config.set("Clans." + getName() + ".Base.X", getBaseX());
		config.set("Clans." + getName() + ".Base.Y", getBaseY());
		config.set("Clans." + getName() + ".Base.Z", getBaseZ());
		
		// Stufe
		config.set("Clans." + getName() + ".Stufe", getStufe());
		
		// Punkte
		config.set("Clans." + getName() + ".Punkte", getPoints());
	}
	
	/**
	 * 
	 * @param location
	 */
	public void setBaseLocation( Integer x, Integer y, Integer z ) {
		setBaseX( x );
		setBaseY( y );
		setBaseZ( z );
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public Location getBaseLocation( ) {
		
		Location location = new Location( MinePvP.getInstance().getServer().getWorld("world"), getBaseX(), getBaseY(), getBaseZ());
		
		return location;
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getBaseX() {
		return baseX;
	}

	/**
	 * 
	 * @param baseX
	 */
	public void setBaseX(Integer baseX) {
		this.baseX = baseX;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getBaseY() {
		return baseY;
	}

	/**
	 * 
	 * @param baseY
	 */
	public void setBaseY(Integer baseY) {
		this.baseY = baseY;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getBaseZ() {
		return baseZ;
	}

	/**
	 * 
	 * @param baseZ
	 */
	public void setBaseZ(Integer baseZ) {
		this.baseZ = baseZ;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getStufe() {
		return stufe;
	}

	/**
	 * 
	 * @param stufe
	 */
	public void setStufe(Integer stufe) {
		this.stufe = stufe;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getPoints() {
		return points;
	}

	/**
	 * 
	 * @param points
	 */
	public void setPoints(Integer points) {
		this.points = points;
	}
	
	/**
	 * 
	 * @param points
	 */
	public void addPoints( Integer points ) {
		this.points += points;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getRadius() {
		return radius;
	}

	/**
	 * 
	 * @param radius
	 */
	public void setRadius(Integer radius) {
		this.radius = radius;
	}

	/**
	 * 
	 * @return
	 */
	public Player getPlayerHasFlag() {
		return playerHasFlag;
	}

	/**
	 * 
	 * @param playerHasFlag
	 */
	public void setPlayerHasFlag(Player playerHasFlag) {
		this.playerHasFlag = playerHasFlag;
	}
	
	/**
	 * 
	 */
	public void resetPlayerHasFlag() {
		this.playerHasFlag = null;
	}
	
}