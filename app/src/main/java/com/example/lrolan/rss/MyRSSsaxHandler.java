package com.example.lrolan.rss;

import android.graphics.Bitmap;
import android.util.Log;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.net.URL;
import java.util.List;


public class MyRSSsaxHandler extends DefaultHandler {
    private String url = null ;// l'URL du flux RSS à parser
    // Ensemble de drapeau permettant de savoir où en est le parseur dans le flux XML
    private boolean inTitle = false ;
    private boolean inDescription = false ;
    private boolean inItem = false ;
    private boolean inDate = false ;
    // L'image référencée par l'attribut url du tag <enclosure>
    private Bitmap image = null ;
    private String imageURL = null ;
    // Le titre, la description et la date extraits du flux RSS
    private StringBuffer title = new StringBuffer();
    private StringBuffer description = new StringBuffer();
    private StringBuffer date = new StringBuffer();
    private int numItem = 0; // Le numéro de l'item à extraire du flux RSS
    private int numItemMax = - 1; // Le nombre total d’items dans le flux RSS
    private List<Item> liste;

    public void setUrl(String url){
        this.url= url;
    }

    public void processFeed(){
        try {
            numItem = 0; //A modifier pour visualiser un autre item
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            reader.setContentHandler(this);
            InputStream inputStream = new URL(url).openStream();
            reader.parse(new InputSource(inputStream));
            image = getBitmap(imageURL);
            numItemMax = numItem;
        }catch(Exception e) {
            Log.e("smb116rssview", "processFeed Exception" + e.getMessage());
        }
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {


    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName)
        {
            case "item":
                liste.add(new Item(new String(title), new String(description), new String(date), imageURL));
                numItemMax += 1;
                break;
            case "title":
                //TODO
                break;
        }
    }

    public void characters(char ch[], int start, int length){
        String chars = new String(ch).substring(start, start+length);
    }

    public Bitmap getBitmap(String imageURL) {

        return null;
    }

}
