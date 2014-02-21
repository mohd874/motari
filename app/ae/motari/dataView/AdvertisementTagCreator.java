package ae.motari.dataView;

import ae.motari.models.Advertisement;

public class AdvertisementTagCreator {

	public static Tag generateTagFor(Advertisement adv){
		Tag tag = new Tag();
		
		tag.data.put("title", adv.title);
		tag.data.put("description", adv.description);
		tag.data.put("imgSrc", "advertisements/"+adv.id+"/"+adv.thumbnail);
		tag.data.put("link", "/advertisement/"+adv.id);
		
		return tag;
	}
}
