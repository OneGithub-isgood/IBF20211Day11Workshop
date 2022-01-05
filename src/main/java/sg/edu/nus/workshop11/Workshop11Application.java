package sg.edu.nus.workshop11;

import java.util.*;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class Workshop11Application {
	//Instantiate the Logger
	private static final Logger logger = LoggerFactory.getLogger(Workshop11Application.class);
	//Declare default fallback port to be used by Spring Boot Application
	private static final String DEFAULT_PORT = "8082";
	public static void main(String[] args) {
		logger.info("Workshop 11");
		//Init the spring app
		SpringApplication app = new SpringApplication(Workshop11Application.class);
		//Decode the java app args using Spring args helper
		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);
		//Return Java args as list of strings
		List optsVal = appArgs.getOptionValues("port");
		//Declare variable to hold port number to use in Spring Boot Application
		String portNumber = null;
		//Check if Args is empty or first element of Args is empty
		if (optsVal == null || optsVal.get(0) == null) {
			//Retrieve from OS environment variable
			portNumber = System.getenv("PORT");
			//Declare default fallback port if even OS environment variable is empty
			if (portNumber == null) {
				portNumber = DEFAULT_PORT;
			}
		} else {
			//Declare new port number from valid Args
			portNumber = (String)optsVal.get(0);
		}
		//Check if portNumber is not null
		if (portNumber != null) {
			//Override Spring Boot Application port number using defaultproperties from Spring Boot framework
			app.setDefaultProperties(Collections.singletonMap("server.port", portNumber));
		}
		app.run(args);
	}

}
