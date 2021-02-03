package ensiscolarite.com.exception;

import java.text.SimpleDateFormat;
import java.util.Date;

import ensiscolarite.com.presentation.Application;

public class DatabaseException extends Exception {

	private static final long serialVersionUID = -1606026513109977018L;
	private Date date = new Date(); 
	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	private String datenow = formatter.format(date);
	
	public DatabaseException() {
		this("Une erreur est survenue avec la base de données");
		Application.monLogger.error(datenow + " Une error est survenue dans la base de données");
	}
	public DatabaseException(String reason) {
		
		super(reason);
		Application.monLogger.error(datenow + " " + reason);
	}

	public DatabaseException(Throwable cause) {
		super(cause);
	
	}
	public DatabaseException(String reason, Throwable cause) {
		super(reason, cause);
	}
	

}
