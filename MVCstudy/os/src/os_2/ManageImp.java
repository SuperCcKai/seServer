package os_2;

public interface ManageImp {
	public void newToReady(); //new state To ready state
	public void readyToRunning(); //new state To ready state
	public void runningToExit(); //new state To ready state
	public void runningToBlocked(); //new state To ready state
	public void blockedToReady(); //new state To ready state
	public void runningToReady(); //new state To ready state
	public void displayReady();
	public void displayRunning();
	public void displayBlocked();
	public void consumeTime();
	public void exit();
	
}
