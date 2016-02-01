package de.thkoeln.concurrent;

public class Worker extends Thread{
	
	private boolean keepProcessing = true;
	
	public Worker(){
	}
	
	public void run() {
		System.out.println("Not yet Implemented");
		
		while(keepProcessing){
			try {
				Worker.sleep(2000);
				System.out.println(this.getName()+":"+"Sleeping 2 ms");
			} catch (InterruptedException e) {
				System.out.println("Ohhhhhh:Interrupted");
				
			}
		}
		
		System.out.println(this.getName()+":"+"Exit");
		Worker.interrupted();
		
	}

	public boolean isKeepProcessing() {
		return keepProcessing;
	}

	public void setKeepProcessing(boolean keepProcessing) {
		this.keepProcessing = keepProcessing;
	}
	
	
}
