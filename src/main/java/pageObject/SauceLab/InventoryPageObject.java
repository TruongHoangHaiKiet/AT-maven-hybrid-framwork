package pageObject.SauceLab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.basePage;
import pageUIs.SauceLab.InventoryPageUI;

public class InventoryPageObject extends basePage {
	WebDriver driver;
	
	public InventoryPageObject(WebDriver driver){
		this.driver = driver;
	}

	public void selectSortDropdown(String valueItem) {
		waitForElementClickable(driver, InventoryPageUI.SORT_ITEM_DROPLIST);
		selectItemInDefaultDropdown(driver, InventoryPageUI.SORT_ITEM_DROPLIST, valueItem);
	}

	public boolean isProductNameSortAscending() {
		waitForAllElementVisible(driver, InventoryPageUI.PRODUCT_NAME);
		List<WebElement> productName = getListWebElement(driver, InventoryPageUI.PRODUCT_NAME);
		
		// UI
		List<String> productNameTextUI = new ArrayList<String>();
		for (WebElement product : productName) {
			productNameTextUI.add(product.getText());
		}
		
		System.out.println("---------------Product name at UI after sorting ASC---------------");
		for (String name: productNameTextUI) {
			System.out.println(name);
		}
		
		//Clone Text UI list to Text list will be sorted
		List<String> productNameTextUISort = new ArrayList<String>(productNameTextUI);
		
		// Collection Sort
		Collections.sort(productNameTextUISort);
		System.out.println("---------------Product name after sorting ASC---------------");
		for (String name: productNameTextUI) {
			System.out.println(name);
		}
		return productNameTextUISort.equals(productNameTextUI);
	}
	public boolean isProductNameSortAscending_Java8() {
		waitForAllElementVisible(driver, InventoryPageUI.PRODUCT_NAME);
		List<WebElement> productName = getListWebElement(driver, InventoryPageUI.PRODUCT_NAME);
		
		// UI
		List<String> productNameTextUI = productName.stream().map(n -> n.getText()).collect(Collectors.toList());
		
		//Sort
		List<String> productNameTextUISort = new ArrayList<String>(productNameTextUI);
		
		// Collection Sort
		Collections.sort(productNameTextUISort);
		return productNameTextUISort.equals(productNameTextUI);
	}

	public boolean isProductNameSortDescending() {
		waitForAllElementVisible(driver, InventoryPageUI.PRODUCT_NAME);
		List<WebElement> productName = getListWebElement(driver, InventoryPageUI.PRODUCT_NAME);
		
		// UI
		List<String> productNameTextUI = new ArrayList<String>();
		for (WebElement product : productName) {
			productNameTextUI.add(product.getText());
		}
		
		System.out.println("---------------Product name at UI after sorting DESC---------------");
		for (String name: productNameTextUI) {
			System.out.println(name);
		}
		
		//Clone Text UI list to Text list will be sorted
		List<String> productNameTextUISort = new ArrayList<String>(productNameTextUI);
		
		// Collection Sort
		Collections.sort(productNameTextUISort);
		
		// Reverse Data
		Collections.reverse(productNameTextUISort);
		
		System.out.println("---------------Product name after sorting DESC---------------");
		for (String name: productNameTextUI) {
			System.out.println(name);
		}
		return productNameTextUISort.equals(productNameTextUI);
	}

	public boolean isProductPriceSortAscending() {
		waitForAllElementVisible(driver, InventoryPageUI.PRODUCT_PRICE);
		List<WebElement> productPrice = getListWebElement(driver, InventoryPageUI.PRODUCT_PRICE);
		
		// UI
		List<Float> productPriceTextUI = new ArrayList<Float>();
		for (WebElement product : productPrice) {
		// Get text xong thì xóa ký tự $ đi		
		// Convert text qua kiểu float
			productPriceTextUI.add(Float.valueOf(product.getText().replace("$","")));
		}
		
		System.out.println("---------------Product price at UI after sorting ASC---------------");
		for (Float name: productPriceTextUI) {
			System.out.println(name);
		}
		
		//Clone Text UI list to Text list will be sorted
		List<Float> productPriceTextUISort = new ArrayList<Float>(productPriceTextUI);
		
		// Collection Sort
		Collections.sort(productPriceTextUISort);
		System.out.println("---------------Product price after sorting ASC---------------");
		for (Float name: productPriceTextUI) {
			System.out.println(name);
		}
		return productPriceTextUISort.equals(productPriceTextUI);
	}

	public boolean isProductPriceSortDescending() {
		waitForAllElementVisible(driver, InventoryPageUI.PRODUCT_PRICE);
		List<WebElement> productPrice = getListWebElement(driver, InventoryPageUI.PRODUCT_PRICE);
		
		// UI
		List<Float> productPriceTextUI = new ArrayList<Float>();
		for (WebElement product : productPrice) {
		// Get text xong thì xóa ký tự $ đi		
		// Convert text qua kiểu float
			productPriceTextUI.add(Float.valueOf(product.getText().replace("$","")));
		}
		
		System.out.println("---------------Product price at UI after sorting ASC---------------");
		for (Float name: productPriceTextUI) {
			System.out.println(name);
		}
		
		//Clone Text UI list to Text list will be sorted
		List<Float> productPriceTextUISort = new ArrayList<Float>(productPriceTextUI);
		
		// Collection Sort
		Collections.sort(productPriceTextUISort);
		
		// Reverse Data
		Collections.reverse(productPriceTextUISort);
		
		System.out.println("---------------Product price after sorting ASC---------------");
		for (Float name: productPriceTextUI) {
			System.out.println(name);
		}
		return productPriceTextUISort.equals(productPriceTextUI);
	}
	
}
