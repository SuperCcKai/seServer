package os_2;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class SysMess {
	public final static int maxMem = 2000; //all memory
	public static int curMem = 2000; //current memory
	public static int cpuTime;
	
	
	public static void refreshMem() {
		//running
		curMem = maxMem;
		if(Running.isRun)
			curMem -= Running.p.mem;
		//Ready
		for(int i=0; i<Ready.list.size(); i++) {
			curMem -= Ready.list.get(i).mem;
		}
		//Blocked
		for(int i=0; i<Blocked.list.size(); i++) {
			curMem -= Blocked.list.get(i).mem;
		}
	}//refresh()
	
	public static void run(int time) {
		if(Running.isRun && cpuTime>time) {
			System.out.println("cpuTime>time");
			cpuTime -= time;
		}else if(Running.isRun && cpuTime==time) {
			System.out.println("cpuTime=time");
			switch(Running.p.sar) {
			case exit: //exit
				break;
			case blocked: //blocked
				Blocked.list.add(Running.p);
				SysMess.refreshLevel();
				break;
			case ready: //ready
				Ready.list.add(Running.p);
				SysMess.refreshLevel();
				break;
			}//switch
			Running.isRun = false;
			if(!Ready.list.isEmpty()) { //ready isn't null, first process in Running
				Running.p = Ready.list.poll();
				Running.isRun = true;
			}
		}else if(Running.isRun && cpuTime<time) {
			System.out.println("cpuTime<time");
			
			Running.isRun = false;
			if(Ready.list.size()!=0) {
				Running.p = Ready.list.poll();
				Running.isRun = true;
				run(time-cpuTime);
			}//if
		}else {
			if(!Ready.list.isEmpty()) {
				Running.p = Ready.list.poll();
				Running.isRun = true;
			}
		}//else
	}//run()
	
	public static void refreshLevel() {
		LinkedList<process> list;
		//ready
		list = Ready.list;
		Collections.sort(list, new Comparator<process>() {
			@Override
			public int compare(process o1, process o2) {
				return o1.level-o2.level;
			}
		});
		//Blocked
		list = Blocked.list;
		Collections.sort(list, new Comparator<process>() {
			@Override
			public int compare(process o1, process o2) {
				return o1.level-o2.level;
			}
		});
		
	}//refreshLevel
	
}//SysMess
