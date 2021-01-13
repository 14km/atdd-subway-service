package nextstep.subway.path.application.fare;

import nextstep.subway.auth.domain.LoginMember;
import nextstep.subway.line.domain.Line;
import nextstep.subway.line.domain.Section;
import nextstep.subway.member.MemberAcceptanceTest;
import nextstep.subway.path.application.PathFinder;
import nextstep.subway.station.domain.Station;
import org.jgrapht.GraphPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class FareCalculatorTest {

    private Line 신분당선;
    private Line 이호선;
    private Line 삼호선;
    private Station 강남역;
    private Station 양재역;
    private Station 교대역;
    private Station 남부터미널역;
    private PathFinder pathFinder;
    private LoginMember loginMember;

    /**
     * 교대역    --- *2호선* ---   강남역
     * |                        |
     * *3호선*                   *신분당선*
     * |                        |
     * 남부터미널역  --- *3호선* ---   양재
     */
    @BeforeEach
    public void setUp() {
        강남역 = new Station(1L, "강남역");
        양재역 = new Station(2L, "양재역");
        교대역 = new Station(3L, "교대역");
        남부터미널역 = new Station(4L, "남부터미널역");

        신분당선 = new Line(1L, "신분당선", "bg-red-600", 강남역, 양재역, 10, 900);
        이호선 = new Line("이호선", "bg-red-600", 교대역, 강남역, 10, 0);
        삼호선 = new Line("삼호선", "bg-red-600", 교대역, 양재역, 5, 300);
        삼호선.addLineStation(교대역, 남부터미널역, 3);
        pathFinder = new PathFinder(Arrays.asList(신분당선, 이호선, 삼호선));

        loginMember = new LoginMember();
    }

    @DisplayName("기본운임(10㎞ 이내)")
    @Test
    void calculateDistanceFare1() {
        //given
        GraphPath<Station, Section> path = pathFinder.getPath(강남역, 교대역);
        int distance = 10;
        //when
        int fare = new FareCalculator().calculateFare(distance, path.getEdgeList(), loginMember);

        //then
        assertThat(fare).isEqualTo(1250);
    }

    @DisplayName("10km 초과 ∼ 50km 까지 (5km마다 100원)")
    @Test
    void calculateDistanceFare2() {
        //given
        GraphPath<Station, Section> path = pathFinder.getPath(강남역, 교대역);
        int distance = 30;

        //when
        int fare = new FareCalculator().calculateFare(distance, path.getEdgeList(), loginMember);

        //then - fare = 1250 + 600(100 * 6)
        assertThat(fare).isEqualTo(1850);
    }

    @DisplayName("50km 초과 시 (8km마다 100원)")
    @Test
    void calculateDistanceFare3() {
        //given
        GraphPath<Station, Section> path = pathFinder.getPath(강남역, 교대역);
        int distance = 80;

        //when
        int fare = new FareCalculator().calculateFare(distance, path.getEdgeList(), loginMember);

        //then - fare = 1250 + 1000(100 * 10)
        assertThat(fare).isEqualTo(2250);
    }

    @DisplayName("900원 추가 요금이 있는 노선 8km 이용 시")
    @Test
    void calculateLineFare1() {
        //given
        GraphPath<Station, Section> path = pathFinder.getPath(강남역, 남부터미널역);

        //when
        int fare = new FareCalculator().calculateFare(8, path.getEdgeList(), new LoginMember());

        //then - fare = 1250 + 900
        assertThat(fare).isEqualTo(2150);
    }

    @DisplayName("900원 추가 요금이 있는 노선 12km 이용 시")
    @Test
    void calculateLineFare2() {
        //given
        GraphPath<Station, Section> path = pathFinder.getPath(강남역, 남부터미널역);
        //when
        int fare = new FareCalculator().calculateFare(12, path.getEdgeList(), loginMember);

        //then - fare = 1250 + 300(100 * 3) + 900
        assertThat(fare).isEqualTo(2450);
    }

    @DisplayName("청소년(13세 이상~19세 미만): 운임에서 350원을 공제한 금액의 20%할인")
    @Test
    void calculateAgeFare1() {
        //given
        GraphPath<Station, Section> path = pathFinder.getPath(강남역, 교대역);
        //when
        int fare = new FareCalculator().calculateFare(10, path.getEdgeList(),
                loginMember = new LoginMember(1L, MemberAcceptanceTest.EMAIL, 13));

        //then: fare = 1250 - ((1250 - 350) * 0.2) = 1070
        assertThat(fare).isEqualTo(1070);
    }

    @DisplayName("어린이(6세 이상~ 13세 미만): 운임에서 350원을 공제한 금액의 50%할인")
    @Test
    void calculateAgeFare2() {
        //given
        GraphPath<Station, Section> path = pathFinder.getPath(강남역, 교대역);

        //when
        int fare = new FareCalculator().calculateFare(10, path.getEdgeList(),
                loginMember = new LoginMember(1L, MemberAcceptanceTest.EMAIL, 12));

        //then fare = 1250 - ((1250 - 350) * 0.5) = 800
        assertThat(fare).isEqualTo(800);
    }
}