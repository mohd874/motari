package controllers;

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
		
		return ok(index.render("Your new application is ready.", rooms));
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
		String uploadDir = generateUploadDirString(room.id, room.logo);
		FileUtil.copyFile(logoImage.getFile(), new File(uploadDir));
		flash("success", "Show Room "+showRoomForm.get().name+" has been created");
		return index();
	}
	
	public static Result newAdvertisement(){
		Form<forms.AdvertisementForm> advForm = form(forms.AdvertisementForm.class);
		return ok(
				newAdvertisement.render(advForm)
		);
	}
	
	public static Result saveAdvertisement(){
		Form<forms.AdvertisementForm> advForm = form(forms.AdvertisementForm.class).bindFromRequest();
		Advertisement adv = advForm.get().bind(new Advertisement());
		List<FilePart> images = getFilePartUploads();
		
		if(advForm.hasErrors()){
			return badRequest(newAdvertisement.render(advForm));
		}
		
		System.out.println(adv);
		for(FilePart part : images){
			System.out.println("filename: "+ part.getFilename());
		}
		return TODO;
	}

	private static String generateUploadDirString(Long id, String fileName) {
		return getApplicationString("file.upload.dir") + "/" + id + "/" + fileName;
	}
	
	private static String generateFileName(String fileName) {
		return System.nanoTime() + fileName.substring(fileName.indexOf("."));
	}
	
}
