package bean;

public class TaskBean {
	private String root;//root="0"代表任务未完成，等于"1"表示已完成
	private String name;//任务名称
	private String time;//截至时间
	
	public String getRoot() {
		return root;
	}
	public void setRoot(String root) {
		this.root = root;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
