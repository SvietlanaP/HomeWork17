package SAXParse;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class SonetHandler extends DefaultHandler {
    ArrayList<String> partOfFile = new ArrayList<>();

    boolean isLine = false;
    boolean isFirstName = false;
    boolean isLastName = false;
    boolean isTitle = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        if (qName.equals("line")){
            isLine = true;
        } else if(qName.equals("firstName")){
            isFirstName = true;
        }else if(qName.equals("lastName")){
            isLastName = true;
        }else if(qName.equals("title")){
            isTitle = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if (isFirstName) {
            partOfFile.add(new String(ch, start, length));
            //System.out.println(new String(ch, start, length));
            isFirstName = false;
        } else if (isLastName){
            partOfFile.add(new String(ch, start, length));
            //System.out.println(new String(ch, start, length));
            isLastName = false;
        }else if (isTitle){
            partOfFile.add(new String(ch, start, length));
           //System.out.println(new String(ch, start, length));
            isTitle = false;
        }else if (isLine){
            partOfFile.add(new String(ch, start, length));
            //System.out.println(new String(ch, start, length));
            isLine = false;
        }
    }

    public ArrayList<String> getName(){return partOfFile;}
}
