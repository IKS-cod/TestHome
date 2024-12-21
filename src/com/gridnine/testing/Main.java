package com.gridnine.testing;

import java.util.Arrays;
import java.util.List;

/**
 * Главный класс приложения, который запускает процесс фильтрации перелетов.
 */
public class Main {

    /**
     * Точка входа в приложение.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        // Создаем список перелетов с помощью билдера
        List<Flight> flightList = FlightBuilder.createFlights();

        // Создаем фильтры с пороговыми значениями времени
        Filter departureAfterNowFilter = new DepartureAfterNowFilter();
        Filter arrivalAfterDepartureFilter = new ArrivalAfterDepartureFilter();
        Filter totalGroundTimeFilter = new TotalGroundTimeFilter();

        // Создаем объект фильтрации
        FlightFilter flightFilter = new FlightFilter();

        // Фильтруем перелеты с использованием созданных фильтров и выводим результат после каждого фильтра
        flightFilter.filterFlights(flightList,
                Arrays.asList(departureAfterNowFilter, arrivalAfterDepartureFilter, totalGroundTimeFilter));
    }
}


