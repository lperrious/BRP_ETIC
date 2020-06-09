package fr.etic.brp.brp_front_end.actions;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Quentin Marc & Louis ROB
 */
public abstract class Action {

    public abstract void execute(HttpServletRequest request);
    
}