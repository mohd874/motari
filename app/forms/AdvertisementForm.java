package forms;

import java.util.List;

import models.*;

public class AdvertisementForm {

	public String title;
	public String description;
	public String thumbnail;
	public List<String> images;
	public String owner;

	public Advertisement bind(Advertisement adv){
		adv.title = title;
		adv.description = description;
		adv.thumbnail = thumbnail;
//		adv.owner = owner;
		adv.images = images;
		
		return adv;
	}
}
