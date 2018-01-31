package com.toyrobot.controller;


/**
 * This interface handles the input stream to be feed into the <code>BoardController</code>.
 * Input being read is translated and mapped to methods called on the <code>BoardController</code>.
 *
 */
public interface InputController {
    /**
     * Starts reading the file specified on the command line else
     * if no filepath was passed it, it will read from the default
     * example commands file.
     *
     */
    void processCommandFile();
}
