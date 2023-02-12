package pageUIs.user;

public class HomePageUI {
	// Defind các locator (UI) của element thuộc page đó
	// public: Bất kỳ 1 class nào cũng truy cập đến biến này được
	// protected: Cho phép class con truy cập
	// không set gì hết (default): cho các class trong cùng package truy cập
	// private: Chỉ có Class này mới dùng được
	
	// static: biến tĩnh/ hàm tĩnh
	// Nó cho phép truy cập trực tiếp từ Class
	// Không cần truy cập thông qua việc khởi tạo instance
	
	// final: biến final/ hàm final
	// Không cho gán lại dữ liệu/ không cho phép override
	
	// String: chuỗi - đại diện cho 1 locator
	
	// static final: quy ước đây là 1 hằng số (constant)
	// Bắt buộc phải viết hoa tất cả các ký tự. Ex: MY_ACCOUNT_LINK
	// Phân tách bởi dấu _
	
	public static final String MY_ACCOUNT_LINK = "xpath=//div[@class='footer']//a[@title='My Account']";
	
}
