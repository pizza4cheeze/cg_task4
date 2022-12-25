package ru.vsu.cs.Grushevskaya.draw;

public interface IFilter<T> {
    boolean permit(T value);
}
