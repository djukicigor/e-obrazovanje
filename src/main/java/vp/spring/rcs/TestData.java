package vp.spring.rcs;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vp.spring.rcs.model.Author;
import vp.spring.rcs.model.Record;
import vp.spring.rcs.model.Style;
import vp.spring.rcs.model.user.SecurityUser;
import vp.spring.rcs.model.user.SecurityAuthority;
import vp.spring.rcs.model.user.SecurityUserAuthority;
import vp.spring.rcs.service.AuthorService;
import vp.spring.rcs.service.RecordService;
import vp.spring.rcs.service.StyleService;
import vp.spring.rcs.service.SecurityUserService;
import vp.spring.rcs.service.SecurityAuthorityService;
import vp.spring.rcs.service.SecurityUserAuthorityService;
import vp.spring.rcs.service.StudentService;
import vp.spring.rcs.model.user.Student;

@Component
public class TestData {

	@Autowired
	AuthorService authorService;

	@Autowired
	StyleService styleService;
	
	@Autowired
	RecordService recordService;
	
	@Autowired
	SecurityUserService securityUserService;
	
	@Autowired
	SecurityAuthorityService securityAuthorityService;
	
	@Autowired
	SecurityUserAuthorityService securityUserAuthorityService;
	
	@Autowired
	StudentService studentService;

	/*
	 * Inicijalizacija testnih podataka. 
	 */
	@PostConstruct
	public void init(){
		Author milesDavis = new Author("Miles Davis");
		authorService.save(milesDavis);
		
		Author johnColtrane = new Author("John Coltrane");
		authorService.save(johnColtrane);
		
		Author bobDylan = new Author("Aca Dylan");
		authorService.save(bobDylan);
		
		Style coolJazz = new Style("Cool jazz");
		styleService.save(coolJazz);

		Style jazz = new Style("Jazz");
		styleService.save(jazz);
		
		Style avantGardeJazz = new Style("Avant-garde jazz");
		styleService.save(avantGardeJazz);
		
		Style rockAndRoll = new Style("Rock and roll");
		styleService.save(rockAndRoll);
		
		Record highway61Revisited = new Record("Highway 61 Revisited", "https://upload.wikimedia.org/wikipedia/en/9/95/Bob_Dylan_-_Highway_61_Revisited.jpg",
				700, bobDylan);
		highway61Revisited.getStyles().add(rockAndRoll);
		recordService.save(highway61Revisited);
		
		Record birthOfTheCool = new Record("Birth of the Cool","https://upload.wikimedia.org/wikipedia/en/a/a8/Birth_of_the_Cool.jpg",
				950, milesDavis);
		birthOfTheCool.getStyles().add(jazz);
		birthOfTheCool.getStyles().add(coolJazz);
		recordService.save(birthOfTheCool);
		
		Record interstellarSpace = new Record("Interstellar Space", "https://upload.wikimedia.org/wikipedia/en/2/26/John_Coltrane_Interstellar_Space.jpg", 
				500, johnColtrane);
		interstellarSpace.getStyles().add(jazz);
		interstellarSpace.getStyles().add(avantGardeJazz);
		recordService.save(interstellarSpace);
		
		SecurityUser user1 = new SecurityUser((long)1, "User", "$2a$04$4pqDFh9SxLAg/uSH59JCB.LwIS6QoAjM9qcE7H9e2drFuWhvTnDFi", "Zoran", "Peric");
		securityUserService.save(user1);
		
		SecurityAuthority authority1 = new SecurityAuthority(1, "User");
		securityAuthorityService.save(authority1);
		
		SecurityUserAuthority userAuthority1 = new SecurityUserAuthority(1, user1, authority1);
		securityUserAuthorityService.save(userAuthority1);
		
		Student student1 = new Student((long)1, "pera", "$2a$04$4pqDFh9SxLAg/uSH59JCB.LwIS6QoAjM9qcE7H9e2drFuWhvTnDFi", "Petar", "Petrovic", 123456789, "SF100", 10000);
		studentService.save(student1);
		
//		Printing out objects as JSON for testing
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user1));
			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(student1));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
