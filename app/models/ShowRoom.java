package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "show_room")
public class ShowRoom extends BaseEntityAudit {

	public String name;
	public String location;
//	@OneToMany(fetch=FetchType.EAGER,mappedBy="showRoom", cascade=CascadeType.ALL)
//	public Image logo;
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

	public static ShowRoom findById(Long id) {
		return find.byId(id);
	}
}
