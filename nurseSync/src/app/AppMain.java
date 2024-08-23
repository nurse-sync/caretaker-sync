package app;

import dao.AdminDaoCollectionImpl;
import dao.CaretakerDaoCollectionImpl;
import dao.RequestDaoCollectionImpl;
import dao.UserDaoCollectionImpl;
import presentation.Presentation;
import presentation.PresentationDemo;
import service.CaretakerService;
import service.AdminService;
import service.UserService;
import service.UserServiceImpl;
import service.AdminServiceImpl;
import service.CaretakerServiceImpl;
import service.RequestService;
import service.RequestServiceImpl;

public class AppMain {

	private static UserService userService = new UserServiceImpl(new UserDaoCollectionImpl());
	private static AdminService adminService = new AdminServiceImpl(new AdminDaoCollectionImpl());
	private static CaretakerService caretakerService = new CaretakerServiceImpl(new CaretakerDaoCollectionImpl());
	private static RequestService requestService = new RequestServiceImpl(new RequestDaoCollectionImpl()); // Initialize
																											// RequestService

	public static void main(String[] args) {
		PresentationDemo presentation = new PresentationDemo(userService, caretakerService, adminService,
				requestService); // Pass RequestService

		DummyDataInitializer dummyDataInitializer = new DummyDataInitializer(userService, caretakerService,
				adminService);
		dummyDataInitializer.initializeData();

		System.out.println("Dummy data initialized.");

		presentation.start();

	}
}
