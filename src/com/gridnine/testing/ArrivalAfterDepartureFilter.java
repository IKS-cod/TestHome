package com.gridnine.testing;

/**
 * Фильтр, который проверяет, что время прибытия каждого сегмента перелета
 * происходит после времени его отправления.
 * <p>
 * Этот фильтр применяется для проверки корректности временных меток
 * сегментов перелета. Если хотя бы один сегмент имеет время прибытия,
 * которое предшествует времени отправления, фильтр не пропускает этот
 * перелет.
 * </p>
 */
public class ArrivalAfterDepartureFilter implements Filter {

    /**
     * Проверяет, соответствует ли перелет критериям по времени прибытия и отправления.
     *
     * @param flight перелет, который необходимо проверить
     * @return {@code true}, если время прибытия каждого сегмента происходит после
     *         времени его отправления; {@code false} в противном случае
     */
    @Override
    public boolean apply(Flight flight) {
        return flight.getSegments().stream()
                .allMatch(segment -> segment.getArrivalDate().isAfter(segment.getDepartureDate()));
    }
}
