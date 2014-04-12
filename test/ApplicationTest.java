import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ae.motari.models.*;

import com.fasterxml.jackson.databind.JsonNode;

import models.*;

import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;
import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

/**
 * 
 * Simple (JUnit) tests that can call all parts of a play app. If you are
 * interested in mocking a whole application, see the wiki for more details.
 * 
 */
public class ApplicationTest {

	private FakeApplication app;
	
	@Before
	public void setup(){
		Map<String, String> settings = new HashMap<String, String>();
		settings.put("db.default.url", "jdbc:h2:tcp://localhost/D:/workspace/motari/data/test-db");
		settings.put("db.default.jndiName", "Default");
		app = Helpers.fakeApplication(settings);
	}
	// @Test
	// public void simpleCheck() {
	// int a = 1 + 1;
	// assertThat(a).isEqualTo(2);
	// }

	// @Test
	// public void renderTemplate() {
	// Content html = views.html.index.render("Your new application is ready.",
	// ShowRoom.find.all(), Advertisement.find.all());
	// assertThat(contentType(html)).isEqualTo("text/html");
	// assertThat(contentAsString(html)).contains("Your new application is ready.");
	// }

	@Test
	public void searchShowRoomTest() {
		running(app, new Runnable() {
			public void run() {
				String searchTerm = "car";
				List<ShowRoom> result = ShowRoom.findWhereNameLike(searchTerm);
				assertThat(result.size()).isGreaterThan(0);
			}
		});
	}

}
