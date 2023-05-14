package main;

import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

//klasa board dziedziczy po klasie jpanel 
public class Board extends JPanel {

    public int tileSize= 85; // rozmiar pojedynczego pola szachowncy
    int cols = 8; // liczba kolumn szachownicy
    int rows = 8; // liczba wierszy szachownicy

    ArrayList<Piece> pieceList = new ArrayList<>(); // lista przechowuje wszystkie figury na planszy

    
    //aktualnie wybrana figura na planszy
    public Piece selectedPiece;

    //obiekt klasy input reprezentuje interakcje z plansza i potem przypisujemy do board
    Input input = new Input(this);

    //konstruktor klasy Board, inicjalizuje plansze, myszke, figure
    public Board (){
        this.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));
        this.addMouseListener(input);
        this.addMouseMotionListener(input);

    addPieces(); // dodanie figur do listy
    }

    //zwraca figure znajdujaca sie na danym polu
    public Piece getPiece(int col, int row){

        for(Piece piece : pieceList){
            if(piece.col == col && piece.row == row){
                return piece;
            }
        }

      return null;
    }

    //wykonuje ruch figury na planszy
    public void makeMove(Move move){

        move.piece.col = move.newCol;
        move.piece.row = move.newRow;
        move.piece.xPos = move.newCol * tileSize;
        move.piece.yPos = move.newRow * tileSize;

        capture(move);
    }

    //usuwa zbitego przeciwnika z planszy
    public void capture(Move move){

        pieceList.remove(move.capture);

    }
    
    //sprawdza czy ruch poprawny
    public boolean isValidMove(Move move){
        if(sameTeam(move.piece, move.capture)){

            return false;
        }


        return true;
    }
//sprawdza czy 2 figury naleza do tej samej druzyny
    public boolean sameTeam(Piece p1, Piece p2) {
        if(p1 == null || p2 == null) {
            return false;
        }

        return p1.isWhite == p2.isWhite;
    }

    public void addPieces(){

        // dodawanie figur na plansze

        //konie
        pieceList.add(new Knight(this, 1, 0, false));
        pieceList.add(new Knight(this, 6, 0, false));
        pieceList.add(new Knight(this, 1, 7, true));
        pieceList.add(new Knight(this, 6, 7, true));

        //krolowa
        pieceList.add(new Queen(this, 3, 0, false));
        pieceList.add(new Queen(this, 3, 7, true));

        //krol
        pieceList.add(new King(this, 4, 0, false));
        pieceList.add(new King(this, 4, 7, true));

        //wieza
        pieceList.add(new Rock(this, 0, 0, false));
        pieceList.add(new Rock(this, 7, 0, false));
        pieceList.add(new Rock(this, 0, 7, true));
        pieceList.add(new Rock(this, 7, 7, true));

        //goniec
        pieceList.add(new Bishop(this, 2, 0, false));
        pieceList.add(new Bishop(this, 5, 0, false));
        pieceList.add(new Bishop(this, 2, 7, true));
        pieceList.add(new Bishop(this, 5, 7, true));

        //pionki

        pieceList.add(new Pawn(this, 0, 1, false));
        pieceList.add(new Pawn(this, 1, 1, false));
        pieceList.add(new Pawn(this, 2, 1, false));
        pieceList.add(new Pawn(this, 3, 1, false));
        pieceList.add(new Pawn(this, 4, 1, false));
        pieceList.add(new Pawn(this, 5, 1, false));
        pieceList.add(new Pawn(this, 6, 1, false));
        pieceList.add(new Pawn(this, 7, 1, false));


        pieceList.add(new Pawn(this, 0, 6, true));
        pieceList.add(new Pawn(this, 1, 6, true));
        pieceList.add(new Pawn(this, 2, 6, true));
        pieceList.add(new Pawn(this, 3, 6, true));
        pieceList.add(new Pawn(this, 4, 6, true));
        pieceList.add(new Pawn(this, 5, 6, true));
        pieceList.add(new Pawn(this, 6, 6, true));
        pieceList.add(new Pawn(this, 7, 6, true));






    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        // petla rysuje pola szachoniwcy
    for(int r= 0; r < rows; r++)
        for(int c= 0; c<cols; c++)
        {
            g2d.setColor((c+r) %2== 0 ? Color.white : Color.green); // bialy/czarwony w zaleznosci od pozycji pola
            g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize); // narysowanie prostokata ktory polem szachownicy
        }

    //rysowanie figur na planszy
    for(Piece piece : pieceList){
        piece.paint(g2d);
    }



    }

}
