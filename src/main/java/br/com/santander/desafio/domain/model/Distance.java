package br.com.santander.desafio.domain.model;

public record Distance(String agencyId, double value) implements Comparable<Distance>{
    @Override public int compareTo(Distance o){ return Double.compare(this.value, o.value); }
}
