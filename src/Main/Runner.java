package Main;

import DOMParse.DOMParsingSonet;
import SAXParse.SAXParsingSonet;

import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Укажите путь к папке с файлами.");
        String fileDir = in.next();

        Scanner inNum = new Scanner(System.in);
        System.out.println("Укажите метод, которым парсировать документ:" +
                "1 - DOM" +
                "2 - SAX.");
        int numParse = inNum.nextInt();

        if (numParse == 1){
            DOMParsingSonet parser = new DOMParsingSonet();
            parser.parse(fileDir);
        } else if (numParse == 2){
            SAXParsingSonet parser = new SAXParsingSonet();
            parser.parse(fileDir);
        }else {
            System.out.println("Для этой цифры метод не пределен.");
        }

        in.close();
        inNum.close();
    }

}
