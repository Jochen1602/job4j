package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.figures.white.BishopWhite;
import ru.job4j.chess.figures.Cell;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**class AppTest Тестирование задачи Каркас шахматной доски
 *@author antontokarev
 *@since 01.11.2018
 */
public class AppTest {
    @Test(expected = FigureNotFoundException.class)
    public void whenFigureWasNotFoundInCell() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        Board board = new Board();
        BishopWhite bishop = new BishopWhite(Cell.C1);
        board.add(bishop);
        board.move(Cell.B1, Cell.A2);
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenBishopTryToGoNotByDiagonal() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        Board board = new Board();
        BishopWhite bishop = new BishopWhite(Cell.C1);
        board.add(bishop);
        board.move(Cell.C1, Cell.A2);
    }

    @Test(expected = OccupiedWayException.class)
    public void whenItIsObstacleOnTheWay() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        Board board = new Board();
        BishopWhite bishop = new BishopWhite(Cell.C1);
        BishopWhite obstacle = new BishopWhite(Cell.B2);
        board.add(bishop);
        board.add(obstacle);
        board.move(Cell.C1, Cell.A3);
    }

    @Test()
    public void whenItIsNoObstaclesOnTheWay() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        Board board = new Board();
        BishopWhite bishop = new BishopWhite(Cell.C1);
        BishopWhite obstacle = new BishopWhite(Cell.B3);
        board.add(bishop);
        board.add(obstacle);
        board.move(Cell.C1, Cell.A3);
    }

    @Test()
    public void bishopWalksFromA3toF8() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        Board board = new Board();
        BishopWhite bishop = new BishopWhite(Cell.C1);
        board.add(bishop);
        Cell[] actual = bishop.way(Cell.C1, Cell.F4);
        Cell[] expected = {Cell.C1, Cell.D2, Cell.E3, Cell.F4};
        assertThat(actual, is(expected));
    }

    @Test()
    public void bishopWalksFromC1toA3() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        Board board = new Board();
        BishopWhite bishop = new BishopWhite(Cell.C1);
        board.add(bishop);
        Cell[] actual = bishop.way(Cell.C1, Cell.A3);
        Cell[] expected = {Cell.C1, Cell.B2, Cell.A3};
        assertThat(actual, is(expected));
    }
}