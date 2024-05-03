/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.time.LocalDateTime;
import java.util.Random;

/**
 *
 * @author jeff
 */
public class randomGen {
    
    public int generateReaderID() {
        LocalDateTime now = LocalDateTime.now();

        long seed = now.getYear() * 10000000000L +
                    now.getMonthValue() * 100000000L +
                    now.getDayOfMonth() * 1000000L +
                    now.getHour() * 10000L +
                    now.getMinute() * 100L +
                    now.getSecond();

        Random random = new Random(seed);
        int idreader = Math.abs(random.nextInt());

        System.out.println("Random Number: " + idreader);
        return idreader;
    }
    
    public int generateBlogID() {
         LocalDateTime now = LocalDateTime.now();

        long seed = now.getHour() * 10000L +
                    now.getMinute() * 100L +
                    now.getSecond();

        Random random = new Random(seed);
        int idblog = Math.abs(random.nextInt());

        System.out.println("Random Number: " + idblog);
        return idblog;
    }
    
}
