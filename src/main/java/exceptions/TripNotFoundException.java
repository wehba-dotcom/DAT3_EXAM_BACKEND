/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author jobe
 */
public class TripNotFoundException extends Exception {
    public TripNotFoundException(int i, String message) {
        super(message);
    }
}

