package com.example.Validation.Config;

import com.example.Validation.Model.Profanity;
import com.example.Validation.Service.ProfanityServiceSQL;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ConfigDB implements ApplicationRunner {
    private final ProfanityServiceSQL profanityService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        initProfanity();
    }

    public void initProfanity() {
        Profanity p1 = new Profanity( "1");
        Profanity p2 = new Profanity("ывапр");
        Profanity p3 = new Profanity("жафвлажывла");
        Profanity p4 = new Profanity("22");
        Profanity p5 = new Profanity("мммммм");

//        profanityService.createProfanity("1");
//        profanityService.createProfanity("ывапр");
//        profanityService.createProfanity("жафвлажывла");
//        profanityService.createProfanity("22");
//        profanityService.createProfanity("мммммм");
    }
}
