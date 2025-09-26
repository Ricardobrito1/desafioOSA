package br.com.santander.desafio.domain.model;

import lombok.Builder;

@Builder
public record Distance(
        String agencyId,
        Double value
) implements Comparable<Distance> {
    @Override
    public int compareTo(Distance o) {
        return Double.compare(this.value, o.value);
    }
}
