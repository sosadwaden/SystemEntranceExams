package com.sosadwaden.mapper;

public interface Mapper<F, T> {

    T mapFrom(F object);
}
