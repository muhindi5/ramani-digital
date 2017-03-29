/*
 *  Bitforge Software Labs
 *  (c)2017 
 *  http://bitforge.co.ke
 *  <muhindi@bitforge.co.ke><muhindi09@gmail.com>
 */

package util;

import java.security.SecureRandom;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @project: ramani-digital
 * @author kelly
 * @date Mar 27, 2017 6:24:39 AM
 * 
 */
public class RandomDirNameGen {

    private static final String CHARS = "0123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjklmnpqrstuvwxyz";
    private static SecureRandom random  = new SecureRandom();
    
    public String getDirectoryName(){
        int strLength = 10;
        StringBuilder sb = new StringBuilder(strLength);
        for (int i = 0; i < strLength; i++) {
               sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }
        return sb.toString();
    }
}
