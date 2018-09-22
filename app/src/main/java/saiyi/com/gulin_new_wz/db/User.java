package saiyi.com.gulin_new_wz.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 陈姣姣 on 2018/8/17.
 */

@Entity
public class User {

 @Id
 private Long id;

 private String name;
 private String age;
 private String hight;
 private String greader;
 private String weight;
 private String remarks; //备注
 private String entering_person; //录入人
 private String doctors_name; // 医生名字
 private String userHeader;  // 用户头像
	@Generated(hash = 129213252)
	public User(Long id, String name, String age, String hight, String greader,
			String weight, String remarks, String entering_person, String doctors_name,
			String userHeader) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.hight = hight;
		this.greader = greader;
		this.weight = weight;
		this.remarks = remarks;
		this.entering_person = entering_person;
		this.doctors_name = doctors_name;
		this.userHeader = userHeader;
	}
	@Generated(hash = 586692638)
	public User() {
	}
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return this.age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getHight() {
		return this.hight;
	}
	public void setHight(String hight) {
		this.hight = hight;
	}
	public String getGreader() {
		return this.greader;
	}
	public void setGreader(String greader) {
		this.greader = greader;
	}
	public String getWeight() {
		return this.weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getRemarks() {
		return this.remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getEntering_person() {
		return this.entering_person;
	}
	public void setEntering_person(String entering_person) {
		this.entering_person = entering_person;
	}
	public String getDoctors_name() {
		return this.doctors_name;
	}
	public void setDoctors_name(String doctors_name) {
		this.doctors_name = doctors_name;
	}
	public String getUserHeader() {
		return this.userHeader;
	}
	public void setUserHeader(String userHeader) {
		this.userHeader = userHeader;
	}

}
