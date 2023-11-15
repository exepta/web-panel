package net.exsorce.webpanel.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Daniel Ramke
 * @since 1.0.0-SNAPSHOT
 */
public final class Commons
{

	public static String currentDate ()
	{
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return format.format( new Date() );
	}

}
