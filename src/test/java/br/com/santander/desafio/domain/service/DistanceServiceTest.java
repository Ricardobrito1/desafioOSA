package br.com.santander.desafio.domain.service;
import br.com.santander.desafio.domain.model.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class DistanceServiceTest {

    private final DistanceService service = new DistanceService();

    @Test
    void deveCalcularDistanciaEuclidiana() {
        var a = new Point(-5, -2);
        var b = new Point(-2, 2);
        double d = service.calculate(a, b);
        assertEquals(5.0, d, 1e-9);
    }
}
