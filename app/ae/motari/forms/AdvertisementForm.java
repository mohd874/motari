package ae.motari.forms;

import java.util.List;

import ae.motari.models.*;

public class AdvertisementForm {

	public String title;
	public String description;
	public String thumbnail;
	public Long owner;

	public int price;
	public String manufacturer;
	public String model;
	public int yearMade;
	public String type;
	
	public Advertisement bind(Advertisement adv){
		adv.title = title;
		adv.description = description;
		adv.thumbnail = thumbnail;

		adv.price = price;
		adv.manufacturer = manufacturer;
		adv.model = model;
		adv.yearMade = yearMade;
		adv.type = CarType.findByExactName(type);
		
		ShowRoom room = ShowRoom.find.byId(owner);
		adv.owner = room;
		return adv;
	}
}
