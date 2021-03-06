package com.automation.env;

import com.automation.utils.PropertyReader;
import com.google.common.base.Strings;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Global {

    //Global variables
    public static WebDriver driver;
    public static String email;
    public static String password;
    public static String url;
    public static String browser;
    public static String env;
    public static Integer timeout;
    public static JSONObject testDataJsonObject;

    public static void loadJsonData() {
        try {
            InputStream inputStream = new FileInputStream("src/main/resources/TestData.json");
            String s = IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
            testDataJsonObject = new JSONObject(s);
        } catch(IOException e){e.printStackTrace();}
    }

    public static void loadProperties() {
        email = PropertyReader.readConfigProperties("email");
        password = PropertyReader.readConfigProperties("password");
        url = PropertyReader.readConfigProperties("url");
        timeout = Integer.parseInt(PropertyReader.readConfigProperties("timeout"));

        System.out.println("url: " + url);
        System.out.println("email: " + email);
        System.out.println("password: " + password);
    }

    public static void parseCommandLineArgs() {
        //Reading passing target.browser argument from command line
        String targetBrowser = System.getProperty("target.browser");
        if (!Strings.isNullOrEmpty(targetBrowser)) {
            browser = targetBrowser;
        } else {
            browser = PropertyReader.readConfigProperties("default.browser");
        }
        System.out.println("browser: " + browser);

        //Reading passing target.env argument from command line
        String targetEnv = System.getProperty("target.env");
        if (!Strings.isNullOrEmpty(targetEnv)) {
            env = targetEnv;
        } else {
            env = PropertyReader.readConfigProperties("default.env");
        }
        System.out.println("env: " + env);
    }
}