package ISS.functionality.userComms;

import ISS.database.numberofastronauts.entity.NumberOfAstronauts;
import ISS.database.position.entity.Position;
import ISS.functionality.managers.DatabaseManager;
import ISS.functionality.managers.PeopleInSpaceManager;
import ISS.functionality.managers.PositionManager;
import ISS.functionality.managers.SpeedManager;

import javax.swing.*;

public class MainWindow {
	private static PositionManager positionManager;
	private static PeopleInSpaceManager peopleInSpaceManager;
	private static SpeedManager speedManager;
	private static DatabaseManager databaseManager;
	private static final StringBuilder servicesStatus = new StringBuilder();
	
	private static final JFrame passTimesFrame = new JFrame("Czasy przelotów ISS");
	private final PassTimes passTimesWindow = new PassTimes();
	private JPanel mainContainer;
	private JButton issLocationButton;
	private JButton peopleInSpaceButton;
	private JButton passTimesButton;
	private JButton exitButton;
	private JTextPane outputPane;
	private JButton button_showSpeed;

	public MainWindow() {

		preparePassTimesWindow();
		outputPane.setText(servicesStatus.toString());
		
		exitButton.addActionListener(e -> System.exit(0));
		issLocationButton.addActionListener(e -> {
			Position currentPosition = databaseManager.getLastPosition();
			displayPosition(currentPosition);
		});
		
		peopleInSpaceButton.addActionListener(e -> {
			NumberOfAstronauts numberOfAstronauts = peopleInSpaceManager.getPeopleInSpace();
			displayAstronauts(numberOfAstronauts);
		});
		
		passTimesButton.addActionListener(e -> {
			passTimesFrame.setVisible(true);
			passTimesFrame.transferFocus();
		});
		
		button_showSpeed.addActionListener(e -> {
			double currentSpeed = speedManager.getSpeed();
			outputPane.setText(String.valueOf(currentSpeed));
		});
	}
	
	public void closeWindow() {
		passTimesFrame.setVisible(false);
		calculatePassTimes();
	}
	
	private void calculatePassTimes() {
		Double latitude = Double.parseDouble(passTimesWindow.getLatitude());
		Double longitude = Double.parseDouble(passTimesWindow.getLongitude());
		Integer passNo = Integer.parseInt(passTimesWindow.getPassNo());
		System.out.println(latitude);
		System.out.println(longitude);
		System.out.println(passNo);
	}

	private void displayPosition(Position position) {
		String issPosition = "Bieżąca pozycja stacji to:" +
				"\nSzerokość geograficzna: " + position.getLatitude() +
				"\nDługość geograficzna: " + position.getLongitude();
		outputPane.setText(issPosition);
	}

	private void displayAstronauts(NumberOfAstronauts astronauts) {
		String PiS = "Ilość ludzi przebywających obecnie w kosmosie: " +
				astronauts.getCount();
				
		outputPane.setText(PiS);
	}
	
	private void preparePassTimesWindow() {
		passTimesFrame.setContentPane(passTimesWindow.getMainContainer());
		passTimesFrame.setDefaultCloseOperation(passTimesFrame.HIDE_ON_CLOSE);
		passTimesFrame.pack();
		passTimesFrame.setLocation(200, 200);
		passTimesWindow.setMainWindow(this);
	}
	
	public static void startApplication() {
		databaseManager = new DatabaseManager();
		speedManager = new SpeedManager();
		positionManager = new PositionManager();
		peopleInSpaceManager = new PeopleInSpaceManager();
		
		boolean positionStatus = positionManager.startIssPositionQuery();
		boolean peopleInSpaceStatus = peopleInSpaceManager.startIssPositionQuery();
		
		servicesStatus.append("Usługa pobierania pozycji: ");
		if (positionStatus)
			servicesStatus.append("uruchomiona.");
		else
			servicesStatus.append("błąd usługi.");

		servicesStatus.append("\nUsługa pobierania ilości astronautów: ");
		if (peopleInSpaceStatus)
			servicesStatus.append("uruchomiona.");
		else
			servicesStatus.append("błąd usługi.");

		JFrame frame = new JFrame("ISS Project!");
		frame.setContentPane(new MainWindow().mainContainer);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocation(100,100);
		frame.setVisible(true);
	}
	
	public static PositionManager getPositionManager() {
		return positionManager;
	}
	
	public static PeopleInSpaceManager getPeopleInSpaceManager() {
		return peopleInSpaceManager;
	}
	
	public static SpeedManager getSpeedManager() {
		return speedManager;
	}
	
	public static DatabaseManager getDatabaseManager() {
		return databaseManager;
	}
}
