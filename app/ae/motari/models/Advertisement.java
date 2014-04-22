package ae.motari.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ADVERTISEMENT")
public class Advertisement extends BaseEntityAudit {

	private static final long serialVersionUID = 3323282328164482932L;

	public String title;
	public String description;
	public String thumbnail;
	public String images;
	public ShowRoom owner;
	
	public int price;
	public int countViews;
	public String manufacturer;
	public String model;
	public int yearMade;
	public CarType type;

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
				+ " [owner=" + owner.name + "]";
		return toString;
	}

	public static Advertisement findById(Long id) {
		return find.byId(id);
	}

	public static List<Advertisement> findWhereTitleLike(String title) {
		if(null == title || title.trim() == "")
			return find.all();
		else
			return find.where()
			.ilike("title", "%"+title+"%")
			.findList();
	}

}
