package com.example.java_pos_1;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Extract {

    public static Double[] get_price() {
        String petrolUrl = "https://www.acko.com/fuel/petrol-price-in-new-delhi"; // Replace with your target URL
        String petrolXpath = "/html/body/div[1]/div[3]/div[1]/div[5]/p[2]";

        String dieselUrl = "https://www.acko.com/fuel/diesel-price-in-west-delhi"; // Replace with your target URL
        String dieselXpath = "/html/body/div[1]/div[3]/div[1]/div[5]/p[2]";

        // https://www.acko.com/fuel/diesel-price-in-west-delhi
        // /html/body/div[1]/div[3]/div[1]/div[5]/p[2]

        Double petrolPrice = 0.0, dieselPrice = 0.0;

        try {
            System.out.println("EXTRACTING");
            Document doc = Jsoup.connect(petrolUrl).get();
            Elements elements = doc.selectXpath(petrolXpath);
            // System.out.println(elements);

            for (Element element : elements) {
                // System.out.println("element");
                // System.out.println(element.text());
                petrolPrice = Double.parseDouble(element.text());
            }
        } catch (Exception e) {
            e.printStackTrace();
            // System.err.println(e);
        }

        // DIESEL
        try {
            System.out.println("EXTRACTING Petrol ");
            Document doc = Jsoup.connect(dieselUrl).get();
            Elements elements = doc.selectXpath(dieselXpath);
            // System.out.println(elements);

            for (Element element : elements) {
                // System.out.println("element");
                // System.out.println(element.text());
                dieselPrice = Double.parseDouble(element.text());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
        }

        System.out.println(petrolPrice);
        System.out.println(dieselPrice);
        return new Double[] { petrolPrice, dieselPrice };
    }
}
