package com.getgreetings.secondgreeting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SecondgreetingApplication {

	//client for performing http request
    private static RestTemplate  httpClient  = null;

    //base url for remote calls
    private static String baseURL = "http://localhost:8091/";

    //endpoint for remote calls
    private static String defaultGreetingURL = "greeting";
    private static String namedGreetingURL = "greeting/name?name=Sathish";

	public static void main(String[] args) {
		SpringApplication.run(SecondgreetingApplication.class, args);
		makeCalls();
	}
	//singleton pattern implemented to get a single instnace of the http clients 
    private static RestTemplate getHttpClient(){
        if(httpClient == null){
            httpClient = new RestTemplate();
        }
        return httpClient;
    }

    //call the default endpoint and get the response
    private static Greeting getGreeting(String url){
        RestTemplate restmp = getHttpClient();
        Greeting responce = restmp.getForObject(baseURL + "/" + url,Greeting.class);

        return responce;
    } 

    //call the named endpoint and get the response 
    private static Greeting getGreetingByName(String url){
        RestTemplate restmp = getHttpClient();
        Greeting responce = restmp.getForObject(baseURL + "/" + url,Greeting.class);

        return responce;

    }

    //call the endpoints, receive the responces and print them on the console
    private static void makeCalls(){
        Greeting receivedGreeting1 = SecondgreetingApplication.getGreeting(defaultGreetingURL);
        Greeting receivedGreeting2 = SecondgreetingApplication.getGreetingByName(namedGreetingURL);

        String content1 = receivedGreeting1.content();
        System.out.println(content1);

        String content2 = receivedGreeting2.content();
        System.out.println(content2);

    }


}
