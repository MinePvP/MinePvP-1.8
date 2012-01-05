package ch.nonameweb.bukkit.plugins.minepvp.manager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.Plugin;

import ch.nonameweb.bukkit.plugins.minepvp.MinePvP;

public class SettingsManager {
	
	private Configuration config;
	
	private Boolean debug;
	
	private Integer damageOnBlockBracke;
	
	private Integer ersteStufeKosten;
	private Integer ersteStufeRadius;
	private Integer ersteStufeMinPlayer;
	
	private Integer minAbstand;
	private Integer maxAbstand;
	
	private Integer clanSpawnKosten;
	
	private Integer moatKosten;
	private Integer moatDamage;
	
	private ArrayList<Integer> stufenKosten;
	private ArrayList<Integer> stufenRadius;
	
	private ArrayList<Integer> kostenAlertsystem;
	
	private Integer ctfPoints;
	private Integer ctfMinPlayerOnline;
	
	private Integer attackDelay;
	private Integer attackTime;
	
	private Integer repairChestKosten;
	
	public SettingsManager() {
		this.config = MinePvP.getInstance().getConfig();
		
		this.addDefault();
		this.load();
	}
	
	public void addDefault() {
		config.addDefault("Settings.Debug", false );
		
		config.addDefault("Settings.Land.DamageOnBlockBracke", 2);
		
		config.addDefault("Settings.Land.ErsteStufe.Kosten", 40);
		config.addDefault("Settings.Land.ErsteStufe.Radius", 30);
		config.addDefault("Settings.Land.ErsteStufe.MinPlayer", 4);
		
		config.addDefault("Settings.Land.MinAbstand", 500);
		config.addDefault("Settings.Land.MinAbstand", 800);
	
		config.addDefault("Settings.Land.ClanSpawn.Kosten", 20);
		
		config.addDefault("Settings.Land.Moat.Kosten", 80);
		config.addDefault("Settings.Land.Moat.Damage", 1);
		
		
		stufenKosten = new ArrayList<Integer>();
		stufenKosten.add(5);
		stufenKosten.add(10);
		stufenKosten.add(15);
		stufenKosten.add(20);
		stufenKosten.add(25);
		stufenKosten.add(30);
		stufenKosten.add(35);
		stufenKosten.add(40);
		stufenKosten.add(50);
		
		config.addDefault("Settings.Land.Stufen.Kosten", stufenKosten);
		
		
		stufenRadius = new ArrayList<Integer>();
		stufenRadius.add(40);
		stufenRadius.add(50);
		stufenRadius.add(60);
		stufenRadius.add(70);
		stufenRadius.add(80);
		stufenRadius.add(90);
		stufenRadius.add(100);
		stufenRadius.add(110);
		stufenRadius.add(120);
		
		config.addDefault("Settings.Land.Stufen.Radius", stufenRadius);
		
		kostenAlertsystem = new ArrayList<Integer>();
		kostenAlertsystem.add(20);
		kostenAlertsystem.add(20);
		kostenAlertsystem.add(20);
		
		config.addDefault("Settings.Land.Alertsystem.Kosten", kostenAlertsystem);
		
		config.addDefault("Settings.CTF.Points", 1);
		config.addDefault("Settings.CTF.MinPlayerOnline", 3);
		
		// Attack
	    config.addDefault("Settings.Attack.Time", 30);
	    config.addDefault("Settings.Attack.Delay", 10);
		
	    // RepariChest
	    config.addDefault("Settings.Repair.Kosten", 10);
	    
		config.options().copyDefaults(true);
		MinePvP.getInstance().saveConfig();
	}
	
	public void load() {
		
		MinePvP.getInstance().reloadConfig();
		
		setDebug( config.getBoolean("Settings.Debug") );
		
		setDamageOnBlockBracke( config.getInt("Settings.Land.DamageOnBlockBracke") );
		
		setErsteStufeKosten( config.getInt("Settings.Land.ErsteStufe.Kosten") );
		setErsteStufeRadius( config.getInt("Settings.Land.ErsteStufe.Radius") );
		setErsteStufeMinPlayer( config.getInt("Settings.Land.ErsteStufe.MinPlayer") );
		
		setMinAbstand( config.getInt("Settings.Land.MinAbstand") );
		setMaxAbstand( config.getInt("Settings.Land.MaxAbstand") );
		
		setClanSpawnKosten( config.getInt("Settings.Land.ClanSpawn.Kosten") );
		
		setMoatKosten( config.getInt("Settings.Land.Moat.Kosten") );
		setMoatDamage( config.getInt("Settings.Land.Moat.Damage") );
		
		setStufenKosten( config.getList("Settings.Land.Stufen.Kosten") );
		setStufenRadius( config.getList("Settings.Land.Stufen.Radius") );
		
		setKostenAlertsystem( config.getList("Settings.Land.Alertsystem.Kosten") );
		
		setCtfPoints( config.getInt("Settings.CTF.Points") );
		setCtfMinPlayerOnline( config.getInt("Settings.CTF.MinPlayerOnline") );
		
		setAttackDelay( config.getInt("Settings.Attack.Delay") );
		setAttackTime( config.getInt("Settings.Attack.Time") );
		
		setRepairChestKosten( config.getInt("Settings.Repair.Kosten") );
		
	}
	
	public void save() {
		
		config.set("Settings.Debug", getDebug());
		
		config.set("Settings.Land.DamageOnBlockBracke", getDamageOnBlockBracke());
		
		config.set("Settings.Land.ErsteStufe.Kosten", getErsteStufeKosten());
		config.set("Settings.Land.ErsteStufe.Radius", getErsteStufeRadius());
		config.set("Settings.Land.ErsteStufe.MinPlayer", getErsteStufeMinPlayer());
		
		config.set("Settings.Land.MinAbstand", getMinAbstand());
		config.set("Settings.Land.MaxAbstand", getMaxAbstand());
		
		config.set("Settings.Land.ClanSpawn.Kosten", getClanSpawnKosten());
		
		config.set("Settings.Land.Moat.Kosten", getMoatKosten());
		config.set("Settings.Land.Moat.Damage", getMoatDamage());
		
		config.set("Settings.Land.Stufen.Kosten", getStufenKosten());
		config.set("Settings.Land.Stufen.Radius", getStufenRadius());
		
		config.set("Settings.Land.Alertsystem.Kosten", getKostenAlertsystem());
		
		config.set("Settings.CTF.Points", getCtfPoints());
		config.set("Settings.CTF.MinPlayerOnline", getCtfMinPlayerOnline());
		
		config.set("Settings.Attack.Delay", getAttackDelay());
		config.set("Settings.Attack.Time", getAttackTime());
		
		config.set("Settings.Repair.Kosten", getRepairChestKosten());
		
		MinePvP.getInstance().saveConfig();
	}
	
	public Boolean getDebug() {
		return this.debug;
	}
	
	public void setDebug(Boolean debug) {
		this.debug = debug;
	}
	
	public Integer getDamageOnBlockBracke() {
		return damageOnBlockBracke;
	}

	public void setDamageOnBlockBracke(Integer damageOnBlockBracke) {
		this.damageOnBlockBracke = damageOnBlockBracke;
	}

	public Integer getErsteStufeKosten() {
		return ersteStufeKosten;
	}

	public void setErsteStufeKosten(Integer ersteStufeKosten) {
		this.ersteStufeKosten = ersteStufeKosten;
	}

	public Integer getErsteStufeRadius() {
		return ersteStufeRadius;
	}

	public void setErsteStufeRadius(Integer ersteStufeRadius) {
		this.ersteStufeRadius = ersteStufeRadius;
	}

	public Integer getErsteStufeMinPlayer() {
		return ersteStufeMinPlayer;
	}

	public void setErsteStufeMinPlayer(Integer ersteStufeMinPlayer) {
		this.ersteStufeMinPlayer = ersteStufeMinPlayer;
	}

	public Integer getMinAbstand() {
		return minAbstand;
	}

	public void setMinAbstand(Integer minAbstand) {
		this.minAbstand = minAbstand;
	}

	public Integer getClanSpawnKosten() {
		return clanSpawnKosten;
	}

	public void setClanSpawnKosten(Integer clanSpawnKosten) {
		this.clanSpawnKosten = clanSpawnKosten;
	}

	public Integer getMoatKosten() {
		return moatKosten;
	}

	public void setMoatKosten(Integer moatKosten) {
		this.moatKosten = moatKosten;
	}

	public Integer getMoatDamage() {
		return moatDamage;
	}

	public void setMoatDamage(Integer moatDamage) {
		this.moatDamage = moatDamage;
	}

	public ArrayList<Integer> getStufenKosten() {
		return stufenKosten;
	}

	public void setStufenKosten(List<Integer> stufenKosten) {
		
		this.stufenKosten = new ArrayList<Integer>();
		
		for ( Integer kosten : stufenKosten ) {
			this.stufenKosten.add(kosten);
		}
		
	}

	public ArrayList<Integer> getStufenRadius() {
		return stufenRadius;
	}

	public void setStufenRadius(List<Integer> stufenRadius) {
		
		this.stufenRadius = new ArrayList<Integer>();
		
		for ( Integer radius : stufenRadius ) {
			this.stufenRadius.add( radius );
		}
		
	}

	public Integer getCtfPoints() {
		return ctfPoints;
	}

	public void setCtfPoints(Integer ctfPoints) {
		this.ctfPoints = ctfPoints;
	}

	public Integer getCtfMinPlayerOnline() {
		return ctfMinPlayerOnline;
	}

	public void setCtfMinPlayerOnline(Integer ctfMinPlayerOnline) {
		this.ctfMinPlayerOnline = ctfMinPlayerOnline;
	}

	public ArrayList<Integer> getKostenAlertsystem() {
		return kostenAlertsystem;
	}

	public void setKostenAlertsystem(List<Integer> kostenAlertsystem) {
		this.kostenAlertsystem = new ArrayList<Integer>();
		
		for ( Integer kosten : kostenAlertsystem ) {
			this.kostenAlertsystem.add( kosten );
		}
	}

	public Integer getAttackDelay() {
		return attackDelay;
	}

	public void setAttackDelay(Integer attackDelay) {
		this.attackDelay = attackDelay;
	}

	public Integer getAttackTime() {
		return attackTime;
	}

	public void setAttackTime(Integer attackTime) {
		this.attackTime = attackTime;
	}

	public Integer getRepairChestKosten() {
		return repairChestKosten;
	}

	public void setRepairChestKosten(Integer repairChestKosten) {
		this.repairChestKosten = repairChestKosten;
	}

	public Integer getMaxAbstand() {
		return maxAbstand;
	}

	public void setMaxAbstand(Integer maxAbstand) {
		this.maxAbstand = maxAbstand;
	}
	
	
}
