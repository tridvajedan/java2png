import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //encrypt();
        decrypt();
    }

    public static void decrypt() {
        System.out.println("Decrypting!");
        File file = new File("C://Users/Korisnik/IdeaProjects/java2png/picture3.png");
        ArrayList<Character> chars = new ArrayList<>();
        BufferedImage img = null;
        try {
            img = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                Color color = new Color(img.getRGB(j,i));
                int blue = color.getBlue();
                int green = color.getGreen();
                int red = color.getRed();
                if (color.getBlue() != 0) {
                    System.out.println(red +" || " +blue+" || " + green);
                    blue = blue - 101;
                    chars.add((char) blue);
                }
            }
        }
        String text = "";
        for (int i = 0; i < chars.size(); i++)
        {
            text += chars.get(i);
        }
        System.out.println(chars.size());
        System.out.println(text);
    }

    public static void encrypt()
    {
        Random r = new Random();
        int width = 640;
        int height = 320;
        ArrayList<Character> chars = new ArrayList<>();
        BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        File textFile = new File("C://Users/Korisnik/IdeaProjects/java2png/test.txt");
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(textFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(fileScanner.hasNextLine())
        {
            String text = fileScanner.nextLine();
            for(int i = 0; i < text.length();i++)
            {
                chars.add(text.charAt(i));
            }
        }
        System.out.println(chars.size());
        int counter = 0;
        for(int i =0; i < height;i++)
        {
            for(int j = 0; j < width; j++)
            {
                if(counter + 1 > chars.size())
                {
                    img.setRGB(j,i, 000);
                }
                else {
                    img.setRGB(j, i, (int) chars.get(counter) + 101);
                    System.out.println("Triggered");
                }
                counter++;
            }
        }
        System.out.println("Looped!");
        try {
            ImageIO.write(img,"png",new File("picture3.png"));
            System.out.println("Written!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}