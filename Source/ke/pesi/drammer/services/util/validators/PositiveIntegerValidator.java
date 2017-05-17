/*
 *  Bitforge Software Labs
 *  (c)2017 
 *  http://bitforge.co.ke
 *  <muhindi@bitforge.co.ke><muhindi09@gmail.com>
 */
package ke.pesi.drammer.services.util.validators;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @project: ramani-digital
 * @author kelly
 * @date May 16, 2017 8:40:20 PM
 *
 * Validator for positive integer values in form 'add_product.xhtml'
 */
@FacesValidator
public class PositiveIntegerValidator implements javax.faces.validator.Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {
        Long input = (Long)value;
        Logger.getAnonymousLogger().log(Level.INFO,"input == {0}",input);
        Logger.getAnonymousLogger().log(Level.INFO,value.getClass().getCanonicalName());
        try {
            if (input <= 0) {
                FacesMessage message = new FacesMessage("Invalid value: '0' in: "+component.getId());
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(message);
            }
        }
            catch(NumberFormatException exception){
                FacesMessage message = new FacesMessage("Value entered not a number");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(message);
                    }

    }

}
