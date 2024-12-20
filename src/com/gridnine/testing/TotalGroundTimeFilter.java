package com.gridnine.testing;

import java.time.Duration;
import java.util.List;

/**
 * Фильтр, который проверяет общее время на земле между сегментами перелета.
 * <p>
 * Этот фильтр применяет правило, согласно которому общее время на земле
 * между всеми сегментами перелета не должно превышать двух часов.
 * Если у рейса менее двух сегментов, фильтр пропускает его.
 * </p>
 */
public class TotalGroundTimeFilter implements Filter {

    /**
     * Проверяет, соответствует ли перелет критериям по общему времени на земле.
     *
     * @param flight перелет, который необходимо проверить
     * @return {@code true}, если общее время на земле не превышает два часа или
     *         если у перелета менее двух сегментов; {@code false} в противном случае
     */
    @Override
    public boolean apply(Flight flight) {
        List<Segment> segments = flight.getSegments();

        // Проверяем, если у нас меньше двух сегментов, то нет времени на земле
        if (segments.size() < 2) {
            return true; // Если только один сегмент или меньше, то время на земле не считается
        }

        Duration totalGroundTime = Duration.ZERO;

        for (int i = 0; i < segments.size() - 1; i++) {
            Segment currentSegment = segments.get(i);
            Segment nextSegment = segments.get(i + 1);
            // Вычисляем время на земле между текущим и следующим сегментом
            Duration groundTime = Duration.between(currentSegment.getArrivalDate(), nextSegment.getDepartureDate());
            totalGroundTime = totalGroundTime.plus(groundTime);
        }

        // Проверяем, превышает ли общее время на земле два часа
        return totalGroundTime.toHours() <= 2;
    }
}
