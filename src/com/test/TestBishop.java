package com.test;
import com.chess.*;

/**
 * @author Andrew Effendi
 */
public class TestBishop {
    public static void main(String[] args) {
        int pixel = 64;
        System.out.println("Bishop Test Started\n");
        System.out.println("Testing bishop movement...");
        Piece test_2 = new Piece("bishop", false, 5, 1, Main.pcs);

        test_2.move(7, 3, 2);
        if(test_2 != Main.getPiece(7*pixel, 3*pixel)) {
            System.out.println("Test diagonal backward right fail (bishop should have moved to (7,3))" );
            return;
        }
        test_2.move(5, 1, 2);
        if(test_2 != Main.getPiece(5*pixel, pixel)) {
            System.out.println("Test diagonal forward left fail (bishop should have moved to (5,1))");
            return;
        }

        test_2.move(2, 4, 2);
        if(test_2 != Main.getPiece(2*pixel, 4*pixel)) {
            System.out.println("Test diagonal backward left fail (bishop should have moved to (2,4))");
            return;
        }

        test_2.move(5, 1, 2);
        if(test_2 != Main.getPiece(5*pixel, pixel)) {
            System.out.println("Test diagonal forward right fail (bishop should have moved to (5,1))");
            return;
        }
        test_2.move(5, 2, 2);
        if(test_2 != Main.getPiece(5*pixel, pixel)) {
            System.out.println("Test backward fail (bishop shouldn't move)");
            return;
        }

        test_2.move(5, 0, 2);
        if(test_2 != Main.getPiece(5*pixel, pixel)) {
            System.out.println("Test forward fail (bishop shouldn't move)");
            return;
        }

        test_2.move(4, 1, 2);
        if(test_2 != Main.getPiece(5*pixel, pixel)) {
            System.out.println("Test left fail (bishop shouldn't move)");
            return;
        }

        test_2.move(6, 1, 2);
        if(test_2 != Main.getPiece(5*pixel, pixel)) {
            System.out.println("Test right fail (bishop shouldn't move)");
            return;
        }

        System.out.println("All movement test passed\n");

        //////////////////////////////////////////////
        System.out.println("Testing team kill...");
        if(TestKill.teamKill(test_2)) return;
        System.out.println("All team kill test passed\n");

        //////////////////////////////////////////////
        System.out.println("Testing invalid enemy kill...");
        Piece test_3 = new Piece("bishop", false, 2, 1, Main.pcs);
        TestKill.createSurround(test_3, true);

        test_3.move(2, 2, 2);
        if(test_3 != Main.getPiece(2*pixel, pixel)) {
            System.out.println("Test backward fail (bishop shouldn't move)");
            return;
        }

        test_3.move(2, 0, 2);
        if(test_3 != Main.getPiece(2*pixel, pixel)) {
            System.out.println("Test forward fail (bishop shouldn't move)");
            return;
        }

        test_3.move(3, 1, 2);
        if(test_3 != Main.getPiece(2*pixel, pixel)) {
            System.out.println("Test left fail (bishop shouldn't move)");
            return;
        }

        test_3.move(1, 1, 2);
        if(test_3 != Main.getPiece(2*pixel, pixel)) {
            System.out.println("Test right fail (bishop shouldn't move)");
            return;
        }
        System.out.println("All invalid enemy kill test passed\n");

        //////////////////////////////////////////////
        System.out.println("Testing valid enemy kill...");

        test_3.move(3, 2, 2);
        if(test_3 != Main.getPiece(3*pixel, 2*pixel)) {
            System.out.println("Test diagonal backward right fail (bishop should have killed enemy at (3,2))");
            return;
        }

        test_3.move(1, 0, 2);
        if(test_3 != Main.getPiece(pixel, 0)) {
            System.out.println("Test diagonal forward left fail (bishop should have killed enemy at (1,0))");
            return;
        }

        test_3.move(2, 1, 2);
        test_3.move(3, 0, 2);
        if(test_3 != Main.getPiece(3*pixel, 0)) {
            System.out.println("Test diagonal forward right fail (bishop should have killed enemy at (3,0))");
            return;
        }

        test_3.move(1, 2, 2);
        if(test_3 != Main.getPiece(pixel, 2*pixel)) {
            System.out.println("Test diagonal backward left fail (bishop should have killed enemy at (1,2))");
            return;
        }
        System.out.println("All valid enemy kill test passed\n");

        System.out.println("All test passed\n");
    }
}
