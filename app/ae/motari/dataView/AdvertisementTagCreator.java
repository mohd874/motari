package ae.motari.dataView;

import ae.motari.models.Advertisement;

public class AdvertisementTagCreator {

	public static Tag generateAdvertisementTag(Advertisement adv){
		Tag tag = new Tag();
		tag.title = adv.title;
		tag.description = adv.description;
		tag.imgSrc = adv.id+"/"+adv.thumbnail;
		tag.additionalData = new String[5];
		
		//add additionalData here
		
		return tag;
	}
}
