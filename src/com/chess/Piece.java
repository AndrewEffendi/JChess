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

    public void kill(){
        pcs.remove(this);
    }

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

    public boolean doNothing(){
        x=this.gridX*64;
        y=this.gridY*64;
        return false;
    }

    public boolean pawnMove(int gridX,int gridY){
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
    public boolean bishopMove(int gridX,int gridY){
        //fail move not diagonal
        if(!(this.gridX - gridX == this.gridY - gridY || this.gridX - gridX == - (this.gridY - gridY)) || gridY == this.gridY){
            return doNothing();
        }
        //fail move have blockade
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
            while (i < h){
                if(Main.getPiece(i*64, j*64)!=null) return doNothing();
                i++;
                j++;
            }
        }
        else{
            i--;
            while (i > h){
                if(Main.getPiece(i*64, j*64)!=null) return doNothing();
                i--;
                j++;
            }
        }
        return moveToKill(gridX, gridY);
    }

    public boolean rookMove(int gridX,int gridY){
        //fail move not straight
        if(!(this.gridX == gridX || this.gridY == gridY )) return doNothing();
        //fail move have blockade
        int k ,o;
        if(gridX == this.gridX){
            if(gridY > this.gridY){
                k = this.gridY + 1;
                o = gridY;
            }
            else{
                k = gridY + 1;
                o = this.gridY;
            }
            while(k < o){
                if(Main.getPiece(gridX*64, k*64)!=null) return doNothing();
                k++;
            }
        }
        else{
            if(gridX > this.gridX){
                k = this.gridX + 1;
                o = gridX;
            }
            else{
                k = gridX + 1;
                o = this.gridX;
            }
            while(k < o){
                if(Main.getPiece(k*64, gridY*64)!=null) return doNothing();
                k++;
            }
        }
        return moveToKill(gridX, gridY);
    }
    public boolean queenMove(int gridX,int gridY){
        //fail move not diagonal nor straight
        if(!(this.gridX - gridX == this.gridY - gridY || this.gridX - gridX == - (this.gridY - gridY) || this.gridX == gridX || this.gridY == gridY)){
            return doNothing();
        }
        //for straight
        if(this.gridX == gridX || this.gridY == gridY){
            return rookMove(gridX, gridY);
        }
        //for diagonal blockade
        return bishopMove(gridX, gridY);
    }

    public boolean kingMove(int gridX,int gridY){
        if(this.gridX - gridX > 1 || this.gridX - gridX < -1 || this.gridY - gridY > 1 || this.gridY - gridY < -1 ){
            return doNothing();
        }
        return moveToKill(gridX, gridY);
    }
    public boolean knightMove(int gridX,int gridY){
        if(!((Math.abs(this.gridX - gridX) == 2 && Math.abs(this.gridY - gridY)==1) || (Math.abs(this.gridX - gridX) == 1 && Math.abs(this.gridY - gridY)==2))){
            return doNothing();
        }
        return moveToKill(gridX, gridY);
    }
}
