package com.parabank.qa.helpers;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropDownHelper {

    public void selectByVisibleText(WebElement dropdownElement, String visibleText) {
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(visibleText);
    }

    public void selectByValue(WebElement dropdownElement, String value) {
        Select select = new Select(dropdownElement);
        select.selectByValue(value);
    }

    public void selectByIndex(WebElement dropdownElement, int index) {
        Select select = new Select(dropdownElement);
        select.selectByIndex(index);
    }

    public String getSelectedValue(WebElement dropdownElement) {
        Select select = new Select(dropdownElement);
        return select.getFirstSelectedOption().getText();
    }
    
    public void deselectByVisibleText(WebElement dropdownElement, String text) {
        Select select = new Select(dropdownElement);
        select.deselectByVisibleText(text);
    }
    
    public List<String> getAllDropdownOptions(WebElement dropdownElement) {
        Select select = new Select(dropdownElement);
        return select.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
