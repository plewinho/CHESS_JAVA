

package pieces;

import main.Board;

import java.awt.image.BufferedImage;

public class Queen extends Piece {
    // konstruktor Knight dziedziczacy po klasie Piece
    public Queen(Board board, int col, int row, boolean isWhite) {
        //wywolanie konstruktora klasy bazowej Piece
        super(board);

        // Inicjalizacja pozycji i koloru figury
        this.col = col;
        this.row = row;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;
        this.isWhite = isWhite;

        //Inicjalizacja nazwy i wybor jego obrazka(sprite)
        this.name = "Queen";
        this.sprite = sheet.getSubimage(1 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
    }
}