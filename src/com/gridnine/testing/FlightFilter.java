package com.gridnine.testing;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для фильтрации перелетов на основе заданных фильтров.
 * <p>
 * Этот класс предоставляет метод для применения списка фильтров к
 * списку перелетов и вывода подходящих перелетов после применения
 * каждого фильтра.
 * </p>
 */
public class FlightFilter {

    /**
     * Фильтрует список перелетов на основе заданных фильтров.
     *
     * @param flights список перелетов, который необходимо отфильтровать
     * @param filters список фильтров, которые будут применены к перелетам
     */
    public void filterFlights(List<Flight> flights, List<Filter> filters) {
        // Сохраняем оригинальный список перелетов
        List<Flight> originalFlights = flights;

        for (Filter filter : filters) {
            // Применяем текущий фильтр к оригинальному списку
            List<Flight> filteredFlights = originalFlights.stream()
                    .filter(filter::apply)
                    .toList();

            // Выводим подходящие перелеты после применения каждого фильтра
            System.out.println("Подходящие перелеты после фильтра: " + filter.getClass().getSimpleName());
            if (filteredFlights.isEmpty()) {
                System.out.println("Нет подходящих перелетов.");
            } else {
                for (Flight flight : filteredFlights) {
                    System.out.println(flight); // Используем toString() для удобного вывода
                }
            }
            System.out.println(); // Пустая строка для разделения выводов
        }
    }
}
