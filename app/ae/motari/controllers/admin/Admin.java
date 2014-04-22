package ae.motari.controllers.admin;

import play.Logger;
import play.mvc.*;
import play.mvc.Http.MultipartFormData.FilePart;
import play.Routes;
import play.data.*;
import static play.data.Form.*;
import ae.motari.utils.FileUtil;
import views.html.*;

import java.io.File;
import java.util.*;

import ae.motari.views.html.*;
import ae.motari.views.html.admin.*;
import ae.motari.controllers.*;
import ae.motari.models.*;

public class Admin extends BaseController {
	
	public static Result index() {
		return ok(admin.render(""));
	}
	
	public static Result crudCarType(){
		return ok(crudCarType.render("", CarType.find.all()));
	}
	
	public static Result saveCarType(){
		Form<CarType> form = form(CarType.class).bindFromRequest();
		CarType newType = form.get();
		
		newType.save();

		return crudCarType();
	}
	
	public static Result updateCarType(){
		Form<CarType> form = form(CarType.class).bindFromRequest();
		CarType formObj = form(CarType.class).bindFromRequest().get();
		CarType modifiedType = CarType.find.byId(formObj.id);
		
		Logger.info("id: "+modifiedType.id);
		
		modifiedType.name = formObj.name;
		modifiedType.update();
		
		return crudCarType();
	}
	
	public static Result deleteCarType(Long id){
		CarType deleteType = CarType.find.byId(id);
		
		deleteType.delete();
		
		return crudCarType();
	}
}
