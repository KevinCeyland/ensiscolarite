package ensiscolarite.com.exception;

import java.text.SimpleDateFormat;
import java.util.Date;

import ensiscolarite.com.presentation.Application;

public class DatabaseDriverException extends DatabaseException {

	private static final long serialVersionUID = 5437984740965826501L;
	private Date date = new Date(); 
	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	private String datenow = formatter.format(date);
	
	public DatabaseDriverException() {
		this(" Classe introuvable, contactez l'administrateur");
		Application.databaseLogger.error(datenow + " Classe introuvable, contactez l'administrateur");
	}
	public DatabaseDriverException(String reason) {
		super(reason);
		Application.databaseLogger.error(datenow + " " + reason);
	}

	public DatabaseDriverException(Throwable cause) {
		super(cause);
	
	}
	public DatabaseDriverException(String reason, Throwable cause) {
		super(reason, cause);
	}

}
