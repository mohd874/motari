package controllers;

import play.Logger;
import play.mvc.*;
import play.mvc.Http.MultipartFormData.FilePart;
import play.data.*;
import static play.data.Form.*;
import utils.FileUtil;
import views.html.*;

import java.io.File;
import java.util.*;

import models.*;

public class Application extends BaseController {

	public static Result index() {
		List<ShowRoom> rooms = ShowRoom.find.all();
		List<Advertisement> advs = Advertisement.find.all();
		return ok(index.render("Your new application is ready.", rooms, advs));
	}

	public static Result newShowRoom() {
		Form<forms.ShowRoomForm> showRoomForm = form(forms.ShowRoomForm.class);
		return ok(
				newShowRoom.render(showRoomForm)
		);
	}
	
	public static Result saveShowRoom(){
		Form<forms.ShowRoomForm> showRoomForm = form(forms.ShowRoomForm.class).bindFromRequest();
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
		Form<forms.AdvertisementForm> advForm = form(forms.AdvertisementForm.class);
		return ok(
				newAdvertisement.render(advForm, ShowRoom.find.all())
		);
	}
	
	public static Result saveAdvertisement(){
		Form<forms.AdvertisementForm> advForm = form(forms.AdvertisementForm.class).bindFromRequest();
		Advertisement adv = advForm.get().bind(new Advertisement());
		List<FilePart> images = getFilePartUploads();
		FilePart thumbnailFilePart = getFilePartUploadByString("thumbnail");
		
		if(advForm.hasErrors()){
			return badRequest(newAdvertisement.render(advForm, ShowRoom.find.all()));
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
		return ok(views.html.showRoom.render("", room));
	}

	public static Result advertisementDetails(Long id){
		Advertisement adv = Advertisement.findById(id);
		
//		Logger.info(adv.thumbnail.toString());
//		Logger.info(adv.toString());
//		return todo();
		return ok(views.html.advertisement.render("", adv));
	}
	
	public static Result todo(){
		return TODO;
	}
	
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
