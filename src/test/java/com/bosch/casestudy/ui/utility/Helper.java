package com.bosch.casestudy.ui.utility;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Helper {

    public static ArrayList<String> getStringArray(List<WebElement> listOfElements) {
        ArrayList<String> stringArrayList = new ArrayList<>();
        Helper.pauseFor(1);
        listOfElements.forEach(element -> {
            stringArrayList.add(element.getText());
        });
        return stringArrayList;
    }

    public static ArrayList<Integer> convertToIntegerArrayList(ArrayList<String> listOfStrings) {
        ArrayList<Integer> listOfIntegers = new ArrayList<>();

        for (String str : listOfStrings) {
            try {
                int intValue = Integer.parseInt(str.replace(".",""));
                listOfIntegers.add(intValue);
            } catch (NumberFormatException e) {
                System.err.println("Skipping invalid string: " + str);
            }
        }
        return listOfIntegers;
    }

    public static void pauseFor(double seconds) {
        try {
            double milliseconds = seconds*1000;
            Thread.sleep((long) milliseconds);
        }
        catch(InterruptedException e) {
            System.out.println(e);
        }
    }
}
