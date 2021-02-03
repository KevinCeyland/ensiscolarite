package ensiscolarite.com.exception;

import java.text.SimpleDateFormat;
import java.util.Date;

import ensiscolarite.com.presentation.Application;

public class DatabaseInsertionException extends DatabaseException {

	private static final long serialVersionUID = 968107516459772738L;
	private Date date = new Date(); 
	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	private String datenow = formatter.format(date);
	
	public DatabaseInsertionException() {
		this("Une erreur est survenue avec lors de l'insertion des données");
		Application.monLogger.error(datenow + " Une erreur est survenue avec lors de l'insertion des données");
	}
	public DatabaseInsertionException(String reason) {
		super(reason);
		Application.monLogger.error(datenow + " " + reason);
	}

	public DatabaseInsertionException(Throwable cause) {
		super(cause);
	
	}
	public DatabaseInsertionException(String reason, Throwable cause) {
		super(reason, cause);
	}

}
