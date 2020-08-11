package server.models.util;

import lombok.Getter;

public class Triple<F, S, T> {
    @Getter public final F first;
    @Getter public final S second;
    @Getter public final T third;

    public Triple(F first, S second, T third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
}
