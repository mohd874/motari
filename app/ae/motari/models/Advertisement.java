package ae.motari.models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
@Table(name = "ADVERTISEMENT")
public class Advertisement extends BaseEntityAudit {

	private static final long serialVersionUID = 3323282328164482932L;

    @Constraints.Required
	public String title;

	public String description;

    @Constraints.Required
	public String thumbnail;

	public String images;

    @ManyToOne
	public ShowRoom owner;
	
    @Constraints.Required
	public int price;

	public int countViews;

	public String manufacturer;

	public String model;

	public int yearMade;

    @ManyToOne
	public CarType carType;

    public String bodyCondition;

    public String carCondition;

    public String engineCondition;

    public String driveTrain;

    public int numberOfDoors;

    public String exteriorColor;

    public String interiorColor;

    public int distanceDriven;

    public String specs;

    public int numberOfCylinders;

    public int horsePower;

    public String fuelType;

    public String warrenty;

    public String extras;

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

    public static List<Advertisement> getRecentTen(){
        return find.orderBy("id desc").findPagingList(10).setFetchAhead(false).getPage(1).getList();
    }
}
