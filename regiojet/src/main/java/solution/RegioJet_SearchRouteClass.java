package solution;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class RegioJet_SearchRouteClass {
    private String searchRouteClass_XPATH = "//*[contains(concat(' ', normalize-space(@class), ' '), ' search-route ')]";
            private String routeClass_XPATH = searchRouteClass_XPATH + "//*[contains(concat(' ', normalize-space(@class), ' '), ' route ')]";
                private String selectRouteClass_XPATH = routeClass_XPATH + "/*[contains(concat(' ', normalize-space(@class), ' '), ' select-route ')]";
                    private String selectRouteInnerClass_XPATH = selectRouteClass_XPATH + "/*[contains(concat(' ', normalize-space(@class), ' '), ' select-route-inner ')]";
                        private String stationsSelectClass_XPATH = selectRouteInnerClass_XPATH + "/*[contains(concat(' ', normalize-space(@class), ' '), ' stations-select ')]";
                            private String selectWrapClass_routeFrom_XPATH = stationsSelectClass_XPATH + "/div[contains(concat(' ', normalize-space(@class), ' '), ' select-wrap ') and position()=1]";
                                        private String selectPlaceholderClass_routeFrom_XPATH = selectWrapClass_routeFrom_XPATH + "//*[contains(concat(' ', normalize-space(@class), ' '), ' Select-placeholder ')]";
                                        private String selectValueClass_routeFrom_XPATH = selectWrapClass_routeFrom_XPATH + "//*[contains(concat(' ', normalize-space(@class), ' '), ' Select-value ')]";
                                            private String routeFromDisplayed_XPATH = selectValueClass_routeFrom_XPATH + "//span[@id='react-select-route-from--value-item']";
                                        private String selectInputClass_routeFrom_XPATH = selectWrapClass_routeFrom_XPATH + "//*[contains(concat(' ', normalize-space(@class), ' '), ' Select-input ')]";
                                            private String routeFromInput_XPATH = selectInputClass_routeFrom_XPATH + "//input[@id='route-from']";
                                        private String clearClassButton_routeFrom_XPATH = selectWrapClass_routeFrom_XPATH + "//*[contains(concat(' ', normalize-space(@class), ' '), ' clear ')]";
                            private String selectWrapClass_routeTo_XPATH = stationsSelectClass_XPATH + "/div[contains(concat(' ', normalize-space(@class), ' '), ' select-wrap ') and position()=2]";
                                        private String selectPlaceholderClass_routeTo_XPATH = selectWrapClass_routeTo_XPATH + "//*[contains(concat(' ', normalize-space(@class), ' '), ' Select-placeholder ')]";
                                        private String selectValueClass_routeTo_XPATH = selectWrapClass_routeTo_XPATH + "//*[contains(concat(' ', normalize-space(@class), ' '), ' Select-value ')]";
                                            private String routeToDisplayed_XPATH = selectValueClass_routeTo_XPATH + "//span[@id='react-select-route-to--value-item']";
                                        private String selectInputClass_routeTo_XPATH = selectWrapClass_routeTo_XPATH + "//*[contains(concat(' ', normalize-space(@class), ' '), ' Select-input ')]";
                                            private String routeToInput_XPATH = selectInputClass_routeTo_XPATH + "//input[@id='route-to']";
                                        private String clearClassButton_routeTo_XPATH = selectWrapClass_routeTo_XPATH + "//*[contains(concat(' ', normalize-space(@class), ' '), ' clear ')]";
            private String dateClass_XPATH = searchRouteClass_XPATH + "//*[contains(concat(' ', normalize-space(@class), ' '), ' date ')]";
                private String selectDatesClass_XPATH = dateClass_XPATH + "/*[contains(concat(' ', normalize-space(@class), ' '), ' select-dates ')]";
                    private String dateSelectClass_there_XPATH = selectDatesClass_XPATH + "/*[contains(concat(' ', normalize-space(@class), ' '), ' date-select ') and position()=1]";
                        private String routeThereInput_XPATH = dateSelectClass_there_XPATH + "//*[@id='route-there-input']";
                        private String clearClassButton_there_XPATH = dateSelectClass_there_XPATH + "//*[contains(concat(' ', normalize-space(@class), ' '), ' button-icon ')]";
                    private String dateSelectClass_back_XPATH = selectDatesClass_XPATH + "/*[contains(concat(' ', normalize-space(@class), ' '), ' date-select ') and position()=2]";
                        private String routeBackInput_XPATH = dateSelectClass_back_XPATH + "//*[@id='route-back-input']";
                        private String clearClassButton_back_XPATH = dateSelectClass_back_XPATH + "//*[contains(concat(' ', normalize-space(@class), ' '), ' button-icon ')]";
            private String tariffClass_XPATH = searchRouteClass_XPATH + "//*[contains(concat(' ', normalize-space(@class), ' '), ' tariff ')]";

                    private String searchButton_XPATH = "//*[@id='search-button']";
                    private String loader_XPATHold="//*[contains(@id,'loader')]";
                    private String loader_XPATH="//*[contains(concat(' ', normalize-space(@class), ' '), ' loader ')]";


    public RegioJet_SearchRouteClass setRouteFrom(String routeFrom ){
        $(By.xpath(routeFromInput_XPATH)).setValue(routeFrom).pressEnter();
        $(By.xpath(routeFromDisplayed_XPATH)).shouldHave(Condition.text(routeFrom));
        return new RegioJet_SearchRouteClass();
    }

    public RegioJet_SearchRouteClass clearRouteFrom(){
        $(By.xpath(clearClassButton_routeFrom_XPATH)).click();
        $(By.xpath(clearClassButton_routeFrom_XPATH)).shouldNot(Condition.exist);
        return new RegioJet_SearchRouteClass();
    }

    public RegioJet_SearchRouteClass setRouteTo(String routeTo ){
        $(By.xpath(routeToInput_XPATH)).setValue(routeTo).pressEnter();
        $(By.xpath(routeToDisplayed_XPATH)).shouldHave(Condition.text(routeTo));
        return new RegioJet_SearchRouteClass();
    }

    public RegioJet_SearchRouteClass clearRouteTo(){
        $(By.xpath(clearClassButton_routeTo_XPATH)).click();
        $(By.xpath(clearClassButton_routeTo_XPATH)).shouldNot(Condition.exist);
        return new RegioJet_SearchRouteClass();
    }


    public RegioJet_SearchRouteClass clearRouteThere(){
        $(By.xpath(clearClassButton_there_XPATH)).click();
        $(By.xpath(clearClassButton_there_XPATH)).shouldNot(Condition.exist);
        return new RegioJet_SearchRouteClass();
    }

    public RegioJet_SearchRouteClass clearRouteBack(){
        $(By.xpath(clearClassButton_back_XPATH)).click();
        $(By.xpath(clearClassButton_back_XPATH)).shouldNot(Condition.exist);
        return new RegioJet_SearchRouteClass();
    }

    public RegioJet_DatePickerPortalClass expandRouteThere(){
        $(By.xpath(routeThereInput_XPATH)).click();
        return new RegioJet_DatePickerPortalClass();
    }

    public RegioJet_DatePickerPortalClass expandRouteBack(){
        $(By.xpath(routeBackInput_XPATH)).click();
        return new RegioJet_DatePickerPortalClass();
    }

    public RegioJet_ConnectionsListClass search(){
        $(By.xpath(searchButton_XPATH)).click();;
        $(By.xpath(loader_XPATH)).should(Condition.disappear);
        return new RegioJet_ConnectionsListClass();
    }


}
