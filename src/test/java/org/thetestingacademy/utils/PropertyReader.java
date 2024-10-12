package org.thetestingacademy.utils;

import org.codehaus.groovy.runtime.StringGroovyMethods;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {

    //Responsibility of this clsss will provide key value pair

    public static String readKey(String Key)
    {
        Properties properties = new Properties();
        //Property is a class of collection framework //legacy class/old classes


        try {

            FileInputStream file = new FileInputStream("src/test/resources/data.properties");
            properties.load(file);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return properties.getProperty(Key);

    }
    public static Integer readKey(Integer Key)
    {
        Properties properties = new Properties();
        //Property is a class of collection framework //legacy class/old classes


        try {

            FileInputStream file = new FileInputStream("src/test/resources/data.properties");
            properties.load(file);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return  Integer.valueOf(properties.getProperty(String.valueOf(Key)));

    }


}
