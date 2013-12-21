package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import play.db.ebean.Model.Finder;

@Entity
@Table(name = "advertisement")
public class Advertisement extends BaseEntityAudit {

	public String title;
	public String description;
	public String thumbnail;
	public List<String> images;
	public ShowRoom owner;

	public Advertisement() {}
	
	public static Finder<Long, Advertisement> find = new Finder<Long, Advertisement>(Long.class, Advertisement.class);
	
	public void addImageString(String img){
		if(images == null){
			images = new ArrayList<String>();
		}
		
		images.add(img);
	}
	
	@Override
	public String toString() {
		String toString = super.toString()
				+ " [title=" + title + "]"
				+ " [description=" + description + "]"
				+ " [thumbnail=" + thumbnail + "]"
				+ " [owner=" + owner.name + "]"
				+ " [Images= Size: "+images.size()+" ";
				
				for(String s : images)
					toString += "["+s+"]";

				toString += "]";
		return toString;
	}

	public static Advertisement findById(Long id) {
		return find.byId(id);
	}
}
