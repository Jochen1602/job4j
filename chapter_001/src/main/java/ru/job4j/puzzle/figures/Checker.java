package ru.job4j.puzzle.figures;

public class Checker implements Figure {
    private final Cell position;

    public Checker(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        //пустой массив шагов
        Cell[] steps = new Cell[0];
        //если перемещение только на 1 клетку не важно куда, тогда ок, в массив шагов его
        if ((Math.abs(source.x - dest.x) + Math.abs(source.y - dest.y)) == 1) {
            steps = new Cell[]{dest};
        }
        return steps;
    }
    //оно двигается по возможности
    @Override
    public Figure copy(Cell dest) {
        return new Checker(dest);
    }
}