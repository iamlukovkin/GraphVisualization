package ru.lyubakova.application.context;

import ru.lyubakova.application.handlers.CityCreateHandler;

public class Handlers {

    private static CityCreateHandler cityCreateHandler;

    public static CityCreateHandler cityCreateHandler() {
        if (cityCreateHandler == null) {
            cityCreateHandler = new CityCreateHandler();
        }
        return cityCreateHandler;
    }
}
