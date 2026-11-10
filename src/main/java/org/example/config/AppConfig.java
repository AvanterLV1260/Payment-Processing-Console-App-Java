package org.example.config;

import lombok.Getter;

// Singleton configuration class
@Getter
public class AppConfig {
    private static AppConfig instance;

    private final String applicationName;
    private final String currency;
    private final double taxRate;

    private AppConfig(){
        this.applicationName = "Bootcamp Payment App";
        this.currency = "EUR";
        this.taxRate = 0.21;
    }

    public static AppConfig getInstance(){
        if (instance == null){
            instance = new AppConfig();
        }
        return instance;
    }
}
