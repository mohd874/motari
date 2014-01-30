package ae.motari.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model.Finder;

@Entity
@Table(name = "CAR_TYPE")
public class CarType extends BaseEntityAudit {

	@Required
	public String name;
	
	public CarType(String _name) {
		name = _name;
	}
	
	public static Finder<Long, CarType> find = new Finder<Long, CarType>(Long.class, CarType.class);
	
	public static CarType findByExactName(String name){
		if(null == name || name.trim().length() < 1){
			return null;
		}else{
			List<CarType> res = find.where().ieq("name", name).findList();
			if(res.size() > 0)
				return res.get(0);
			else
				return null;
		}
	}
}
