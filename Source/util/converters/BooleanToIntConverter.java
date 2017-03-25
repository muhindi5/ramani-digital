/*
 *  Bitforge Software Labs
 *  (c)2017 
 *  http://bitforge.co.ke
 *  <muhindi@bitforge.co.ke><muhindi09@gmail.com>
 */

package util.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @project: ramani-digital
 * @author kelly
 * @date Mar 22, 2017 11:04:06 PM
 * 
 */

@FacesConverter("util.converters.BooleanToIntConverter")
public class BooleanToIntConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(!value.matches("true")) {
        System.out.println("Called converter");
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        System.out.println("Called converter");
        if(value.equals(1)) return "true";
        return "false";
        
    }

}
