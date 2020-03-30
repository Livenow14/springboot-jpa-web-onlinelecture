package livenowjpaweb.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {

		Hello hello= new Hello();
		hello.setDate("hello");
		String Data = hello.getDate();
		System.out.println("Data = " + Data);
		SpringApplication.run(JpashopApplication.class, args);
	}
	}




