package org.ferrari.utils.upload;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
/**
 * 
   * @create.date: Jun 24, 2009 2:01:20 PM     
   * @comment: <p>TODO</p>
   * @see: org.ferrari.utils.upload
   * @author: shellrong
   * @modify.by: shellrong
   * @modify.date: Jun 24, 2009 2:01:20 PM
 */
public class Logger {

	   /** The logger name. */
    private final String name;
    
    /**
     * Cached instances of allocated logger objects.
     */
    @SuppressWarnings("unchecked")
	private static Map loggerInstances = new TreeMap();
    
    private static final int NONE = 7;
 
    private static final int ALL = 1;

    private static final int FATAL = 6;

    private static final int ERROR = 5;

    private static final int WARN = 4;

    private static final int INFO = 3;

    private static final int DEBUG = 2;

 

    private static int logLevel = NONE;

    // Load log level from log.properties
    static {
        Properties props = new Properties();
        try {
            props.load(Logger.class.getResourceAsStream("/ftplog.properties"));
            String levelStr = props.getProperty("loglevel");

            if ("ALL".equalsIgnoreCase(levelStr)) {
                logLevel = ALL;
            } else if ("ERROR".equalsIgnoreCase(levelStr)) {
                logLevel = ERROR;
            } else if ("WARN".equalsIgnoreCase(levelStr)) {
                logLevel = WARN;
            } else if ("INFO".equalsIgnoreCase(levelStr)) {
                logLevel = INFO;
            } else if ("DEBUG".equalsIgnoreCase(levelStr)) {
                logLevel = DEBUG;
            } else if ("NONE".equalsIgnoreCase(levelStr)) {
                logLevel = NONE;
            } else if ("FATAL".equalsIgnoreCase(levelStr)) {
                logLevel = FATAL;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates new Logger the given logger name.
     * 
     * @param name
     *            the logger name.
     */
    protected Logger(final String name) {
        this.name = name;
        //this.loggerDelegate = getDelegatePlugin(name);
    }
    
    private String formatCurrentDate() {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return formatter.format(new Date());
    }

    /**
     * Added by BeanSoft.
     * 
     * @return the log level of this class
     */
    public int getLogLevel() {
        return logLevel;
    }

    /**
     * Return the name of this logger.
     * 
     * @return The name of this logger.
     */
    public String getName() {
        return name;
    }

    /**
     * Check to see if the DEBUG level is enabled for this logger.
     * 
     * @return true if a {@link #debug(Object)}method invocation would pass the
     *         msg to the configured appenders, false otherwise.
     */
    public boolean isDebugEnabled() {
        return getLogLevel() >= DEBUG;
    }

    /**
     * Issue a log msg with a level of DEBUG. Invokes log.log(Level.DEBUG,
     * message);
     */
    public void debug(Object message) {
        if (getLogLevel() <= DEBUG) {
            System.out.println(formatCurrentDate() + " " + getName() + ":debug:" + message);
        }
    }

    /**
     * Issue a log msg and throwable with a level of DEBUG. Invokes
     * log.log(Level.DEBUG, message, t);
     */
    public void debug(Object message, Throwable t) {
        if (getLogLevel() <= DEBUG) {
            System.out.println(formatCurrentDate() + " " + getName() + ":" + "debug:" + message);
            t.printStackTrace();
        }
    }

    /**
     * Check to see if the INFO level is enabled for this logger.
     * 
     * @return true if a {@link #info(Object)}method invocation would pass the
     *         msg to the configured appenders, false otherwise.
     */
    public boolean isInfoEnabled() {
        return getLogLevel() <= INFO;
    }

    /**
     * Issue a log msg with a level of INFO. Invokes log.log(Level.INFO,
     * message);
     */
    public void info(Object message) {
        if (getLogLevel() <= INFO) {
            System.out.println(formatCurrentDate() + " " + getName() + ":" + "info:" + message);
        }
    }

    /**
     * Issue a log msg and throwable with a level of INFO. Invokes
     * log.log(Level.INFO, message, t);
     */
    public void info(Object message, Throwable t) {
        if (getLogLevel() <= INFO) {
            System.out.println(formatCurrentDate() + " " + getName() + ":" + "info:" + message);
         t.printStackTrace();
        }
    }

    /**
     * Issue a log msg with a level of WARN. Invokes log.log(Level.WARN,
     * message);
     */
    public void warn(Object message) {
        if (getLogLevel() <= WARN) {
            System.out.println(formatCurrentDate() + " " + getName() + ":" + "warn:" + message);
        }
    }

    /**
     * Issue a log msg and throwable with a level of WARN. Invokes
     * log.log(Level.WARN, message, t);
     */
    public void warn(Object message, Throwable t) {
        if (getLogLevel() <= WARN) {
         System.out.println(formatCurrentDate() + " " + getName() + ":" + "warn:" + message);
         t.printStackTrace();
        }
    }

    /**
     * Issue a log msg with a level of ERROR. Invokes log.log(Level.ERROR,
     * message);
     */
    public void error(Object message) {
        if (getLogLevel() <= ERROR) {
            System.err.println(formatCurrentDate() + " " + getName() + ":" + "error:" + message);
        }
    }

    /**
     * Issue a log msg and throwable with a level of ERROR. Invokes
     * log.log(Level.ERROR, message, t);
     */
    public void error(Object message, Throwable t) {
        if (getLogLevel() <= ERROR) {
            System.err.println(formatCurrentDate() + " " + getName() + ":" + "error:" + message);
            t.printStackTrace(System.err);
        }
    }

    /**
     * Issue a log msg with a level of FATAL. Invokes log.log(Level.FATAL,
     * message);
     */
    public void fatal(Object message) {
        if (getLogLevel() <= FATAL) {
            System.err.println(formatCurrentDate() + " " + getName() + ":" + "fatal error:" + message);
        }        
    }

    /**
     * Issue a log msg and throwable with a level of FATAL. Invokes
     * log.log(Level.FATAL, message, t);
     */
    public void fatal(Object message, Throwable t) {
        if (getLogLevel() <= FATAL) {
            System.err.println(formatCurrentDate() + " " + getName() + ":" + "fatal error:" + message);
            t.printStackTrace(System.err);
        }
    }

    /////////////////////////////////////////////////////////////////////////
    //                            Factory Methods
    /////////////////////////////////////////////////////////////////////////
    
    /**
     * Create a anonymous Logger instance.
     * Date: 2005-04-09
     */
    public static Logger getAnonymousLogger() {
        return getLogger("");
    }
    
    /**
     * Create a Logger instance given the logger name from cached instances.
     * 
     * @param name
     *            the logger name
     */
    @SuppressWarnings("unchecked")
	public static Logger getLogger(String name) {
        Logger instance = (Logger) loggerInstances.get(name);
        
        if(instance == null) {
            instance = new Logger(name);
//            System.out.println(formatCurrentDate() + " " + instance + " not found, so create it!");
            loggerInstances.put(name, instance);
        }
        return instance;
    }

    /**
     * Create a Logger instance given the logger name with the given suffix.
     * 
     * <p>
     * This will include a logger seperator between classname and suffix
     * 
     * @param name
     *            The logger name
     * @param suffix
     *            A suffix to append to the classname.
     */
    public static Logger getLogger(String name, String suffix) {
        return getLogger(name + "." + suffix);
    }

    /**
     * Create a Logger instance given the logger class. This simply calls
     * create(clazz.getName()).
     * 
     * @param clazz
     *            the Class whose name will be used as the logger name
     */
    @SuppressWarnings("unchecked")
	public static Logger getLogger(Class clazz) {
        return getLogger(clazz.getName());
    }

    /**
     * Create a Logger instance given the logger class with the given suffix.
     * 
     * <p>
     * This will include a logger seperator between classname and suffix
     * 
     * @param clazz
     *            The Class whose name will be used as the logger name.
     * @param suffix
     *            A suffix to append to the classname.
     */
    @SuppressWarnings("unchecked")
	public static Logger getLogger(Class clazz, String suffix) {
        return getLogger(clazz.getName() + "." + suffix);
    }
    
    public String toString() {
        return getClass().getName() + "[" + getName();
    }
    public static void main(String[] args) {
        Logger.getLogger("test").fatal("fatal");
  Logger.getLogger("test").error("error");
  Logger.getLogger("test").debug("debug");
    }
}
