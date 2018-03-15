package framework;


import org.openqa.selenium.By;
import org.testng.Assert;
import stepdefinition.SharedSD;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by donaldbialer on 3/9/18.
 */
public class DarkSkyTest extends BasePage {

    //Today element to expand view
    private By todayBar = By.xpath("//*[@id=\"week\"]/a[1]/span[2]/span[2]");
    //low temperature on parent bar
    private By parentLowTempToday = By.xpath("//*[@id=\"week\"]/a[1]/span[2]/span[1]");
    //high temperature on parent bar
    private By getParentHighTempToday = By.xpath("//*[@id=\"week\"]/a[1]/span[2]/span[3]");
    //low temperature on today expanded
    private By todayTempExpandLow = By.xpath("//*[@id=\"week\"]/div[2]/div[1]/div[2]/div[1]/span[1]/span[1]");
    //high temperature on today expanded
    private By todayTempExpandHigh = By.xpath("//*[@id=\"week\"]/div[2]/div[1]/div[2]/div[1]/span[3]/span[1]");

    //Time machine CTA button
    private By timeMachine_ctaButton = By.xpath("//*[@id=\"timeMachine\"]/div[2]/a");
    //CTA Button for today's date
    private By timeMachine_TodaysDate = By.className("is-today");
    private By timeMachine_TomorrowsDate = By.xpath("//*[@id=\"main\"]/div[1]/div[1]/div");


    //initialized variables and assigns list of week days
    private List<WebElement> listOfWeekDays = SharedSD.getDriver().findElements(By.xpath(".//div[@id='week']/descendant::span[class='name']"));

    //initialized list for expected weekdays
    private List<String> ExpectedWeekDaysList = new ArrayList<>();

    //initialized list for actual weekday
    private List<String> ActualWeekDaysList = new ArrayList<>();

    //Set list of days starting from current date
    public void setListOfWeekDays() {
        //create format to parse date
        SimpleDateFormat format = new SimpleDateFormat("EEE");
        //Instance of calendar
        Calendar calendar = Calendar.getInstance();
        //Set Current Date
        calendar.setTime(new Date());
        //Adds value to 0 index
        ExpectedWeekDaysList.add(0, "Today");
        //Iterates(counts and arranges) the time of base of listofWeekDays
        for (int i = 1; i < listOfWeekDays.size(); i++) ;
        //adds a day for each iteration
        calendar.add(Calendar.DAY_OF_WEEK, 1);
        //formats a day from String and adds it to List each iteration
        ExpectedWeekDaysList.add(format.format(calendar.getTime()));
    }

    //Gets text from listOfWeekDays collections and adds to ActualWeekDaysList collections
    public void setActualWeekDaysList() {
        for (WebElement listOfDays : listOfWeekDays) {
            //add string value to actual days
            ActualWeekDaysList.add(listOfDays.getText());
        }
    }

    //Compares ExpectedWeekDaysList to actual ActualWeekDaysList
    public boolean isWeekDisplayedCorrectly() {
        return (ExpectedWeekDaysList.equals(ActualWeekDaysList));
    }



    /* ----------------------------------------*/


    public void matchParentAndExpandedTemperature() {

        //click today today bar to expand
        clickOn(todayBar);
    }


    //Compare the 2 temperatures
    public boolean todayHighTemp() {

        //Get the highest and lowest temperature from parent bar
        String A = getTextFromElement(parentLowTempToday);
        String B = getTextFromElement(getParentHighTempToday);

        //Get the highest and lowest temperature from expanded view
        String C = getTextFromElement(todayTempExpandLow);
        String D = getTextFromElement(todayTempExpandHigh);

        //Verify high and low temperatures match
        return (A.equals(C) && (B.equals(D)));
    }



    /*-----------------------------------------------*/

    //Click on Time Machine
    public void clickOnTimeMachine() {
        clickOn(timeMachine_ctaButton);
    }


    //Select tomorrow on the calendar
    public void clickOnTomorrowsDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 1);
        String newDate = dateFormat.format(cal.getTime());
    }

        //set date library function to verfiy todays date
        //match date library with calendar date
        //add one day and then click

    //Click On the date.  Verify that it is not clickable
    public void clickOnTommorrowsDate() {
        clickOn(timeMachine_TomorrowsDate);
    }

    //Verify date is displayed in correct format
    public String dateDisplayedInCorrectFormat() {
        String TimeMachineDate = getTextFromElement(timeMachine_TomorrowsDate);
        String TimeMachineFormat = dateDisplayedInCorrectFormat();
        return TimeMachineDate;
    }
}













