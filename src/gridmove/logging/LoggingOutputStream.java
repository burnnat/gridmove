package gridmove.logging;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/** 
 * An OutputStream that writes contents to a Logger upon each call to flush() 
 */ 
public class LoggingOutputStream extends ByteArrayOutputStream {

	private String lineSeparator; 

	private Logger logger; 
	private PrintStream ps;
	private Level level; 

	/** 
	 * Constructor 
	 * @param logger Logger to write to 
	 * @param level Level at which to write the log message 
	 */ 
	public LoggingOutputStream(Logger logger, PrintStream ps, Level level) { 
		super(); 
		this.logger = logger;
		this.ps = ps;
		this.level = level; 
		lineSeparator = System.getProperty("line.separator"); 
	} 

	/** 
	 * upon flush() write the existing contents of the OutputStream
	 * to the logger as a log record. 
	 * @throws java.io.IOException in case of error 
	 */ 
	public void flush() throws IOException { 

		String record; 
		synchronized(this) { 
			super.flush(); 
			record = this.toString(); 
			super.reset(); 

			if (record.length() == 0 || record.equals(lineSeparator)) { 
				// avoid empty records 
				return; 
			} 

			ps.println(record);
			logger.logp(level, "", "", record); 
		} 
	} 
} 