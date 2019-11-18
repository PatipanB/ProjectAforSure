package application;

import java.util.List;

public class ThreadPool extends ThreadGroup {

	private boolean alive;
	private List<Runnable> songList;
	
	public ThreadPool(int numThread) {
		super("ThreadPool");
	}

}
