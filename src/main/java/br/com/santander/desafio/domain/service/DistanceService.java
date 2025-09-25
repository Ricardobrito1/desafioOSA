package br.com.santander.desafio.domain.service;

import br.com.santander.desafio.domain.model.Point;

public class DistanceService {
        public double calculate(Point a, Point b){
            // calcula a diferença entre as coordenadas X
            long dx = (long)a.x() - b.x();

            // calcula a diferença entre as coordenadas Y
            long dy = (long)a.y() - b.y();

            return Math.hypot(dx, dy);
        }
}
