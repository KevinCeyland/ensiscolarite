package ensiscolarite.com.exception;

import java.text.SimpleDateFormat;
import java.util.Date;

import ensiscolarite.com.presentation.Application;

public class DatabaseRecuperationException extends DatabaseException {

	private static final long serialVersionUID = -7940366994927455802L;
	private Date date = new Date(); 
	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	private String datenow = formatter.format(date);
	
	public DatabaseRecuperationException() {
		this("Une erreur est survenue avec lors de la récupération des données");
		Application.databaseLogger.error(datenow + " Une erreur est survenue avec lors de la récupération des données");
	}
	public DatabaseRecuperationException(String reason) {
		super(reason);
		Application.databaseLogger.error(datenow + " " + reason);
	}

	public DatabaseRecuperationException(Throwable cause) {
		super(cause);
	
	}
	public DatabaseRecuperationException(String reason, Throwable cause) {
		super(reason, cause);
	}
}
