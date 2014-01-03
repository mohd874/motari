package forms;

import java.util.List;

import models.*;

public class AdvertisementForm {

	public String title;
	public String description;
	public String thumbnail;
	public Long owner;

	public Advertisement bind(Advertisement adv){
		adv.title = title;
		adv.description = description;
		adv.thumbnail = thumbnail;

		ShowRoom room = ShowRoom.find.byId(owner);
		adv.owner = room;
		return adv;
	}
}
