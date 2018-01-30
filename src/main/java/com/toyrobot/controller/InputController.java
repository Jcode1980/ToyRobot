package com.toyrobot.controller;


/**
 * This interface handles the input stream to be feed into the <code>BoardController</code>.
 * Input being read is translated and mapped to methods called on the <code>BoardController</code>.
 *
 */
public interface InputController {
    /**
     * Starts reading the Standard input stream.
     *
     */


    public void processCommandFile();
}
