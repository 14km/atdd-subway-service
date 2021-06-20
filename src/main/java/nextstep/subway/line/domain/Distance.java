package nextstep.subway.line.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Distance {
    public static final String INVALID_DISTANCE_EXCEPTION_MESSAGE = "구간 거리는 0보다 커야합니다.";
    public static final String BIGGER_THAN_DISTANCE_EXCEPTION_MESSAGE = "역과 역 사이의 거리보다 좁은 거리를 입력해주세요.";

    @Column
    private int distance;

    protected Distance() {
    }

    private Distance(int distance) {
        validate(distance);
        this.distance = distance;
    }

    public static Distance valueOf(int distance){
        return new Distance(distance);
    }

    private void validate(int distance) {
        if (distance < 0) {
            throw new IllegalArgumentException(INVALID_DISTANCE_EXCEPTION_MESSAGE);
        }
    }

    public int getDistance() {
        return distance;
    }

    public void minus(Distance distanceToMinus) {
        if (!isAvailableMinus(distanceToMinus)) {
            throw new IllegalArgumentException(BIGGER_THAN_DISTANCE_EXCEPTION_MESSAGE);
        }
        distance -= distanceToMinus.getDistance();
    }

    private boolean isAvailableMinus(Distance distanceToMinus){
        return distance > distanceToMinus.getDistance();
    }

    public void plus(Distance distanceToPlus) {
        distance += distanceToPlus.getDistance();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distance distance1 = (Distance) o;
        return distance == distance1.distance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(distance);
    }
}
