package de.thkoeln.concurrent;

import java.util.ArrayList;
import java.util.Iterator;

public class ThreadPool {
	
	private int numberOfThreads =5;
	ArrayList<Worker> workers;
	
	public ThreadPool(int numberOfThreads ) throws InterruptedException{
		this.numberOfThreads = numberOfThreads;
		workers = new ArrayList<Worker>(numberOfThreads);
		this.buildWorkers();
		
		Thread.sleep(6000);
		this.stopWorkers();
	}
	
	private void buildWorkers(){
		for (int i = 0; i < numberOfThreads; i++) {
			Worker worker = new Worker();
			worker.start();
			workers.add(worker);
		}
	}
	
	private void stopWorkers(){
		for (Iterator<Worker> iterator = workers.iterator(); iterator.hasNext();) {
			Worker worker = (Worker) iterator.next();
			worker.setKeepProcessing(false);;
			
		}
	}

}
