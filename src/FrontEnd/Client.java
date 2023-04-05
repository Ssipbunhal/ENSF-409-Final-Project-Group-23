//package FrontEnd;
//
//
//import java.net.Socket;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.net.UnknownHostException;
//
//
///**
// * Provides data fields and methods to create a client which will connect to server
// * and use its login view to create the appropriate student or professor GUI
// * @authors Daniel Guieb, Huzaifa Amar
// * @version 1.0
// * @since April 12, 2018
// *
// */
//public class Client {
//	/**
//	 * A login GUI
//	 */
//	private LoginGUI loginView;
//
//	/**
//	 * A login handler
//	 */
//	private LoginModel loginModel;
//
//	/**
//	 * A socket to communicate with the server
//	 */
//	private Socket socket;
//
//	/**
//	 * An ObjectOutputStream to send objects
//	 */
//	private ObjectOutputStream sendObject;
//
//	/**
//	 * An ObjectInputStream to receive objects
//	 */
//	private ObjectInputStream readObject;
//
//	/**
//	 * A constructor which sets the socket to the host name and port number.
//	 * Also instantiates the object input and output streams
//	 * @param host - the socket local host
//	 * @param portnumber - the socket port number
//	 * @throws UnknownHostException
//	 * @throws IOException
//	 */
//	public Client(String host, int portnumber) throws UnknownHostException,
//											IOException
//	{
//		socket=new Socket(host, portnumber);
//		sendObject=new ObjectOutputStream(socket.getOutputStream());
//		readObject=new ObjectInputStream(socket.getInputStream());
//	}
//
//	/**
//	 * Creates the login GUI and login model
//	 */
//	public void makeLogin()
//	{
//		loginView = new LoginGUI();
//		loginView.addSignInActionListener(new SignInListener());
//		loginModel=new LoginModel(readObject, sendObject);
//		loginView.setVisible(true);
//	}
//
//	/**
//	 * Creates the professor's necessary working objects
//	 * @param profffirstname - the professor's first name
//	 * @param profflastname - the professor's last name
//	 * @param proffid - the professor's ID
//	 * @throws IOException
//	 */
//	private void makeProfessor(String profffirstname, String profflastname,
//			int proffid) throws IOException
//	{
//		ProfessorModel proffmodel=new ProfessorModel(sendObject, readObject);
//		ProfessorView proffview=new ProfessorView(proffid, profffirstname, profflastname);
//		ProfessorControl proffcontrol=new ProfessorControl(proffmodel, proffview);
//	}
//
//	/**
//	 * Creates the student's necessary working objects
//	 * @param studentfirstname - the student's first name
// 	 * @param studentlastname - the student's last name
//	 * @param studenid - the student's ID
//	 * @throws IOException
//	 */
//	private void makeStudent(String studentfirstname, String studentlastname, int studenid) throws IOException
//	{
//		StudentModel studentmodel=new StudentModel(sendObject, readObject);
//		StudentView studenview=new StudentView(studenid, studentfirstname, studentlastname);
//		StudentControl studentcontrol=new StudentControl(studentmodel, studenview);
//	}
//
//	public static void main(String[] args)
//	{
//		try {
//			Client client=new Client("localhost", 9090);
//			client.makeLogin();
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * An action listener for the sign in button in the login GUI.
//	 * When pressed, will verify if a user with the inputted information exists
//	 * and log them into their proper GUI if they do. If not, sends an error
//	 */
//	public class SignInListener implements ActionListener{
//
//		@Override
//		public void actionPerformed(ActionEvent arg0) {
//			String[] string=loginModel.loginAttempt(loginView.getUser(),
//					loginView.getPass());
//			if (string!=null) {
//				loginView.setVisible(false);
//				try {
//					if (string[0].equals("PROFF"))
//						makeProfessor(string[1], string[2],
//								Integer.parseInt(string[3]));
//					else
//						makeStudent(string[1], string[2],
//								Integer.parseInt(string[3]));
//				}
//				catch (NumberFormatException | IOException e) {
//					e.printStackTrace();
//				}
//			}
//			else {
//				loginView.simpleError(
//						"Incorrect Login Information: "
//						+ "User does not exist in database");
//			}
//		}
//	}
//}