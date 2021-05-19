package com.chess;
import java.util.LinkedList;

/**
 * @author Andrew Effendi
 */
public class Piece {
    //int current_Player = 1; // player 1 is white and player 2 is black
    public int gridX;
    public int gridY;

    int x;
    int y;
    boolean is_w;
    LinkedList<Piece> pcs;
    String name;

    // this object is used to store all the data of the related chess piece
    public Piece(String pcs_name, boolean is_w, int gridX, int gridY, LinkedList<Piece> pcs) {
        name = pcs_name;
        this.is_w = is_w;
        this.gridX = gridX;
        this.gridY = gridY;
        x=gridX*64;
        y=gridY*64;
        this.pcs=pcs;
        pcs.add(this);
    }

    //this method is used to determine the movement of each pieces
    public boolean move(int gridX,int gridY,int current_Player){
        //System.out.println(ChessGame.getPiece(this.gridX*64, this.gridY*64).name);
        //check if its player's turn
        if(current_Player == 1 && !Main.getPiece(this.gridX*64, this.gridY*64).is_w) return doNothing();
        if(current_Player == 2 && Main.getPiece(this.gridX*64, this.gridY*64).is_w) return doNothing();

        //set boundaries
        if(gridX < 0 || gridX > 7 || gridY < 0 || gridY > 7) return doNothing();

        //movement
        //pawn
        if(Main.getPiece(this.gridX*64, this.gridY*64).name.equals("pawn")) return pawnMove(gridX, gridY);
        //bishop
        if(Main.getPiece(this.gridX*64, this.gridY*64).name.equals("bishop")) return bishopMove(gridX, gridY);
        //rook
        if(Main.getPiece(this.gridX*64, this.gridY*64).name.equals("rook")) return rookMove(gridX, gridY);
        //queen
        if(Main.getPiece(this.gridX*64, this.gridY*64).name.equals("queen")) return queenMove(gridX, gridY);
        //king
        if(Main.getPiece(this.gridX*64, this.gridY*64).name.equals("king")) return kingMove(gridX, gridY);
        //knight
        if(Main.getPiece(this.gridX*64, this.gridY*64).name.equals("knight")) return knightMove(gridX, gridY);
        return false;
    }

    //this method is used to kill the chess pieces i.e. remove it from the board
    public void kill(){
        pcs.remove(this);
    }

    //this method is used to move the piece to the new grid and kill the opponent on that grid if there is any
    public boolean moveToKill(int gridX,int gridY){
        if(Main.getPiece(gridX*64, gridY*64)!=null){
            if(Main.getPiece(gridX*64, gridY*64).is_w!=is_w){
                Main.getPiece(gridX*64, gridY*64).kill();
            }else{
                return doNothing();
            }
        }
        this.gridX=gridX;
        this.gridY=gridY;
        x=gridX*64;
        y=gridY*64;
        return true;
    }

    //this method is used for invalid movement and do nothing so the move wouldn't be registered to the board
    public boolean doNothing(){
        x=this.gridX*64;
        y=this.gridY*64;
        return false;
    }

    //this method is used to determine the movement for the pawn
    public boolean pawnMove(int gridX,int gridY){
        //for white pieces
        if(this.is_w){
            //kill
            if(Main.getPiece(gridX*64, gridY*64)!=null && this.gridY - gridY == 1 && (this.gridX - gridX == 1 || this.gridX - gridX == -1 ) && !Main.getPiece(gridX*64, gridY*64).is_w){
                Main.getPiece(gridX*64, gridY*64).kill();
            }
            //fail move
            else if((this.gridY - gridY != 1 && !(this.gridY == 6 && this.gridY - gridY == 2 && Main.getPiece(gridX*64, (gridY+1)*64)==null )) || this.gridX - gridX != 0 || Main.getPiece(gridX*64, gridY*64)!=null ){
                return doNothing();
            }
        }
        //for black pieces
        else{
            //kill
            if(Main.getPiece(gridX*64, gridY*64)!=null && this.gridY - gridY == -1 && (this.gridX - gridX == 1 || this.gridX - gridX == -1 ) && Main.getPiece(gridX*64, gridY*64).is_w){
                Main.getPiece(gridX*64, gridY*64).kill();
            }
            //fail move
            else if((this.gridY - gridY != -1 && !(this.gridY == 1 && this.gridY - gridY == -2 && Main.getPiece(gridX*64, (gridY-1)*64)==null )) || this.gridX - gridX != 0 || Main.getPiece(gridX*64, gridY*64)!=null ){
                return doNothing();
            }

        }
        return moveToKill(gridX, gridY);
    }

    // this is the method that determines the movement for the bishop
    public boolean bishopMove(int gridX,int gridY){
        //fail move not diagonal
        if(!(this.gridX - gridX == this.gridY - gridY || this.gridX - gridX == - (this.gridY - gridY)) || gridY == this.gridY){
            return doNothing();
        }
        //check if it is a valid diagonal movement
        int i, j,h;
        if(gridY > this.gridY) {
            j = this.gridY + 1;
            i = this.gridX;
            h = gridX;
        }
        else {
            j = gridY + 1;
            i = gridX;
            h = this.gridX;
        }
        if((this.gridX - gridX) * (this.gridY - gridY) > 0){
            i++;
            //checks if there is any blockade along the path
            while (i < h){
                if(Main.getPiece(i*64, j*64)!=null) return doNothing();
                i++;
                j++;
            }
        }
        else{
            i--;
            //checks if there is any blockade along the path
            while (i > h){
                if(Main.getPiece(i*64, j*64)!=null) return doNothing();
                i--;
                j++;
            }
        }
        return moveToKill(gridX, gridY);
    }

    //this is the method that determines the movement for the rook
    public boolean rookMove(int gridX,int gridY){
        //fail move not straight
        if(!(this.gridX == gridX || this.gridY == gridY )) return doNothing();
        //fail move have blockade
        int k ,o;
        //for vertical straight movement
        if(gridX == this.gridX){
            if(gridY > this.gridY){
                k = this.gridY + 1;
                o = gridY;
            }
            else{
                k = gridY + 1;
                o = this.gridY;
            }
            //checks if there is any blockades along the path
            while(k < o){
                if(Main.getPiece(gridX*64, k*64)!=null) return doNothing();
                k++;
            }
        }

        //for horizontal straight movement
        else{
            if(gridX > this.gridX){
                k = this.gridX + 1;
                o = gridX;
            }
            else{
                k = gridX + 1;
                o = this.gridX;
            }
            //checks if there is any blockades along the path
            while(k < o){
                if(Main.getPiece(k*64, gridY*64)!=null) return doNothing();
                k++;
            }
        }
        return moveToKill(gridX, gridY);
    }

    // this is the method that determines the movement for the queen
    public boolean queenMove(int gridX,int gridY){
        //fail move not diagonal nor straight
        if(!(this.gridX - gridX == this.gridY - gridY || this.gridX - gridX == - (this.gridY - gridY) || this.gridX == gridX || this.gridY == gridY)){
            return doNothing();
        }
        //checks if it is a valid straight movement
        if(this.gridX == gridX || this.gridY == gridY){
            return rookMove(gridX, gridY);
        }
        //checks if it si a valid diagonal movement
        return bishopMove(gridX, gridY);
    }

    // this is the method that is used to determines the movement for the king
    public boolean kingMove(int gridX,int gridY){
        if(this.gridX - gridX > 1 || this.gridX - gridX < -1 || this.gridY - gridY > 1 || this.gridY - gridY < -1 ){
            return doNothing();
        }
        return moveToKill(gridX, gridY);
    }

    // this is the method that is used to determine the movement for the knight
    public boolean knightMove(int gridX,int gridY){
        if(!((Math.abs(this.gridX - gridX) == 2 && Math.abs(this.gridY - gridY)==1) || (Math.abs(this.gridX - gridX) == 1 && Math.abs(this.gridY - gridY)==2))){
            return doNothing();
        }
        return moveToKill(gridX, gridY);
    }
}
