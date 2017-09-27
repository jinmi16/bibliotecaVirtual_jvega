package bv.converter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "trimConverter")
public class TrimConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value == null || value.equals("")){
            return "";
        }
        return value.trim();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
         return value.toString();
    }
    
}
