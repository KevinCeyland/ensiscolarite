package ensiscolarite.com.exception;

import java.text.SimpleDateFormat;
import java.util.Date;

import ensiscolarite.com.presentation.Application;

public class ServiceValidateDateException extends ServiceException {


	private static final long serialVersionUID = 6997441686795206834L;
	private Date date = new Date(); 
	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	private String datenow = formatter.format(date);

	public ServiceValidateDateException() {
		this("Le format de date n'est pas le bon ");
		Application.serviceLogger.error(datenow + " Le format de date n'est pas le bon");
		
	}
	public ServiceValidateDateException(String message) {
		super(message);
		Application.serviceLogger.error(datenow + " " + message);
		
	}
	public ServiceValidateDateException(Throwable cause) {
		super(cause);
		
	}
	public ServiceValidateDateException(String message, Throwable cause) {
		super(message, cause);
		
	}

}
