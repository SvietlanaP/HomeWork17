package DOMParse;

import Main.IParser;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DOMParsingSonet implements IParser {
    @Override
    public void parse(String pathToFolder) {

        File folder = new File(pathToFolder);

        if(folder.isDirectory()) {
            // получаем только xml файл согласно условию
            File[] files = folder.listFiles((dir, name) -> name.endsWith("xml"));

            // проверка на то, что после фильтрации есть файлы, подходящие под наше условие
            if (files.length == 0) {
                System.out.println("Нет подходящих файлов");
                return;
            }

            try{
                DocumentBuilderFactory docFactoty = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = docFactoty.newDocumentBuilder();
                Document document = builder.parse(new File(String.valueOf(files[0])));

                document.getDocumentElement().normalize();

                //получение имени файла
                String title = document.getElementsByTagName("title").item(0).getTextContent();
                String firstName = document.getElementsByTagName("firstName").item(0).getTextContent();
                String lastName = document.getElementsByTagName("lastName").item(0).getTextContent();
                String nameOfFile = firstName + "_" + lastName + "_" + title;
                NodeList lineList = document.getElementsByTagName("lines");

                //получение данных line
                for (int i = 0; i < lineList.getLength(); i++) {
                    Node node = lineList.item(i);
                    String linesAll = node.getTextContent();
                    //System.out.println(linesAll);

                    //записываем стих в файл
                    try (BufferedWriter docNumberResult = new BufferedWriter(new FileWriter(nameOfFile + ".txt"))){
                        docNumberResult.write(linesAll);
                    } catch (IOException e){
                        e.getMessage();
                    }
                }
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Невалидный путь");
        }
    }
}
