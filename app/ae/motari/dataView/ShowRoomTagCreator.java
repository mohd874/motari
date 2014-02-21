package ae.motari.dataView;

import ae.motari.models.ShowRoom;

public class ShowRoomTagCreator {

	public static Tag generateTagFor(ShowRoom room){
		Tag tag = new Tag();
		
		tag.data.put("title", room.name);
		tag.data.put("description", room.description);
		tag.data.put("imgSrc", "cars/"+room.id+"/"+room.logo);
		tag.data.put("link", "/showRoom/"+room.id);
		
		return tag;
	}

}
