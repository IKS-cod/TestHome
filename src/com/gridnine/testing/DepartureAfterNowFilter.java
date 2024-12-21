package com.gridnine.testing;

import java.time.LocalDateTime;

/**
 * Фильтр, который проверяет, что все сегменты перелета отправляются после текущего времени.
 * <p>
 * Этот фильтр применяется для определения, подходит ли перелет на основе
 * времени отправления его сегментов. Если хотя бы один сегмент отправляется
 * до текущего момента, фильтр не пропускает этот перелет.</p>
 */
public class DepartureAfterNowFilter implements Filter {

    /**
     * Проверяет, соответствует ли перелет критериям по времени отправления.
     *
     * @param flight перелет, который необходимо проверить
     * @return {@code true}, если все сегменты перелета отправляются после текущего времени;
     *         {@code false} в противном случае
     */
    @Override
    public boolean apply(Flight flight) {
        LocalDateTime now = LocalDateTime.now();
        return flight.getSegments().stream()
                .allMatch(segment -> segment.getDepartureDate().isAfter(now));
    }
}
