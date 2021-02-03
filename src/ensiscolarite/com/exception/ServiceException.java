package ensiscolarite.com.exception;

import java.text.SimpleDateFormat;
import java.util.Date;

import ensiscolarite.com.presentation.Application;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 1697106604407975793L;
	private Date date = new Date(); 
	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	private String datenow = formatter.format(date);
	
	public ServiceException() {
		this("une erreur est survenue dans la couche service");
		Application.monLogger.error(datenow + " une erreur est survenue dans la couche service.");
		
	}
	public ServiceException(String message) {
		super(message);
		Application.monLogger.error(datenow + " " + message);
		
	}
	public ServiceException(Throwable cause) {
		super(cause);
		
	}
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		
	}

	

	
	
	

}
