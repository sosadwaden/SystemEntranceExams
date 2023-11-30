package com.sosadwaden.entity;

import java.util.Arrays;
import java.util.Optional;

public enum Status {

    /**
     * DEFAULT - когда пользователь зарегистрировался на сайте,
     * но ещё не выбрал факультет для сдачи экзаменов
     */
    DEFAULT,
    TAKE_EXAMS,
    NOT_ENROLLED,
    ENROLLED;

    public static Optional<Status> find(String status) {
        return Arrays.stream(values())
                .filter(it -> it.name().equals(status))
                .findFirst();
    }
}
