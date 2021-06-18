package SAXParse;

import Main.IParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.ArrayList;

public class SAXParsingSonet implements IParser {

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

            ArrayList<String> partOfFile = new ArrayList<>();

                try {
                    File inputFile = new File(String.valueOf(files[0]));
                    SAXParserFactory factory = SAXParserFactory.newInstance();
                    SAXParser saxParser = factory.newSAXParser();

                    SonetHandler sonetHandler = new SonetHandler();
                    saxParser.parse(inputFile, sonetHandler);

                    partOfFile = sonetHandler.getName();

                    //имя файла
                    String nameOfFile = partOfFile.get(1) + "_" + partOfFile.get(0) + "_" + partOfFile.get(2);

                    //записываем стих в файл
                    try (BufferedWriter docNumberResult = new BufferedWriter(new FileWriter(nameOfFile + ".txt"))) {
                        for (int i = 3; i < partOfFile.size(); i++) {
                            docNumberResult.write(partOfFile.get(i));
                        }
                    } catch (IOException e) {
                        e.getMessage();
                    }


                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        } else {
            System.out.println("Невалидный путь");
        }
    }
}
