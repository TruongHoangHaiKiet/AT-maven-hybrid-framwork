package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.basePage;
import io.qameta.allure.Step;
import pageObjects.Navigation.PageGeneratorManager;
import pageUIs.user.HomePageUI;

public class UserHomePageObjects extends basePage {
	// Hàm để tương tác với các element trên UI
	// Mỗi page Objects sẽ tương ứng với 1 page UI
	
	// Hàm khởi tạo - Constructor
	// Gọi đầu tiên khi khởi tạo 1 Class lên 
	// Cùng tên với tên Class
	// Không có kiểu trả về
	// Có tham số hoặc không tham số
	// 1 Class có thể có 1 hoặc nhiều hàm khởi tạo được
	// Nếu như mình không viết rõ hàm khởi tạo thì trong quá trình chạy nó sẽ gọi đến hàm khởi tạo mặc định 
	// của Class đó (hàm khởi tạo rỗng: tên hàm/ không có phần thân)
	
	WebDriver driver;
	// Truy cập đến biến global trong case trùng tên với biến local dùng keyword this
	// Hàm khởi tạo (Constructor) để map driver
	public UserHomePageObjects(WebDriver driver) {
		this.driver = driver;
	}
	
	@Step("Click to My account link")
	public UserLoginPageObjects clickToMyAccountLink() {
		waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getUserLoginPage(driver);
	}
		
	
	
}
