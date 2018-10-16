package ru.job4j.puzzle.figures;

public class Block implements Figure {
    private final Cell position;
    //блок у нас имеет финальные координаты ячейки
    public Block(final Cell position) {
        this.position = position;
    }
    //расположение
    @Override
    public Cell position() {
        return this.position;
    }
    //путь отсутствует, ибо блок зафиксирован
    @Override
    public Cell[] way(Cell source, Cell dest) {
        return new Cell[0];
    }
    //блок нельзя двигать
    @Override
    public Figure copy(Cell dest) {
        throw new IllegalStateException("Block could not move.");
    }
    //блок - это недвижимость
    @Override
    public boolean movable() {
        return false;
    }
}