package solution;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;

public class RegioJet_DatePickerPortalClass {
    private String datePickerPortalClass_XPATH = "//*[contains(concat(' ', normalize-space(@class), ' '), ' react-datepicker__portal ')]";
//        private String datePickerTriangleClass_XPATH = datePickerPortalClass_XPATH + "//*[contains(concat(' ', normalize-space(@class), ' '), ' react-datepicker__triangle ')]";
            private String previousMonthButton_XPATH = /*datePickerTriangleClass_XPATH +*/ "//*[contains(concat(' ', normalize-space(@class), ' '), ' react-datepicker__navigation--previous ')]";
            private String nextMonthButton_XPATH = /*datePickerTriangleClass_XPATH +*/ "//*[contains(concat(' ', normalize-space(@class), ' '), ' react-datepicker__navigation--next ')]";
//        private String datePickerMonthContainerClass_XPATH = datePickerPortalClass_XPATH + "//*[contains(concat(' ', normalize-space(@class), ' '), ' react-datepicker__month-container ')]";
            private String datePickerHeaderClass_XPATH = /*datePickerMonthContainerClass_XPATH +*/ "//*[contains(concat(' ', normalize-space(@class), ' '), ' react-datepicker__header ')]";
            private String datePickerMonthClass_XPATH = /*datePickerMonthContainerClass_XPATH +*/ "//*[contains(concat(' ', normalize-space(@class), ' '), ' react-datepicker__month ')]";
                private String dayElement_XPATH = datePickerMonthClass_XPATH + "//*[text()='%s']"; // %s = dayNumber
                private String selectedDayElement_XPATH = datePickerMonthClass_XPATH + "//*[@tabindex='0']";

    private String getYear() {
        String displayedYearMonthLabel = $(By.xpath(datePickerMonthClass_XPATH)).getAttribute("aria-label"); // "month  2021-03"
        String displayedYearMonth = displayedYearMonthLabel.substring(7); // "2021-03"
        String displayedYear = displayedYearMonth.substring(0,4); //"2021"
        return displayedYear;
    }

    private String getMonth() {
        String displayedYearMonthLabel = $(By.xpath(datePickerMonthClass_XPATH)).getAttribute("aria-label"); // "month  2021-03"
        String displayedYearMonth = displayedYearMonthLabel.substring(7); // "2021-03"
        String displayedMonth = displayedYearMonth.substring(5,7); //"03"
        return displayedMonth;
    }

    private String getDay() {
        String displayedDay = $(By.xpath(selectedDayElement_XPATH)).getText();
        return displayedDay;
    }


    private RegioJet_DatePickerPortalClass clickNextMonth() {
        $(By.xpath(nextMonthButton_XPATH)).click();
        return new RegioJet_DatePickerPortalClass();
    }

    private RegioJet_DatePickerPortalClass clickPreviousMonth() {
        $(By.xpath(previousMonthButton_XPATH)).click();
        return new RegioJet_DatePickerPortalClass();
    }

    public RegioJet_DatePickerPortalClass setYearAndMonth(String yearNum, String monthNum) {
        $(By.xpath(nextMonthButton_XPATH)).shouldBe(Condition.visible);
        $(By.xpath(datePickerMonthClass_XPATH)).shouldBe(Condition.visible);

        while(Integer.parseInt(getYear()) < Integer.parseInt(yearNum)) {
            clickNextMonth();
        }
        while(Integer.parseInt(getMonth()) < Integer.parseInt(monthNum)) {
            clickNextMonth();
        }
        while(Integer.parseInt(getYear()) > Integer.parseInt(yearNum)) {
            clickPreviousMonth();
        }
        while(Integer.parseInt(getMonth()) > Integer.parseInt(monthNum)) {
            clickPreviousMonth();
        }

        String desiredYearAndMonthLabel = "month  "+yearNum + "-" + monthNum;
        $(By.xpath(datePickerMonthClass_XPATH)).shouldHave(Condition.attributeMatching("aria-label",desiredYearAndMonthLabel)); // "month  2021-03"

        return new RegioJet_DatePickerPortalClass();
    }

    public RegioJet_DatePickerPortalClass setYearAndMonth(LocalDate localDate) {
        String yearNum = localDate.format(DateTimeFormatter.ofPattern("yyyy"));
        String monthNum = localDate.format(DateTimeFormatter.ofPattern("MM"));
        return setYearAndMonth(yearNum,monthNum);
    }

    public RegioJet_SearchRouteClass setDay(String dayNum) {
//        $(By.xpath("//*[@class='react-datepicker']//*[text()='" + dayNum + "']")).click();
        $(By.xpath(String.format(dayElement_XPATH,dayNum))).click();
        return new RegioJet_SearchRouteClass();
    }

    public RegioJet_SearchRouteClass setDay(LocalDate localDate) {
        String dayNum =  localDate.format(DateTimeFormatter.ofPattern("dd"));
        return setDay(dayNum);
    }

    public RegioJet_SearchRouteClass clickToday() {
        $(By.xpath(selectedDayElement_XPATH)).click(); // today
        return new RegioJet_SearchRouteClass();

    }


}
