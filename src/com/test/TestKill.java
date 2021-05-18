package com.test;
import com.chess.*;

/**
 * @author Andrew Effendi
 */
public class TestKill {
    public static void createSurround(Piece pc, boolean isWhite){
        Piece test_2_2 = new Piece("pawn", isWhite, pc.gridX - 1, pc.gridY - 1, Main.pcs);
        Piece test_2_3 = new Piece("pawn", isWhite, pc.gridX - 1, pc.gridY, Main.pcs);
        Piece test_2_4 = new Piece("pawn", isWhite, pc.gridX - 1, pc.gridY + 1, Main.pcs);
        Piece test_2_5 = new Piece("pawn", isWhite, pc.gridX, pc.gridY - 1, Main.pcs);
        Piece test_2_6 = new Piece("pawn", isWhite, pc.gridX, pc.gridY + 1, Main.pcs);
        Piece test_2_7 = new Piece("pawn", isWhite, pc.gridX + 1, pc.gridY - 1, Main.pcs);
        Piece test_2_8 = new Piece("pawn", isWhite, pc.gridX + 1, pc.gridY, Main.pcs);
        Piece test_2_9 = new Piece("pawn", isWhite, pc.gridX + 1, pc.gridY + 1, Main.pcs);
    }
    public static boolean teamKill(Piece pc) {
        int pixel = 64;
        createSurround(pc,false);
        pc.move(pc.gridX, pc.gridY+1, 2);
        if(pc != Main.getPiece(pc.gridX*pixel, pc.gridY*pixel)) {
            System.out.println("Test backward fail (" + pc +" shouldn't move)");
            return true;
        }

        pc.move(pc.gridX, pc.gridY-1, 2);
        if(pc != Main.getPiece(pc.gridX*pixel, pc.gridY*pixel)) {
            System.out.println("Test forward fail (" + pc + " shouldn't move)");
            return true;
        }

        pc.move(pc.gridX-1, pc.gridY, 2);
        if(pc != Main.getPiece(pc.gridX*pixel, pc.gridY*pixel)) {
            System.out.println("Test left fail (" + pc + " shouldn't move)");
            return true;
        }

        pc.move(pc.gridX+1, pc.gridY, 2);
        if(pc != Main.getPiece(pc.gridX*pixel, pc.gridY*pixel)) {
            System.out.println("Test right fail (" + pc + " shouldn't move)");
            return true;
        }

        pc.move(pc.gridX+1, pc.gridY+1, 2);
        if(pc != Main.getPiece(pc.gridX*pixel, pc.gridY*pixel)) {
            System.out.println("Test diagonal backward right fail (" + pc + " shouldn't move)");
            return true;
        }

        pc.move(pc.gridX-1, pc.gridY+1, 2);
        if(pc != Main.getPiece(pc.gridX*pixel, pc.gridY*pixel)) {
            System.out.println("Test diagonal backward left fail (" + pc + " shouldn't move)");
            return true;
        }

        pc.move(pc.gridX-1, pc.gridY-1, 2);
        if(pc != Main.getPiece(pc.gridX*pixel, pc.gridY*pixel)) {
            System.out.println("Test diagonal forward left fail (" + pc + " shouldn't move)");
            return true;
        }

        pc.move(pc.gridX+1, pc.gridY-1, 2);
        if(pc != Main.getPiece(pc.gridX*pixel, pc.gridY*pixel)) {
            System.out.println("Test diagonal forward right fail (" + pc + " shouldn't move)");
            return true;
        }
        return false;
    }
    public static boolean queenKingTest(String pcName){
        int pixel = 64;
        System.out.println("Testing " + pcName + " movement and enemy kill...");
        Piece test_3 = new Piece(pcName, false, 2, 1, Main.pcs);
        createSurround(test_3, true);

        test_3.move(3, 2, 2);
        if(test_3 != Main.getPiece(3*pixel, 2*pixel)) {
            System.out.println("Test diagonal backward right fail (bishop should have killed enemy at (3,2))");
            return false;
        }

        test_3.move(2, 1, 2);
        test_3.move(1, 0, 2);
        if(test_3 != Main.getPiece(pixel, 0)) {
            System.out.println("Test diagonal forward left fail (bishop should have killed enemy at (1,0))");
            return false;
        }

        test_3.move(2, 1, 2);
        test_3.move(3, 0, 2);
        if(test_3 != Main.getPiece(3*pixel, 0)) {
            System.out.println("Test diagonal forward right fail (bishop should have killed enemy at (3,0))");
            return false;
        }

        test_3.move(2, 1, 2);
        test_3.move(1, 2, 2);
        if(test_3 != Main.getPiece(pixel, 2*pixel)) {
            System.out.println("Test diagonal backward left fail (bishop should have killed enemy at (1,2))");
            return false;
        }

        test_3.move(2, 1, 2);
        test_3.move(2, 2, 2);
        if(test_3 != Main.getPiece(2*pixel, 2*pixel)) {
            System.out.println("Test backward fail (bishop should have killed enemy at (2,2))");
            return false;
        }

        test_3.move(2, 1, 2);
        test_3.move(2, 0, 2);
        if(test_3 != Main.getPiece(2*pixel, 0)) {
            System.out.println("Test forward fail (bishop should have killed enemy at (2,0))");
            return false;
        }

        test_3.move(2, 1, 2);
        test_3.move(3, 1, 2);
        if(test_3 != Main.getPiece(3*pixel, pixel)) {
            System.out.println("Test right fail (bishop should have killed enemy at (3,1))");
            return false;
        }

        test_3.move(2, 1, 2);
        test_3.move(1, 1, 2);
        if(test_3 != Main.getPiece(pixel, pixel)) {
            System.out.println("Test left fail (bishop should have killed enemy at (1,1))");
            return false;
        }
        System.out.println("All valid enemy kill test passed\n");

        //////////////////////////////////////////////
        System.out.println("Testing team kill...");
        Piece test_2 = new Piece(pcName, false, 5, 1, Main.pcs);
        if(teamKill(test_2)) return false;
        System.out.println("All team kill test passed\n");
        return true;
    }
}
