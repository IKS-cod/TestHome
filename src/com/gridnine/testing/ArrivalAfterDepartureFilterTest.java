package com.gridnine.testing;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArrivalAfterDepartureFilterTest {

    @Test
    public void ApplyAllSegmentsArriveAfterDepartureTest() {
        // Получаем текущее локальное время
        LocalDateTime now = LocalDateTime.now();

        // Создаем сегменты с корректными временными метками
        Segment segment1 = new Segment(now.plusHours(1), now.plusHours(2)); // Отправление через 1 час, прибытие через 2 часа
        Segment segment2 = new Segment(now.plusHours(3), now.plusHours(4)); // Отправление через 3 часа, прибытие через 4 часа

        // Создаем перелет с этими сегментами
        Flight flight = new Flight(Arrays.asList(segment1, segment2));

        // Создаем фильтр
        ArrivalAfterDepartureFilter filter = new ArrivalAfterDepartureFilter();

        // Проверяем, что фильтр возвращает true
        assertTrue(filter.apply(flight), "Фильтр должен пропустить перелет с корректными временными метками.");
    }

    @Test
    public void ApplySegmentArrivesBeforeDepartureTest() {
        // Получаем текущее локальное время
        LocalDateTime now = LocalDateTime.now();

        // Создаем сегменты с некорректными временными метками
        Segment segment1 = new Segment(now.plusHours(1), now.minusHours(1)); // Прибытие до отправления
        Segment segment2 = new Segment(now.plusHours(3), now.plusHours(4));

        // Создаем перелет с этими сегментами
        Flight flight = new Flight(Arrays.asList(segment1, segment2));

        // Создаем фильтр
        ArrivalAfterDepartureFilter filter = new ArrivalAfterDepartureFilter();

        // Проверяем, что фильтр возвращает false
        assertFalse(filter.apply(flight), "Фильтр не должен пропускать перелет с некорректными временными метками.");
    }

    @Test
    public void ApplyEmptyFlightSegmentsTest() {
        // Создаем пустой перелет
        Flight flight = new Flight(Arrays.asList());

        // Создаем фильтр
        ArrivalAfterDepartureFilter filter = new ArrivalAfterDepartureFilter();

        // Проверяем поведение фильтра для пустого перелета (можно считать его валидным)
        assertTrue(filter.apply(flight), "Фильтр должен пропустить пустой перелет.");
    }
}
