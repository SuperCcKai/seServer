package os_2;

enum stateAfterRun {exit, blocked, ready};

public class process {
	public String name;
	public int mem;
	public int level;
	public int cpuTime;
	stateAfterRun sar;
	
	@Override
	public String toString() {
		String str = "\n" + "name: " + name + "#mem: " + mem + "#level: " + level + 
				"#cpuTime: " + cpuTime + "\n";
		return str;
	}
}
