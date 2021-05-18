package com.test;
import com.chess.*;

/**
 * @author Andrew Effendi
 */
public class TestQueen {
    public static void main(String[] args) {
        int pixel = 64;
        System.out.println("Queen Test Started\n");
        if(!TestKill.queenKingTest("queen"))
            return;

        //////////////////////////////////////////////
        System.out.println("Testing multiple tiles movement...");

        Piece test_4 = new Piece("queen", false, 3, 5, Main.pcs);
        test_4.move(0, 5, 2);
        if(test_4 != Main.getPiece(0, 5*pixel)) {
            System.out.println("Test multiple tiles left fail (queen should have moved to (0,5))");
            return;
        }
        test_4.move(7, 5, 2);
        if(test_4 != Main.getPiece(7*pixel, 5*pixel)) {
            System.out.println("Test multiple tiles right fail (queen should have moved to (7,5))");
            return;
        }

        test_4.move(7, 0, 2);
        if(test_4 != Main.getPiece(7*pixel, 0)) {
            System.out.println("Test multiple tiles forward fail (queen should have moved to (7,0))");
            return;
        }

        test_4.move(7, 7, 2);
        if(test_4 != Main.getPiece(7*pixel, 7*pixel)) {
            System.out.println("Test multiple tiles backward fail (queen should have moved to (7,7))");
            return;
        }

        test_4.move(0, 0, 2);
        if(test_4 != Main.getPiece(7*pixel, 7*pixel)) {
            System.out.println("Test multiple tiles jump over other pieces fail (queen shouldn't move)");
            return;
        }

        test_4.move(2, 2, 2);
        if(test_4 != Main.getPiece(2*pixel, 2*pixel)) {
            System.out.println("Test multiple tiles diagonal forward left fail (queen should have moved to (2,2))");
            return;
        }

        test_4.move(0, 4, 2);
        if(test_4 != Main.getPiece(0, 4*pixel)) {
            System.out.println("Test multiple tiles diagonal backward left fail (queen should have moved to (0,4))");
            return;
        }

        test_4.move(3, 7, 2);
        if(test_4 != Main.getPiece(3*pixel, 7*pixel)) {
            System.out.println("Test multiple tiles diagonal backward right fail (queen should have moved to (3,7))");
            return;
        }

        test_4.move(6, 4, 2);
        if(test_4 != Main.getPiece(6*pixel, 4*pixel)) {
            System.out.println("Test multiple tiles diagonal forward right fail (queen should have moved to (6,4))");
            return;
        }

        System.out.println("All multiple tiles movement test passed\n");

        System.out.println("All test passed\n");
    }
}