package ru.job4j.puzzle.figures;

public interface Figure {
    default boolean movable() {
        return true;
    }
    //у общности "фигура" что есть: ячейка с x и y
    Cell position();
    //путь от начала до назначения
    Cell[] way(Cell source, Cell dest);
    //пнг-тип блок или чекер
    default String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );

    }
    //хз что это за метод, вероятно для перемещения
    Figure copy(Cell dest);

}