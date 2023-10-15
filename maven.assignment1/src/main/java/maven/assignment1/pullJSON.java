package maven.assignment1;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class pullJSON {
	
	static List<Student> students = new ArrayList<Student>();

	public static void main(String[] args) throws ClientHandlerException, UniformInterfaceException, ParseException {
		// TODO Auto-generated method stub
		String url = "https://hccs-advancejava.s3.amazonaws.com/student.json";
		parseJSON(url);
		studentByName("caleb");
		studentByGender("male");
	}
	
	public static void parseJSON (String url) throws ClientHandlerException, UniformInterfaceException, ParseException {
		
		Client client = Client.create();
		WebResource webResource = client.resource(url);
		
		ClientResponse clientResponse = webResource.accept("application/json").get(ClientResponse.class);
		if (clientResponse.getStatus()!=200) {
			throw new RuntimeException("Failed"+clientResponse.toString());
		}
		
		JSONArray jsonArray = (JSONArray) new JSONParser().parse(clientResponse.getEntity(String.class));
		Iterator<Object> it = jsonArray.iterator();
		
		while (it.hasNext()) {
			JSONObject jsonObject = (JSONObject)it.next();
			int id = Integer.parseInt(jsonObject.get("id").toString());
			String firstName = jsonObject.get("first_name").toString();
			double gpa = Double.parseDouble(jsonObject.get("gpa").toString());
			String email = jsonObject.get("email").toString();
			String gender = jsonObject.get("gender").toString();
			Student newStudent = new Student(id,firstName,gpa,email,gender);
			System.out.println(newStudent.toString());
			students.add(newStudent);
		}
	}
	
	public static void studentByName(String f) {
		for (Student s: students) {
			if (s.getFirstName().toUpperCase().equals(f.toUpperCase())) {
				System.out.println("Student found: " + s.toString());
			}
		}
	}
	
	public static void studentByGender(String g) {
		for (Student s: students) {
			if (s.getGender().toUpperCase().equals(g.toUpperCase())) {
				System.out.println("Student found: "+s.toString());
			}
		}
	}

}
