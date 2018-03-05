package forms;

public class Person {
	private String name,sex,room,major;
	public Person(String n,String s,String r,String m) {
		this.name=n;
		this.sex=s;
		this.room=r;
		this.major=m;
	}
	public Person() {
		this.name="Hak Meng";
		this.sex="Male";
		this.room="A102";
		this.major="Statistic";
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	public String toString() {
		return name+"\t"+sex+"\t"+sex+"\t"+room+"\t"+major+"\n\n";
	}
}
