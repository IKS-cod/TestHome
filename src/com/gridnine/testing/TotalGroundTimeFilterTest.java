package com.gridnine.testing;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TotalGroundTimeFilterTest {

    @Test
    public void ApplyTotalGroundTimeWithinLimitTest() {
        // Получаем текущее локальное время
        LocalDateTime now = LocalDateTime.now();

        // Создаем сегменты с корректными временными метками
        Segment segment1 = new Segment(now.plusHours(1), now.plusHours(2)); // Прибытие через 1 час, отправление через 2 часа
        Segment segment2 = new Segment(now.plusHours(3), now.plusHours(4)); // Прибытие через 3 часа, отправление через 4 часа

        // Создаем перелет с этими сегментами
        Flight flight = new Flight(Arrays.asList(segment1, segment2));

        // Создаем фильтр
        TotalGroundTimeFilter filter = new TotalGroundTimeFilter();

        // Проверяем, что фильтр возвращает true (общее время на земле 1 час)
        assertTrue(filter.apply(flight), "Фильтр должен пропустить перелет с общим временем на земле менее 2 часов.");
    }

    @Test
    public void ApplyTotalGroundTimeExceedsLimitTest() {
        // Получаем текущее локальное время
        LocalDateTime now = LocalDateTime.now();

        // Создаем сегменты с некорректными временными метками (общее время на земле превышает 2 часа)
        Segment segment1 = new Segment(now.plusHours(1), now.plusHours(2)); // Прибытие через 1 час, отправление через 2 часа
        Segment segment2 = new Segment(now.plusHours(5), now.plusHours(6)); // Прибытие через 5 часов, отправление через 6 часов

        // Создаем перелет с этими сегментами
        Flight flight = new Flight(Arrays.asList(segment1, segment2));

        // Создаем фильтр
        TotalGroundTimeFilter filter = new TotalGroundTimeFilter();

        // Проверяем, что фильтр возвращает false (общее время на земле 3 часа)
        assertFalse(filter.apply(flight), "Фильтр не должен пропускать перелет с общим временем на земле более 2 часов.");
    }

    @Test
    public void ApplyOneSegmentFlightTest() {
        // Получаем текущее локальное время
        LocalDateTime now = LocalDateTime.now();

        // Создаем сегмент (один сегмент)
        Segment segment = new Segment(now.plusHours(1), now.plusHours(2)); // Прибытие через 1 час, отправление через 2 часа

        // Создаем перелет с этим сегментом
        Flight flight = new Flight(Arrays.asList(segment));

        // Создаем фильтр
        TotalGroundTimeFilter filter = new TotalGroundTimeFilter();

        // Проверяем, что фильтр возвращает true (менее двух сегментов)
        assertTrue(filter.apply(flight), "Фильтр должен пропустить перелет с одним сегментом.");
    }

    @Test
    public void ApplyEmptyFlightSegmentsTest() {
        // Создаем пустой перелет
        Flight flight = new Flight(Arrays.asList());

        // Создаем фильтр
        TotalGroundTimeFilter filter = new TotalGroundTimeFilter();

        // Проверяем поведение фильтра для пустого перелета (можно считать его валидным)
        assertTrue(filter.apply(flight), "Фильтр должен пропустить пустой перелет.");
    }
}
