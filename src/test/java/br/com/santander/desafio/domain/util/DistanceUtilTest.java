package br.com.santander.desafio.domain.util;

import br.com.santander.desafio.domain.model.Point;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DistanceUtilTest {

    private final DistanceUtil service = new DistanceUtil();

    @Test
    void calculate_deveRetornarHipotenusa() {
        var a = Point.builder().x(0L).y(0L).build();
        var b = Point.builder().x(3L).y(4L).build();

        var d = DistanceUtil.calculate(a, b);
        assertThat(d).isEqualTo(5.0);
    }

    @Test
    void getRoundedDistance_deveFormatarComDuasCasas() {
        var s = DistanceUtil.getRoundedDistance(12.3);
        assertThat(s).isEqualTo("12,30");
    }
}
