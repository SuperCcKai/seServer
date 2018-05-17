package os_2;

import java.util.Scanner;

public class Manage implements ManageImp {
	
	public void manage() {
		switch (Main.num) {
		case 1:
			newToReady();
			break;
		case 2:
			readyToRunning();
			break;
		case 3:
			runningToExit();
			break;
		case 4:
			runningToBlocked();
			break;
		case 5:
			blockedToReady();
			break;
		case 6:
			runningToReady();
			break;
		case 7:
			displayReady();
			break;
		case 8:
			displayRunning();
			break;
		case 9:
			displayBlocked();
			break;
		case 10:
			consumeTime();
			break;
		case 11:
			exit();
			break;
		default:
			break;
		}//switch
	}

	@Override
	public void newToReady() {
		process p = new process();
		Scanner sc = new Scanner(System.in);
		System.out.println("input the new process's name(varchar in [0, 10]):");
		p.name = sc.nextLine();
		System.out.println("input the memory that it will use(0-2000):");
		p.mem = sc.nextInt();
		SysMess.refreshMem();
		if(SysMess.curMem < p.mem) {
			System.out.println("error: current remaining memory is less than it!");
			sc.nextLine();
			return;
		}
		System.out.println("input the level(0-9):");
		p.level = sc.nextInt();
		System.out.println("input the cpu time(0-100):");
		p.cpuTime = sc.nextInt();
		System.out.println("input process's state after Running(0-2):,");
		System.out.println("(ps: 0:exit, 1:blocked, 2:ready)");
		switch(sc.nextInt()) {
		case 0:
			p.sar = stateAfterRun.exit;
			break;
		case 1:
			p.sar = stateAfterRun.blocked;
			break;
		case 2:
			p.sar = stateAfterRun.ready;
			break;
		default:
			System.out.println("input error!");
			return;
		}
		if(Ready.list.isEmpty() && !Running.isRun) {
			Running.p = p;
			Running.isRun = true;
			SysMess.cpuTime = p.cpuTime;
		}
		else {
			System.out.println("newToReady operate can't be newToRunning.");
			System.out.println("Running is run: " + Running.isRun + 
					"#Ready is null:" + Ready.list.isEmpty());
			//SysMess.run(10); //creating a new process consumes 10 cpu time
			Ready.list.add(p);
		}
		SysMess.refreshMem(); //刷新内存
		SysMess.refreshLevel(); //刷新优先级
	}

	@Override
	public void readyToRunning() {
		if(!Running.isRun && Ready.list.size()!=0) {
			Running.p = Ready.list.poll();
			Running.isRun = true;
		}else {
			Scanner sc = new Scanner(System.in);
			System.out.println("there is one process in Running state now!");
			sc.nextLine();
		}
		
	}

	@Override
	public void runningToExit() {
		if(Running.isRun){
			if(Ready.list.size() != 0)
				Running.p = Ready.list.poll();
			else
				Running.isRun = false;
		}else {
			Scanner sc = new Scanner(System.in);
			System.out.println("no process is running!");
			sc.nextLine();
		}
	}

	@Override
	public void runningToBlocked() {
		if(Running.isRun){
			Blocked.list.add(Running.p);
			if(Ready.list.size() != 0)
				Running.p = Ready.list.poll();
			else
				Running.isRun = false;
		}else {
			Scanner sc = new Scanner(System.in);
			System.out.println("no process is running!");
			sc.nextLine();
		}
	}

	@Override
	public void blockedToReady() {
		if(!Running.isRun && Ready.list.size()==0) {
			Running.p = Blocked.list.poll();
			Running.isRun = true;
		}else {
			Ready.list.add(Blocked.list.poll());
		}
	}

	@Override
	public void runningToReady() {
		if(Running.isRun){
			Ready.list.add(Running.p);
			Running.p = Ready.list.poll();
		}else {
			Scanner sc = new Scanner(System.in);
			System.out.println("no process is running!");
			sc.nextLine();
		}
		
	}

	@Override
	public void displayReady() {
		Scanner sc = new Scanner(System.in);
		System.out.println(Ready.list);
		sc.nextLine();
	}

	@Override
	public void displayRunning() {
		Scanner sc = new Scanner(System.in);
		if(Running.isRun)
			System.out.println("process_name: " + Running.p.name 
					+ "#mem: " + Running.p.mem 
					+ "#level: " + Running.p.level 
					+ "#left_cpuTime: " + SysMess.cpuTime);
		else
			System.out.println("no process is running");
		sc.nextLine();
	}

	@Override
	public void displayBlocked() {
		Scanner sc = new Scanner(System.in);
		System.out.println(Blocked.list);
		sc.nextLine();
	}

	@Override
	public void consumeTime() {
		Scanner sc = new Scanner(System.in);
		System.out.println("input time:");
		int time = sc.nextInt();
		SysMess.run(time);
	}
	
	@Override
	public void exit() {
		Main.isExit = true;
	}
	
}
