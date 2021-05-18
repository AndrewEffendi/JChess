package com.test;
import com.chess.*;

/**
 * @author Andrew Effendi
 */
public class TestPawn {
    public static void main(String[] args) {
        int pixel = 64;
        System.out.println("Pawn Test Started...\n");
        System.out.println("testing pawn movement");
        Piece test_2 = new Piece("pawn", false, 5, 1, Main.pcs);

        test_2.move(5, 0, 2);
        if(test_2 != Main.getPiece(5*pixel, pixel)) {
            System.out.println("Test backward fail (pawn shouldn't move)");
            return;
        }
        test_2.move(6, 1, 2);
        if(test_2 != Main.getPiece(5*pixel, pixel)) {
            System.out.println("Test left fail (pawn shouldn't move)");
            return;
        }
        test_2.move(4, 1, 2);
        if(test_2 != Main.getPiece(5*pixel, pixel)) {
            System.out.println("Test right fail (pawn shouldn't move)");
            return;
        }
        test_2.move(6, 2, 2);
        if(test_2 != Main.getPiece(5*pixel, pixel)) {
            System.out.println("Test diagonal forward left fail (pawn shouldn't move)");
            return;
        }
        test_2.move(4, 2, 2);
        if(test_2 != Main.getPiece(5*pixel, pixel)) {
            System.out.println("Test diagonal forward right fail (pawn shouldn't move)");
            return;
        }
        test_2.move(6, 0, 2);
        if(test_2 != Main.getPiece(5*pixel, pixel)) {
            System.out.println("Test diagonal backward left fail (pawn shouldn't move)");
            return;
        }
        test_2.move(4, 0, 2);
        if(test_2 != Main.getPiece(5*pixel, pixel)) {
            System.out.println("Test diagonal backward right fail (pawn shouldn't move)");
            return;
        }
        test_2.move(5, 3, 2);
        if(test_2 != Main.getPiece(5*pixel, 3*pixel)) {
            System.out.println("Test forward valid 2 step jump fail (pawn should have moved to (5,3))");
            return;
        }
        test_2.move(5, 5, 2);
        if(test_2 != Main.getPiece(5*pixel, 3*pixel)) {
            System.out.println("Test forward invalid 2 step jump fail (pawn shouldn't move)");
            return;
        }
        test_2.move(5, 4, 2);
        if(test_2 != Main.getPiece(5*pixel, 4*pixel)) {
            System.out.println("Test forward 1 step fail (pawn should have moved to (5,4))");
            return;
        }

        System.out.println("All movement passed\n");

        //////////////////////////////////////////////
        System.out.println("Testing team kill...");
        if(TestKill.teamKill(test_2)) return;
        test_2.move(5, 8, 2);
        if(test_2 != Main.getPiece(5*pixel, 4*pixel)) {
            System.out.println("Test forward invalid 2 step jump fail (pawn shouldn't move)");
            return;
        }
        System.out.println("All team kill test passed");

        //////////////////////////////////////////////
        System.out.println("\nTesting invalid enemy kill...");
        Piece test_3 = new Piece("pawn", false, 2, 1, Main.pcs);
        TestKill.createSurround(test_3, true);
        Piece test_3_1_9 = new Piece("pawn", true, 2, 3, Main.pcs);

        test_3.move(2, 2, 2);
        if(test_3 != Main.getPiece(2*pixel, pixel)) {
            System.out.println("Test forward 1 step fail (pawn shouldn't move)");
            return;
        }
        test_3.move(2, 0, 2);
        if(test_3 != Main.getPiece(2*pixel, pixel)) {
            System.out.println("Test backward fail (pawn shouldn't move)");
            return;
        }
        test_3.move(3, 1, 2);
        if(test_3 != Main.getPiece(2*pixel, pixel)) {
            System.out.println("Test left fail (pawn shouldn't move)");
            return;
        }
        test_3.move(1, 1, 2);
        if(test_3 != Main.getPiece(2*pixel, pixel)) {
            System.out.println("Test right fail (pawn shouldn't move)");
            return;
        }
        test_3.move(3, 0, 2);
        if(test_3 != Main.getPiece(2*pixel, pixel)) {
            System.out.println("Test diagonal backward left fail (pawn shouldn't move)");
            return;
        }
        test_3.move(1, 0, 2);
        if(test_3 != Main.getPiece(2*pixel, pixel)) {
            System.out.println("Test diagonal backward right fail (pawn shouldn't move)");
            return;
        }
        test_3.move(2, 3, 2);
        if(test_3 != Main.getPiece(2*pixel, pixel)) {
            System.out.println("Test forward valid 2 step jump fail (pawn shouldn't move)");
            return;
        }

        System.out.println("All invalid enemy kill test passed\n");

        //////////////////////////////////////////////
        System.out.println("Testing valid enemy kill...");
        test_3.move(3, 2, 2);
        if(test_3 != Main.getPiece(3*pixel, 2*pixel)) {
            System.out.println("Test diagonal forward left fail (pawn should have moved to (3,2))");
            return;
        }
        test_3.move(2, 3, 2);
        if(test_3 != Main.getPiece(2*pixel, 3*pixel)) {
            System.out.println("Test diagonal forward right fail (pawn should have moved to (2,3))");
            return;
        }
        System.out.println("All valid enemy kill test passed\n");

        System.out.println("All test passed\n");

    }

}