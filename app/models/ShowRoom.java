package models;

import play.db.ebean.*;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "show_room")
public class ShowRoom extends BaseEntityAudit {

	public String name;
	public String location;
	public String logo;
	public String phone;
	public String email;
	public String description;
	
	public static Finder<Long, ShowRoom> find = new Finder<Long, ShowRoom>(Long.class, ShowRoom.class);
	
	public ShowRoom() {}

	@Override
	public String toString() {
		String toString = super.toString() 
				+ " [name=" + name + "]"
				+ " [location=" + location + "]"
				+ " [logo=" + logo + "]"
				+ " [phone=" + phone + "]"
				+ " [email=" + email + "]"
				+ " [description=" + description + "]";
		
		return toString;
	}

}
