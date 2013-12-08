package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import play.Play;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;

public class BaseController extends Controller {

	protected static File getFileUploadByString(String fieldName){
		MultipartFormData body = request().body().asMultipartFormData();
    	MultipartFormData.FilePart fileInput = body.getFile(fieldName);
    	
    	File result = null;
    	
    	if(fileInput != null){
    		result = fileInput.getFile();
    	}
    	
    	return result;
	}
	
	protected static List<File> getFilesUpload(){
		MultipartFormData body = request().body().asMultipartFormData();
    	List<MultipartFormData.FilePart> files = body.getFiles();
    	
    	List<File> result = new ArrayList<File>(files.size());
    	for (MultipartFormData.FilePart file : files) {
    		result.add(file.getFile());
		}
    	
    	return result;
	}
	
	protected static FilePart getFilePartUploadByString(String fieldName){
		return request().body().asMultipartFormData().getFile(fieldName);
	}
	
	protected static List<FilePart> getFilePartUploads(){
		return request().body().asMultipartFormData().getFiles();
	}
	
	protected static String getApplicationString(String arg){
		return Play.application().configuration().getString(arg);
	}
}
