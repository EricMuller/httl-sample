package com.emu.apps.sample;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.emu.apps.sample.model.Config;
import httl.*;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Launcher {

    public static void main(String[] args) throws Exception {

        URL resource = Launcher.class.getResource("config.json");
        //Path path = Paths.get(resource.toURI()).toFile();

        byte[] jsonData = Files.readAllBytes(Paths.get(resource.toURI()));

        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        //convert json string to object
        Config config = objectMapper.readValue(jsonData, Config.class);

        System.out.println("com.emu.apps.sample.model.Config Object\n" + config);

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("config", config);

        Engine engine = Engine.getEngine();
        Template template = engine.getTemplate("com/emu/apps/sample/workflow.httl");
        template.render(parameters, System.out);


    }

}
