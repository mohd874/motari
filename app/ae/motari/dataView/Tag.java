package ae.motari.dataView;

import java.util.*;

public class Tag {

	public String title;
	public String description;
	public String imgSrc;
	public String[] additionalData;
	public Map<String, String> data;
	
	public Tag() {
		data = new HashMap<String, String>();
	}
}
