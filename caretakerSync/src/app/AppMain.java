package app;

import dao.AdminDaoCollectionImpl;
import dao.CaretakerDaoCollectionImpl;
import dao.UserDaoCollectionImpl;
import presentation.Presentation;
import service.CaretakerService;
import service.AdminService;
import service.UserService;
import service.UserServiceImpl;
import service.AdminServiceImpl;
import service.CaretakerServiceImpl;


public class AppMain {
	
	    private static UserService userService = new UserServiceImpl(new UserDaoCollectionImpl()); 
	    private static AdminService adminService = new AdminServiceImpl(new AdminDaoCollectionImpl()); 
	    private static CaretakerService caretakerService = new CaretakerServiceImpl(new CaretakerDaoCollectionImpl()); 

	    public static void main(String[] args) {
	        Presentation presentation = new Presentation(userService, caretakerService, adminService);
	   
	    DummyDataInitializer.initialize();
	    System.out.println("Dummy data initialized.");

	    presentation.start();
	   
	   }
}

