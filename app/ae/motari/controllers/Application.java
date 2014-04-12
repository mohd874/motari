package ae.motari.controllers;

import static play.data.Form.form;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import play.Logger;
import play.Routes;
import play.data.Form;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import ae.motari.dataView.ShowRoomTagCreator;
import ae.motari.dataView.Tag;
import ae.motari.forms.AdvertisementForm;
import ae.motari.forms.ShowRoomForm;
import ae.motari.models.Advertisement;
import ae.motari.models.CarType;
import ae.motari.models.ShowRoom;
import ae.motari.utils.FileUtil;
import ae.motari.views.html.advertisement;
import ae.motari.views.html.gallery;
import ae.motari.views.html.index;
import ae.motari.views.html.newAdvertisement;
import ae.motari.views.html.newShowRoom;
import ae.motari.views.html.searchAdvertisement;
import ae.motari.views.html.searchShowRoom;
import ae.motari.views.html.showRoom;

public class Application extends BaseController {

	public static Result index() {
		List<ShowRoom> rooms = ShowRoom.find.all();
		List<Advertisement> advs = Advertisement.find.all();
		return ok(index.render("Your new application is ready.", rooms, advs));
	}

	public static Result newShowRoom() {
		Form<ShowRoomForm> showRoomForm = form(ShowRoomForm.class);
		return ok(
				newShowRoom.render(showRoomForm)
		);
	}
	
	public static Result saveShowRoom(){
		Form<ShowRoomForm> showRoomForm = form(ShowRoomForm.class).bindFromRequest();
		ShowRoom room = showRoomForm.get().bind(new ShowRoom());
		FilePart logoImage = getFilePartUploadByString("logo");
		
		if(showRoomForm.hasErrors()){
			return badRequest(newShowRoom.render(showRoomForm));
		}
		
		room.logo = generateFileName(logoImage.getFilename());
		room.save();
		
		String uploadDir = generateShowRoomUploadDirString(room.id, room.logo);
		FileUtil.copyFile(logoImage.getFile(), new File(uploadDir));
		flash("success", "Show Room "+showRoomForm.get().name+" has been created");
		return index();
	}
	
	public static Result newAdvertisement(){
		Form<AdvertisementForm> advForm = form(AdvertisementForm.class);
		return ok(
				newAdvertisement.render(advForm, ShowRoom.find.all(), CarType.find.all())
		);
	}
	
	public static Result saveAdvertisement(){
		Form<AdvertisementForm> advForm = form(AdvertisementForm.class).bindFromRequest();
		Advertisement adv = advForm.get().bind(new Advertisement());
		List<FilePart> images = getFilePartUploads();
		FilePart thumbnailFilePart = getFilePartUploadByString("thumbnail");
		
		if(advForm.hasErrors()){
			return badRequest(newAdvertisement.render(advForm, ShowRoom.find.all(), CarType.find.all()));
		}
		
		adv.save();//for the new id
		adv.thumbnail = saveAdvertisementFile(adv, thumbnailFilePart);
		
		for(FilePart part : images){
			if(part != thumbnailFilePart){
				saveAdvertisementFile(adv, part);
			}
		}
		adv.save();
				
		return index();
	}

	public static Result showRoomDetails(Long id){
		ShowRoom room = ShowRoom.findById(id);
		return ok(showRoom.render("", room));
	}

	public static Result advertisementDetails(Long id){
		Advertisement adv = Advertisement.findById(id);
		return ok(advertisement.render("", adv));
	}
	
	public static Result todo(){
		return TODO;
	}
	
	public static Result searchShowRoom(String query){
		return ok(searchShowRoom.render("",ShowRoom.findWhereNameLike(query)));
	}

	public static Result searchAdvertisement(String query){
		return ok(searchAdvertisement.render("",Advertisement.findWhereTitleLike(query)));
	}

	public static Result gallery(String type, String view){
		List<Map<String,String>> data = new ArrayList<Map<String,String>>();
		
		if("adv".equals(type)){
			List<Advertisement> advs = Advertisement.find.all();
			Logger.info("advs: " + advs.size());
			for(Advertisement adv : advs){
//				Tag t = AdvertisementTagCreator.generateTagFor(adv);
//				tags.add(t);
				Map<String, String> m = new HashMap<>();
				m.put("title", adv.title);
				m.put("description", adv.description);
				m.put("imgSrc", "advertisements/"+adv.id+"/"+adv.thumbnail);
				m.put("link", "/advertisement/"+adv.id);
				data.add(m);
			}
		}else if("room".equals(type)){
			List<ShowRoom> rooms = ShowRoom.find.all();
			Logger.info("rooms: " + rooms.size());
			for(ShowRoom r : rooms){
				Map<String, String> m = new HashMap<>();
				m.put("title", r.name);
				m.put("description", r.description);
				m.put("imgSrc", "cars/"+r.id+"/"+r.logo);
				m.put("link", "/showRoom/"+r.id);
				data.add(m);
			}
		}
		return ok(gallery.render(view, data));
	}
	
	// javascript routes
	public static Result javascriptRoutes() {
	    response().setContentType("text/javascript");
	    return ok(
	    		Routes.javascriptRouter("myJsRoutes",
	            routes.javascript.Application.advertisementDetails(),
	            routes.javascript.Application.showRoomDetails(),
	            routes.javascript.Application.searchShowRoom(),
	            routes.javascript.Application.searchAdvertisement()
	        )
	    );
	}
	
	
	// private methods
//	private static Tag getInfoTagForAdvetisement(Advertisement adv){
//		return AdvertisementTagCreator.generateTagFor(adv);
//	}
	
	private static String saveAdvertisementFile(Advertisement adv, FilePart part) {
		String newFilename = generateFileName(part.getFilename());
		adv.addImage(newFilename);
		String uploadDir = generateAdvertisementUploadDirString(adv.id, newFilename);
		FileUtil.copyFile(part.getFile(), new File(uploadDir));
		return newFilename;
	}
	
	private static String generateShowRoomUploadDirString(Long id, String fileName) {
		return getApplicationString("file.upload.cars.dir") + "/" + id + "/" + fileName;
	}
	
	private static String generateAdvertisementUploadDirString(Long id, String fileName) {
		return getApplicationString("file.upload.advs.dir") + "/" + id + "/" + fileName;
	}
	
	private static String generateFileName(String fileName) {
		return System.nanoTime() + fileName.substring(fileName.indexOf("."));
	}
	
}
