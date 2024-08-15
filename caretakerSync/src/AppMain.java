import dao.AdminDaoCollectionImpl;
import dao.CaretakerDaoCollectionImpl;
import dao.UserDaoCollectionImpl;
import presentation.Presentation;
import service.*;


public class AppMain {

	    private static UserService userService = new UserServiceImpl(new UserDaoCollectionImpl()); 
	    private static AdminService adminService = new AdminServiceImpl(new AdminDaoCollectionImpl()); 
	    private static CaretakerService caretakerService = new CaretakerServiceImpl(new CaretakerDaoCollectionImpl()); 

	    public static void main(String[] args) {
	        Presentation presentation = new Presentation(userService, caretakerService, adminService);
	        presentation.start();
	    }
}

