package com.gridnine.testing;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepartureAfterNowFilterTest {

    @Test
    public void ApplyAllSegmentsDepartAfterNowTest() {
        // Получаем текущее локальное время
        LocalDateTime now = LocalDateTime.now();

        // Создаем сегменты с корректными временными метками (после текущего времени)
        Segment segment1 = new Segment(now.plusHours(1), now.plusHours(2)); // Отправление через 1 час
        Segment segment2 = new Segment(now.plusHours(3), now.plusHours(4)); // Отправление через 3 часа

        // Создаем перелет с этими сегментами
        Flight flight = new Flight(Arrays.asList(segment1, segment2));

        // Создаем фильтр
        DepartureAfterNowFilter filter = new DepartureAfterNowFilter();

        // Проверяем, что фильтр возвращает true
        assertTrue(filter.apply(flight), "Фильтр должен пропустить перелет, если все сегменты отправляются после текущего времени.");
    }

    @Test
    public void ApplySegmentDepartsBeforeNowTest() {
        // Получаем текущее локальное время
        LocalDateTime now = LocalDateTime.now();

        // Создаем сегменты с некорректными временными метками (один из них до текущего времени)
        Segment segment1 = new Segment(now.minusHours(1), now.plusHours(1)); // Отправление до текущего времени
        Segment segment2 = new Segment(now.plusHours(2), now.plusHours(3)); // Отправление через 2 часа

        // Создаем перелет с этими сегментами
        Flight flight = new Flight(Arrays.asList(segment1, segment2));

        // Создаем фильтр
        DepartureAfterNowFilter filter = new DepartureAfterNowFilter();

        // Проверяем, что фильтр возвращает false
        assertFalse(filter.apply(flight), "Фильтр не должен пропускать перелет, если хотя бы один сегмент отправляется до текущего времени.");
    }

    @Test
    public void ApplyEmptyFlightSegmentsTest() {
        // Создаем пустой перелет
        Flight flight = new Flight(Arrays.asList());

        // Создаем фильтр
        DepartureAfterNowFilter filter = new DepartureAfterNowFilter();

        // Проверяем поведение фильтра для пустого перелета (можно считать его валидным)
        assertTrue(filter.apply(flight), "Фильтр должен пропустить пустой перелет.");
    }
}
