package main;

import pieces.Piece;

public class Move {

int oldCol; // kolumna, z której ruszono figurą
int oldRow; // rząd, z którego ruszono figurą
int newCol; // kolumna, na którą przesunięto figurę
int newRow; // rząd, na który przesunięto figurę

    Piece piece; // przesuwana figura 
    Piece capture; //ewentualna zbita figura

    //konstruktor klasy move 
    public Move(Board board, Piece piece, int newCol, int newRow){

        this.oldCol = piece.col;
        this.oldRow = piece.row;
        this.newCol = newCol;
        this.newRow = newRow;

        this.piece = piece;
        
        //zapisnaie zbita figura, jezeli na nowych kordach jest figura przeciwnika
        this.capture = board.getPiece(newCol, newRow);


    }

}
