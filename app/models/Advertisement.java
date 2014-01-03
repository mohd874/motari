package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "advertisement")
public class Advertisement extends BaseEntityAudit {

	private static final long serialVersionUID = 3323282328164482932L;

	public String title;
	public String description;
	public String thumbnail;
	public String images;
	public ShowRoom owner;

	public static Finder<Long, Advertisement> find = new Finder<Long, Advertisement>(Long.class, Advertisement.class);

	public void addImage(String img){
		if(images == null){
			images = "";
		}
		if(images.length() > 0){
			images += ",";
		}
		images += img; 
	}
	
	public String[] getImagesAsStringArray(){
		return images.split(",");
	}
	
	@Override
	public String toString() {
		String toString = super.toString()
				+ " [title=" + title + "]"
				+ " [description=" + description + "]"
				+ " [thumbnail=" + thumbnail + "]"
				+ " [owner=" + owner.name + "]"
				+ " [Images= Size: "+images+"]";
		return toString;
	}

	public static Advertisement findById(Long id) {
		return find.byId(id);
	}

}
