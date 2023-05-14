package main;

import pieces.Piece;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Input extends MouseAdapter {

    //obiekt szachownicy 
    Board board; 

    //Konstruktor klasy Input, przyjmujący obiekt klasy Board jako argument.
    public Input(Board board) {
        this.board = board;
    }

    
  /*
  Metoda obsługująca zdarzenie przyciśnięcia przycisku myszy.
  Sprawdza, czy pod klikniętymi współrzędnymi znajduje się figura.
  Jeśli tak, to ustawia tę figurę jako zaznaczoną na planszy.
 */
    
   @Override
    public void mousePressed(MouseEvent e) {

        int col = e.getX() / board.tileSize;
        int row = e.getY() / board.tileSize;

        Piece pieceXY = board.getPiece(col, row);
        if (pieceXY != null) {
            board.selectedPiece = pieceXY;
        }


    }

    /*
  Metoda obsługująca zdarzenie przeciągania myszy.
  Przesuwa zaznaczoną figurę po planszy zgodnie z ruchem myszy.
 Odświeża wyświetlanie planszy. */
    
    @Override
    public void mouseDragged(MouseEvent e) {

        if(board.selectedPiece != null){
            board.selectedPiece.xPos = e.getX() - board.tileSize / 2;
            board.selectedPiece.yPos = e.getY() - board.tileSize / 2;

            board.repaint();

        }

    }


    /*
  Metoda obsługująca zdarzenie puszczenia przycisku myszy.
 Tworzy obiekt klasy Move opisujący ruch przesunięcia figury na planszy.
  Sprawdza, czy ruch jest poprawny i wykonuje go na planszy.
  W przypadku ruchu niepoprawnego zwraca figurę na jej pierwotne miejsce na planszy.
  Odświeża wyświetlanie planszy.
 */
    
    @Override
    public void mouseReleased(MouseEvent e) {

        int col = e.getX() / board.tileSize;
        int row = e.getY() / board.tileSize;

        if(board.selectedPiece != null){
            //instancja, wywolanie konstruktora klasy Move,
            //ktory tworzy nowy obiekt tej klasy
            Move move = new Move(board, board.selectedPiece, col, row);

            if(board.isValidMove(move)){
               board.makeMove(move);

            } else {
                board.selectedPiece.xPos = board.selectedPiece.col * board.tileSize;
                board.selectedPiece.yPos = board.selectedPiece.row * board.tileSize;

            }

        }

        board.selectedPiece = null;
        board.repaint();


    }

}
