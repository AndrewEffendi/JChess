package com.chess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * @author Andrew Effendi
 */
public class Main {
    public static LinkedList<Piece> pcs = new LinkedList<>();
    public static Piece selectedPiece = null;
    public static void main(String[] args) throws IOException {
        /*
         * this reads the chess pieces image file,
         * to load the image change the filePath to the file pate of the image.
         */
        String filePath = "art/chess.png";
        BufferedImage all=ImageIO.read(new File(filePath));
        Image images[]= new Image[12];
        int index=0;
        int pixel = 64;
        int imageWidth = 1200;
        int imageHeight = 400;
        int pcsWidth = 200;
        int pcsHeight =200;
        for(int y = 0; y < imageHeight; y += pcsHeight){
            for(int x = 0 ; x < imageWidth; x += pcsWidth){
                images[index]=all.getSubimage(x, y, pcsWidth, pcsHeight).getScaledInstance(pixel, pixel, BufferedImage.SCALE_SMOOTH);
                index++;
            }
        }
        //initialize board with white pieces
        Piece white_King = new Piece("king", true, 4, 7, pcs);
        Piece white_Queen = new Piece("queen", true, 3, 7, pcs);
        Piece white_Pawn_1 = new Piece("pawn", true, 1, 6, pcs);
        Piece white_Pawn_2 = new Piece("pawn", true, 2, 6, pcs);
        Piece white_Pawn_3 = new Piece("pawn", true, 3, 6, pcs);
        Piece white_Pawn_4 = new Piece("pawn", true, 4, 6, pcs);
        Piece white_Pawn_5 = new Piece("pawn", true, 5, 6, pcs);
        Piece white_Pawn_6 = new Piece("pawn", true, 6, 6, pcs);
        Piece white_Pawn_7 = new Piece("pawn", true, 7, 6, pcs);
        Piece white_Pawn_8 = new Piece("pawn", true, 0, 6, pcs);
        Piece white_Rook_L = new Piece("rook", true, 0, 7, pcs);
        Piece white_Rook_R = new Piece("rook", true, 7, 7, pcs);
        Piece white_Knight_L = new Piece("knight", true, 1, 7, pcs);
        Piece white_Knight_R = new Piece("knight", true, 6, 7, pcs);
        Piece white_Bishop_L = new Piece("bishop", true, 2, 7, pcs);
        Piece white_Bishop_R = new Piece("bishop", true, 5, 7, pcs);

        //initialize board with black pieces
        Piece black_king = new Piece("king", false, 4, 0, pcs);
        Piece black_queen = new Piece("queen", false, 3, 0, pcs);
        Piece black_Pawn_1 = new Piece("pawn", false, 1, 1, pcs);
        Piece black_Pawn_2 = new Piece("pawn", false, 2, 1, pcs);
        Piece black_Pawn_3 = new Piece("pawn", false, 3, 1, pcs);
        Piece black_Pawn_4 = new Piece("pawn", false, 4, 1, pcs);
        Piece black_Pawn_5 = new Piece("pawn", false, 5, 1, pcs);
        Piece black_Pawn_6 = new Piece("pawn", false, 6, 1, pcs);
        Piece black_Pawn_7 = new Piece("pawn", false, 7, 1, pcs);
        Piece black_Pawn_8 = new Piece("pawn", false, 0, 1, pcs);
        Piece black_rook_L = new Piece("rook", false, 0, 0, pcs);
        Piece black_rook_R = new Piece("rook", false, 7, 0, pcs);
        Piece black_knight_L = new Piece("knight", false, 1, 0, pcs);
        Piece black_knight_R = new Piece("knight", false, 6, 0, pcs);
        Piece black_bishop_L = new Piece("bishop", false, 2, 0, pcs);
        Piece black_bishop_R = new Piece("bishop", false, 5, 0, pcs);


        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 512, 512);
        frame.setUndecorated(true);
        //////
        JLabel label = new JLabel();
        label.setBackground(Color.red);
        label.setBounds(20, 20, 10, 10);
        label.setOpaque(true);
        //////////

        JPanel pn=new JPanel(){
            @Override
            public void paint(Graphics g) {
                boolean white=true;
                for(int y= 0;y<8;y++){
                    for(int x= 0;x<8;x++){
                        if(white){
                            g.setColor(new Color(232,235, 239));
                        }else{
                            g.setColor(new Color(125, 135, 150));

                        }
                        g.fillRect(x*pixel, y*pixel, pixel, pixel);
                        white=!white;
                    }
                    white=!white;
                }
                for(Piece i: pcs){
                    int index = -1;
                    if(i.name.equals("king")){
                        index=0;
                    }
                    if(i.name.equals("queen")){
                        index=1;
                    }
                    if(i.name.equals("bishop")){
                        index=2;
                    }
                    if(i.name.equals("knight")){
                        index=3;
                    }
                    if(i.name.equals("rook")){
                        index=4;
                    }
                    if(i.name.equals("pawn")){
                        index=5;
                    }
                    if(!i.is_w){
                        index+=6;
                    }
                    g.drawImage(images[index], i.x, i.y, this);
                }
            }

        };
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(10, 10, 512, 512);

        layeredPane.add(label);
        frame.add(pn);

        frame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(selectedPiece!=null){
                    selectedPiece.x=e.getX()-32;
                    selectedPiece.y=e.getY()-32;
                    frame.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // System.out.println((getPiece(e.getX(), e.getY()).is_w?"white ":"black ")+getPiece(e.getX(), e.getY()).name);
                selectedPiece=getPiece(e.getX(), e.getY());
            }

            int current_Player = 1; // player 1 is white and player 2 is black
            boolean moved;
            boolean targetIsKing;
            @Override
            public void mouseReleased(MouseEvent e) {
                if(getPiece(e.getX(), e.getY())!=null){
                    //System.out.println(getPiece(e.getX(), e.getY()).name);
                    if(getPiece(e.getX(), e.getY()).name.equals("king")) targetIsKing = true;
                }
                moved = selectedPiece.move(e.getX()/pixel, e.getY()/pixel,current_Player);
                if(moved && targetIsKing){
                    System.out.print("Player " + current_Player + " win ");
                    if(current_Player == 1) System.out.println("(White)");
                    else System.out.println("(Black)");
                    System.exit(0);
                }
                if(!moved) System.out.println("Invalid move");
                if(current_Player == 1 && moved) current_Player = 2;
                else if (current_Player == 2 && moved) current_Player = 1;
                if(current_Player == 1) System.out.println("Player 1 turn (White)");
                else System.out.println("Player 2 turn (Black)");
                frame.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        System.out.println("Player 1 turn (White)");
    }
    public static Piece getPiece(int x,int y){
        int pixel = 64;
        int gridX=x/pixel;
        int gridY=y/pixel;
        for(Piece i: pcs){
            if(i.gridX == gridX && i.gridY == gridY){
                return i;
            }
        }
        return null;
    }
}
