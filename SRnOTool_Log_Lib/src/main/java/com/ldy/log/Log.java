package com.ldy.log;


import com.ldy.log.lib.BuildConfig;

public class Log {
	/**
	 * true-开发模式，打印日志；false-生产模式，不打印日志
	 */
	public static final boolean ISDEBUG = BuildConfig.DEBUG;

	/**
	 * Send a {@link #VERBOSE} android.util.Log message.
	 * 
	 * @param tag
	 *            Used to identify the source of a android.util.Log message. It
	 *            usually identifies the class or activity where the
	 *            android.util.Log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 */
	public static int v(String tag, String msg) {
		if (!ISDEBUG) {
            return -1;
        }
		return android.util.Log.v(tag, msg);
	}

	/**
	 * Send a {@link #VERBOSE} android.util.Log message and android.util.Log the
	 * exception.
	 * 
	 * @param tag
	 *            Used to identify the source of a android.util.Log message. It
	 *            usually identifies the class or activity where the
	 *            android.util.Log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 * @param tr
	 *            An exception to android.util.Log
	 */
	public static int v(String tag, String msg, Throwable tr) {
		if (!ISDEBUG) {
            return -1;
        }
		return android.util.Log.v(tag, msg, tr);
	}

	/**
	 * Send a {@link #DEBUG} android.util.Log message.
	 * 
	 * @param tag
	 *            Used to identify the source of a android.util.Log message. It
	 *            usually identifies the class or activity where the
	 *            android.util.Log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 */
	public static int d(String tag, String msg) {
		if (!ISDEBUG) {
            return -1;
        }
		return android.util.Log.d(tag, msg);
	}

	/**
	 * Send a {@link #DEBUG} android.util.Log message and android.util.Log the
	 * exception.
	 * 
	 * @param tag
	 *            Used to identify the source of a android.util.Log message. It
	 *            usually identifies the class or activity where the
	 *            android.util.Log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 * @param tr
	 *            An exception to android.util.Log
	 */
	public static int d(String tag, String msg, Throwable tr) {
		if (!ISDEBUG) {
            return -1;
        }
		return android.util.Log.d(tag, msg, tr);
	}

	/**
	 * Send an {@link #INFO} android.util.Log message.
	 * 
	 * @param tag
	 *            Used to identify the source of a android.util.Log message. It
	 *            usually identifies the class or activity where the
	 *            android.util.Log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 */
	public static int i(String tag, String msg) {
		if (!ISDEBUG) {
            return -1;
        }
		return android.util.Log.i(tag, msg);
	}

	/**
	 * Send a {@link #INFO} android.util.Log message and android.util.Log the
	 * exception.
	 * 
	 * @param tag
	 *            Used to identify the source of a android.util.Log message. It
	 *            usually identifies the class or activity where the
	 *            android.util.Log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 * @param tr
	 *            An exception to android.util.Log
	 */
	public static int i(String tag, String msg, Throwable tr) {
		if (!ISDEBUG) {
            return -1;
        }
		return android.util.Log.i(tag, msg, tr);
	}

	/**
	 * Send a {@link #WARN} android.util.Log message.
	 * 
	 * @param tag
	 *            Used to identify the source of a android.util.Log message. It
	 *            usually identifies the class or activity where the
	 *            android.util.Log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 */
	public static int w(String tag, String msg) {
		if (!ISDEBUG) {
            return -1;
        }
		return android.util.Log.w(tag, msg);
	}

	/**
	 * Send a {@link #WARN} android.util.Log message and android.util.Log the
	 * exception.
	 * 
	 * @param tag
	 *            Used to identify the source of a android.util.Log message. It
	 *            usually identifies the class or activity where the
	 *            android.util.Log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 * @param tr
	 *            An exception to android.util.Log
	 */
	public static int w(String tag, String msg, Throwable tr) {
		if (!ISDEBUG) {
            return -1;
        }
		return android.util.Log.w(tag, msg, tr);
	}

	/*
	 * Send a {@link #WARN} android.util.Log message and android.util.Log the
	 * exception.
	 * 
	 * @param tag Used to identify the source of a android.util.Log message. It
	 * usually identifies the class or activity where the android.util.Log call
	 * occurs.
	 * 
	 * @param tr An exception to android.util.Log
	 */
	public static int w(String tag, Throwable tr) {
		if (!ISDEBUG) {
            return -1;
        }
		return android.util.Log.w(tag, tr);
	}

	/**
	 * Send an {@link #ERROR} android.util.Log message.
	 * 
	 * @param tag
	 *            Used to identify the source of a android.util.Log message. It
	 *            usually identifies the class or activity where the
	 *            android.util.Log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 */
	public static int e(String tag, String msg) {
		if (!ISDEBUG) {
            return -1;
        }
		return android.util.Log.e(tag, msg);
	}

	/**
	 * Send a {@link #ERROR} android.util.Log message and android.util.Log the
	 * exception.
	 * 
	 * @param tag
	 *            Used to identify the source of a android.util.Log message. It
	 *            usually identifies the class or activity where the
	 *            android.util.Log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 * @param tr
	 *            An exception to android.util.Log
	 */
	public static int e(String tag, String msg, Throwable tr) {
		if (!ISDEBUG) {
            return -1;
        }
		return android.util.Log.e(tag, msg, tr);
	}

	/**
	 * Handy function to get a loggable stack trace from a Throwable
	 * 
	 * @param tr
	 *            An exception to android.util.Log
	 */
	public static String getStackTraceString(Throwable tr) {
		if (!ISDEBUG) {
            return "";
        }
		return android.util.Log.getStackTraceString(tr);
	}

	/**
	 * Low-level logging call.
	 * 
	 * @param priority
	 *            The priority/type of this android.util.Log message
	 * @param tag
	 *            Used to identify the source of a android.util.Log message. It
	 *            usually identifies the class or activity where the
	 *            android.util.Log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 * @return The number of bytes written.
	 */
	public static int println(int priority, String tag, String msg) {
		if (!ISDEBUG) {
            return -1;
        }
		return println(LOG_ID_MAIN, priority, tag, msg);
	}

	/** @hide */
	public static final int LOG_ID_MAIN = 0;
	/** @hide */
	public static final int LOG_ID_RADIO = 1;
	/** @hide */
	public static final int LOG_ID_EVENTS = 2;
	/** @hide */
	public static final int LOG_ID_SYSTEM = 3;
	/** @hide */
	public static final int LOG_ID_CRASH = 4;

	/** @hide */
	@SuppressWarnings("unused")
	public static int println(int bufID, int priority, String tag, String msg) {
		return 0;
	}
}
