package com.hive.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class HiveChatServerApplication {
	static {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		String mongoUri = dotenv.get("MONGODB_URI");
		System.setProperty("MONGODB_URI", mongoUri);
	}
	public static void main(String[] args) {
		SpringApplication.run(HiveChatServerApplication.class, args);
	}

}



