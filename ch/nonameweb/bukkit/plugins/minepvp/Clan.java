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
	
	// Alertsystem
	private Integer alertsystem = null;
	
	// Wassergraben
	private Boolean moat = false;
	
	// Punkte
	private Integer points = null;
	
	private Player playerHasFlag = null;
	
	private Integer flags = null;
	
	// TeamSpawn
	private Boolean clanSpawn = false;
	
	private Integer clanSpawnX = null;
	private Integer clanSpawnY = null;
	private Integer clanSpawnZ = null;
	
	
	// Attack
	private Boolean attack = false;
	private Clan attackedClan = null;
	
	
	// Kill Counter
	private Integer playerKills = null;
	private Integer playerKillCounter = null;
	
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
		
		setPlayerKills(0);
		setPlayerKillCounter(0);
		
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
		
		// AlertSystem
		setAlertsystem( config.getInt("Clans." + getName() + ".AlertSystem", 0) );
		
		// Punkte
		setPoints( config.getInt("Clans." + getName() + ".Punkte", 0) );
		
		setFlags( config.getInt("Clans." + getName() + ".Flaggen", 0) );
		
		// TeamSpawn
		setClanSpawn( config.getBoolean("Clans." + getName() + ".ClanSpawn", false) );
		
		setClanSpawnX( config.getInt("Clans." + getName() + ".ClanSpawnLocation.X") );
		setClanSpawnY( config.getInt("Clans." + getName() + ".ClanSpawnLocation.Y") );
		setClanSpawnZ( config.getInt("Clans." + getName() + ".ClanSpawnLocation.Z") );
		
		// Wassergraben
		setMoat( config.getBoolean("Clans." + getName() + ".Moat", false) );
		
		
		// Kills
		setPlayerKills( config.getInt("Clans." + getName() + ".Kills.Player") );
		setPlayerKillCounter( config.getInt("Clans." + getName() + ".Kills.PlayerCounter") );
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
		
		// AlertSystem
		config.set("Clans." + getName() + ".AlertSystem", getAlertsystem());
		
		// Punkte
		config.set("Clans." + getName() + ".Punkte", getPoints());
		
		config.set("Clans." + getName() + ".Flaggen", getFlags());
		
		// TeamSpawn
		config.set("Clans." + getName() + ".ClanSpawn", getClanSpawn());
		
		config.set("Clans." + getName() + ".ClanSpawnLocation.X", getClanSpawnX() );
		config.set("Clans." + getName() + ".ClanSpawnLocation.Y", getClanSpawnY() );
		config.set("Clans." + getName() + ".ClanSpawnLocation.Z", getClanSpawnZ() );
	
		// Wassergraben
		config.set("Clans." + getName() + ".Moat", getMoat());
		
		// Kills
		config.set("Clans." + getName() + ".Kills.Player", getPlayerKills()	);
		config.set("Clans." + getName() + ".Kills.PlayerCounter", getPlayerKillCounter() );
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
	
	
	public void setClanSpawnLocation( Integer x, Integer y, Integer z ) {
		setClanSpawnX(x);
		setClanSpawnY(y);
		setClanSpawnZ(z);
	}

	
	public Location getClanSpawnLocation( ) {
		
		Location location = new Location( MinePvP.getInstance().getServer().getWorld("world"), getClanSpawnX(), getClanSpawnY(), getClanSpawnZ());
		
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
	
	public void addFlag() {
		this.flags++;
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

	public Integer getAlertsystem() {
		return alertsystem;
	}

	public void setAlertsystem(Integer alertsystem) {
		this.alertsystem = alertsystem;
	}
	
	public void upgradeAlertsystem(Integer alertsystem) {
		this.alertsystem = this.alertsystem + alertsystem;
	}

	public Boolean getClanSpawn() {
		return clanSpawn;
	}

	public void setClanSpawn(Boolean clanSpawn) {
		this.clanSpawn = clanSpawn;
	}

	public Integer getFlags() {
		return flags;
	}

	public void setFlags(Integer flags) {
		this.flags = flags;
	}

	public Boolean getMoat() {
		return moat;
	}

	public void setMoat(Boolean moat) {
		this.moat = moat;
	}

	public Integer getClanSpawnX() {
		return clanSpawnX;
	}

	public void setClanSpawnX(Integer clanSpawnX) {
		this.clanSpawnX = clanSpawnX;
	}

	public Integer getClanSpawnY() {
		return clanSpawnY;
	}

	public void setClanSpawnY(Integer clanSpawnY) {
		this.clanSpawnY = clanSpawnY;
	}

	public Integer getClanSpawnZ() {
		return clanSpawnZ;
	}

	public void setClanSpawnZ(Integer clanSpawnZ) {
		this.clanSpawnZ = clanSpawnZ;
	}

	public Clan getAttackedClan() {
		return attackedClan;
	}

	public void setAttackedClan(Clan attackedClan) {
		this.attackedClan = attackedClan;
	}

	public Boolean getAttack() {
		return attack;
	}

	public void setAttack(Boolean attack) {
		this.attack = attack;
	}

	public Integer getPlayerKills() {
		return playerKills;
	}

	public void setPlayerKills(Integer playerKills) {
		this.playerKills = playerKills;
	}

	public Integer getPlayerKillCounter() {
		return playerKillCounter;
	}

	public void setPlayerKillCounter(Integer playerKillCounter) {
		this.playerKillCounter = playerKillCounter;
	}
	
	public void addPlayerKill() {
		
		this.playerKillCounter++;
		this.playerKills++;
		
		if ( this.playerKillCounter == 10 ) {
			this.addPoints( 2 );
			this.setPlayerKillCounter(0);
		}
		
	}
	
	
	
}
