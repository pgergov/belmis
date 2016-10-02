package com.squad.vratsa.belmis;

import android.os.AsyncTask;
import android.util.Log;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by ivo on 02.10.16.
 */

public class SendRequest extends AsyncTask<String, Void, String>{
    private Exception exception;

//    @Override
//    protected void onPostExecute(String result) {
//
//    }

    public String doInBackground(String... urls){
        try{

            //Your server URL
            String url = urls[0];
            URL obj = new URL(url);
            //HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            //add reuqest header
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            //Request Parameters you want to send
            String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

            // Send post request
            con.setDoOutput(true);// Should be part of code only for .Net web-services else no need for PHP
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());
        } catch (Exception e){
            this.exception = e;
            System.out.println(e.getMessage());
            Log.e("Error", e.getMessage());
        }
        return "Working!";

    }

//    protected void doInBackground(String url_arg) {
//        try {
//            URL url = new URL(url_arg);
//            SAXParserFactory factory = SAXParserFactory.newInstance();
//            SAXParser= factory.newSAXParser();
//            XMLReader xmlreader = parser.getXMLReader();
////            RssHandler theRSSHandler = new RssHandler();
////            xmlreader.setContentHandler(theRSSHandler);
//            InputSource is = new InputSource(url.openStream());
//            xmlreader.parse(is);
//
//            return theRSSHandler.getFeed();
//        } catch (Exception e) {
//            this.exception = e;
//
//        }
//    }

}
