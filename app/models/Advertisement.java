package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "advertisement")
public class Advertisement extends BaseEntityAudit {

	public String title;
	public String description;
	public String thumbnail;
	public List<String> images;
	public ShowRoom owner;

	public Advertisement() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		String toString = super.toString()
				+ " [title=" + title + "]"
				+ " [description=" + title + "]"
				+ " [thumbnail=" + title + "]"
				+ " [owner=" + owner.name + "]"
				+ " [Images= Size: "+images.size()+" ";
				
				for(String s : images)
					toString += "["+s+"]";

				toString += "]";
		return toString;
	}
}
