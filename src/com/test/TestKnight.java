package com.test;
import com.chess.*;

/**
 * @author Andrew Effendi
 */
public class TestKnight {
    public static int testNum = 1;
    public static void main(String[] args) {
        System.out.println("Knight Test Started\n");
        Piece test_2 = new Piece("knight", false, 5, 2, Main.pcs);

        System.out.println("Testing knight movement without blockade...");
        if(moveKnight(test_2)) return;

        System.out.println("Testing knight movement with blockade...");
        TestKill.createSurround(test_2, true);
        if(moveKnight(test_2)) return;

        System.out.println("Testing knight kill enemy...");
        createTarget(true);
        if(moveKnight(test_2)) return;

        System.out.println("Testing knight team kill...");
        createTarget(false);
        if(noMove(test_2, 6, 0, 2)) return;
        if(noMove(test_2, 5, 2, 2)) return;
        if(noMove(test_2, 7, 1, 2)) return;
        if(noMove(test_2, 5, 2, 2)) return;
        if(noMove(test_2, 7, 3, 2)) return;
        if(noMove(test_2, 5, 2, 2)) return;
        if(noMove(test_2, 6, 4, 2)) return;
        if(noMove(test_2, 5, 2, 2)) return;
        System.out.println("All movement test passed\n");

        System.out.println("All test passed\n");

    }
    public static boolean moveKnight(Piece pc){
        int pixel = 64;

        //testing invalid movement
        pc.move(5, 0, 2);
        if(pc != Main.getPiece(5*pixel, 2*pixel)) {
            System.out.println("Test 1 fail (knight shouldn't move)" );
            return true;
        }
        pc.move(7, 2, 2);
        if(pc != Main.getPiece(5*pixel, 2*pixel)) {
            System.out.println("Test 2 fail (knight shouldn't move)" );
            return true;
        }
        pc.move(5, 4, 2);
        if(pc != Main.getPiece(5*pixel, 2*pixel)) {
            System.out.println("Test 3 fail (knight shouldn't move)" );
            return true;
        }
        pc.move(3, 2, 2);
        if(pc != Main.getPiece(5*pixel, 2*pixel)) {
            System.out.println("Test 4 fail (knight shouldn't move)" );
            return true;
        }

        //testing valid movement
        if(movePcs(pc, 6, 0, 2)) return true;
        if(movePcs(pc, 5, 2, 2)) return true;
        if(movePcs(pc, 7, 1, 2)) return true;
        if(movePcs(pc, 5, 2, 2)) return true;
        if(movePcs(pc, 7, 3, 2)) return true;
        if(movePcs(pc, 5, 2, 2)) return true;
        if(movePcs(pc, 6, 4, 2)) return true;
        if(movePcs(pc, 5, 2, 2)) return true;

        System.out.println("All movement test passed\n");
        return false;
    }
    public static boolean movePcs(Piece pc, int gridX, int gridY, int current_Player ){
        int pixel = 64;
        pc.move(gridX, gridY, current_Player);
        if(pc != Main.getPiece(gridX*pixel, gridY*pixel)) {
            System.out.println("Test "+testNum+" fail (knight should have moved to ("+gridX+","+gridY+"))" );
            testNum++;
            return true;
        }
        return false;
    }
    public static boolean noMove(Piece pc, int gridX, int gridY, int current_Player ){
        int pixel = 64;
        int originX =5;
        int originY = 2;
        pc.move(gridX, gridY, current_Player);
        if(pc != Main.getPiece(originX*pixel, originY*pixel)) {
            System.out.println("Test "+testNum+" fail (knight shouldn't move)");
            return true;
        }
        testNum++;
        return false;
    }
    public static void createTarget( boolean isWhite){
        Piece test_2_2 = new Piece("pawn", isWhite, 7, 1, Main.pcs);
        Piece test_2_3 = new Piece("pawn", isWhite, 6, 0, Main.pcs);
        Piece test_2_4 = new Piece("pawn", isWhite, 7, 3, Main.pcs);
        Piece test_2_5 = new Piece("pawn", isWhite, 6, 4, Main.pcs);
        Piece test_2_6 = new Piece("pawn", isWhite, 4, 4, Main.pcs);
        Piece test_2_7 = new Piece("pawn", isWhite, 3, 3, Main.pcs);
        Piece test_2_8 = new Piece("pawn", isWhite, 3, 1, Main.pcs);
        Piece test_2_9 = new Piece("pawn", isWhite, 4, 0, Main.pcs);
    }
}