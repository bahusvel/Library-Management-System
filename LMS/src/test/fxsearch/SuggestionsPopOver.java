package test.fxsearch;

import javafx.scene.Node;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


/**
 * Created by denislavrov on 9/28/14.
 */
public class SuggestionsPopOver extends PopOver {

    public SuggestionsPopOver(Node owner, double duration){
        super(owner);
        try {
            setFinalStatic(PopOver.class.getDeclaredField("DEFAULT_FADE_DURATION"), Duration.seconds(duration));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void setFinalStatic(Field field, Object newValue) throws Exception {
        field.setAccessible(true);

        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.set(null, newValue);
    }

}
