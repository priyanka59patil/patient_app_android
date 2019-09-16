package com.werq.patient.base;

import java.util.Set;

public interface BindableAdapter<T> {

    void setData(T Object);

    void changedPositions(Set<Integer> positions);
}
