package forms;

import models.ShowRoom;

public class ShowRoomForm {

	public String name;
	public String location;
	public String logo;
	public String phone;
	public String email;
	public String description;

	public ShowRoom bind(ShowRoom room){
		room.name = name;
		room.location = location;
		room.logo = logo;
		room.phone = phone;
		room.email = email;
		room.description = description;
		
		return room;
	}
}
