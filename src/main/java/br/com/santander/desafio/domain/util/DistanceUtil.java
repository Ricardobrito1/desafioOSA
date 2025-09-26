package br.com.santander.desafio.domain.util;

import br.com.santander.desafio.domain.model.Point;

public class DistanceUtil {
        public static Double calculate(Point a, Point b){
            // calcula a diferença entre as coordenadas X
            Long dx = a.x() - b.x();

            // calcula a diferença entre as coordenadas Y
            Long dy = a.y() - b.y();

            return Math.hypot(dx, dy);
        }

        public static String getRoundedDistance(Double distance){
            return String.format("%.2f", distance);
        }
}
