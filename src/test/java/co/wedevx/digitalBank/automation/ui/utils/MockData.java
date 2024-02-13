package co.wedevx.digitalBank.automation.ui.utils;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class MockData {

    private FakeValuesService fakeValuesService=new FakeValuesService(
        new Locale("en-US"), new RandomService());

    public String generateRandomEmail(){
        String email = fakeValuesService.bothify(new Faker().name().firstName()+
                "####@gmail.com");
        return email;
    }

    public String generateRandomSsn(){
        String ssn = String.format("%09d", new Random().nextInt(1000000000));
        return ssn;
    }

    public Map<String,String> generateRandomNameAndEmail(){
        String name = fakeValuesService.bothify(new Faker().name().firstName());
        String email = fakeValuesService.bothify(name + "##@gmail.com");

        Map<String,String > data = new HashMap<>();
        data.put("name", name);
        data.put("email",email);

        return data;

    }


    public static void main(String[] args) {
        MockData mockDara = new MockData();
        System.out.println(mockDara.generateRandomEmail());


        System.out.println(mockDara.generateRandomSsn());

        System.out.println("========");

        Map<String,String > data = mockDara.generateRandomNameAndEmail();
        System.out.println(data.get("name"));
        System.out.println(data.get("email"));

    }
}
