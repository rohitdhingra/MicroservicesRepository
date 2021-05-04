package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/hello/server")
public class HelloResource {
	
	@Value("${server.port}")
    private String portNumber;
	
	@GetMapping
	public String hello()
	{
		//For Timeout Example only
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println("Got the Request on port number:"+portNumber);
		return "Hello World!"+portNumber;
	}

}
