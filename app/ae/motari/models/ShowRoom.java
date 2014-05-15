package ae.motari.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SHOW_ROOM")
public class ShowRoom extends BaseEntityAudit {

	public String name;
	public String location;
	public String logo;
	public String phone;
    public String fax;
    public String mobile;
    public String poBox;
	public String email;
    public String website;
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
	
	public static List<ShowRoom> findWhereNameLike(String name){
		if(null == name || name.trim() == "")
			return find.all();
		else
			return find.where()
			.ilike("name", "%"+name+"%")
			.findList();
	}
}
