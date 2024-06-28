package Models;

import java.util.ArrayList;
import java.util.Date;

import DAO.ModelsDAO.REGISTROVENTA;
import DAO.ModelsDAO.REGISTROVENTADET;

public class Venta {

	private REGISTROVENTA registroventa;
	private ArrayList<REGISTROVENTADET> registroventadetList;
	
	public Venta() {}

	public Venta(REGISTROVENTA registroventa, ArrayList<REGISTROVENTADET> registroventadetList) {
		
		this.registroventa = registroventa;
		this.registroventadetList = registroventadetList;
		
	}

	public REGISTROVENTA getRegistroventa() {
		return registroventa;
	}

	public void setRegistroventa(REGISTROVENTA registroventa) {
		this.registroventa = registroventa;
	}

	public ArrayList<REGISTROVENTADET> getRegistroventadetList() {
		return registroventadetList;
	}

	public void setRegistroventadetList(ArrayList<REGISTROVENTADET> registroventadetList) {
		this.registroventadetList = registroventadetList;
	}
	
	
	
}
