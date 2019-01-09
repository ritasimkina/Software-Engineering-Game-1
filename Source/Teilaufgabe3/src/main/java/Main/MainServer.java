package Main;

import java.net.MalformedURLException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class MainServer {

  /* Note, the server was already configured by us to run on port 18235, you can adapt this in the application.properties.
   * Note, if you would like to run/debug the server multiple times in a row you will need to close all old running versions as
   * otherwise the port would already be recognized to be in use. You can do this in the debug screen, select the server instance and press the red
   * stop button in the toolbar.*/

  public static void main(String[] args) throws MalformedURLException {
    SpringApplication.run(MainServer.class, args);
  }
}
