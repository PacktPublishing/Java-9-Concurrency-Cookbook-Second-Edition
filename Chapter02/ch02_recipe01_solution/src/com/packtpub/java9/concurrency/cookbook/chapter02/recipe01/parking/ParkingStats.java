package com.packtpub.java9.concurrency.cookbook.chapter02.recipe01.parking;

public class ParkingStats {
	
	/**
	 * This two variables store the number of cars and motorcycles in the parking
	 */
	private long numberCars;
	private long numberMotorcycles;

	/**
	 * Two objects for the synchronization. ControlCars synchronizes the
	 * access to the numberCars attribute and controlMotorcycles synchronizes
	 * the access to the numberMotorcycles attribute.
	 */
	private final Object controlCars, controlMotorcycles;
	
	
	private ParkingCash cash;
	/**
	 * Constructor of the class
	 */
	public ParkingStats (ParkingCash cash) {
		numberCars=0;
		numberMotorcycles=0;
		controlCars=new Object();
		controlMotorcycles=new Object();
		this.cash=cash;
	}
	
	
	public void carComeIn() {
		synchronized (controlCars) {
			numberCars++;
		}
	}
	
	public void carGoOut() {
		synchronized (controlCars) {
			numberCars--;
		}
		cash.vehiclePay();
	}
	
	public void motoComeIn() {
		synchronized (controlMotorcycles) {
			numberMotorcycles++;
		}
	}
	
	public void motoGoOut() {
		synchronized (controlMotorcycles) {
			numberMotorcycles--;
		}
		cash.vehiclePay();
	}


	public synchronized long getNumberCars() {
		synchronized (controlCars) {
			return numberCars;
		}
	}


	public long getNumberMotorcycles() {
		synchronized (controlMotorcycles) {
			return numberMotorcycles;
		}
	}
	
	

}
