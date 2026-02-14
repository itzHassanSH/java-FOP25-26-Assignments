package h06;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

/**
 * A smartphone with an electric battery
 */
public class Smartphone implements ElectricallyPowered {

    @DoNotTouch
    private final TokenDictionary tokenDictionary;
    @DoNotTouch
    private final MessageReceiver messageReceiver;
    private final PlugType plugType = PlugType.USB;

    /**
     * Creates a new Smartphone
     *
     * @param tokenDictionary a dictionary to translate tokens
     * @param messageReceiver a receiver to receive messages from
     */
    @DoNotTouch
    public Smartphone(TokenDictionary tokenDictionary, MessageReceiver messageReceiver) {
        this.tokenDictionary = tokenDictionary;
        this.messageReceiver = messageReceiver;
    }

    /**
     * replaces a token within a message
     * with a string specified in the {@link Smartphone#tokenDictionary}
     *
     * @param template a string which contains a token
     * @return the message generated from the template
     */
    @StudentImplementationRequired("H6.4.2")
    public String replaceToken(String template) {
        // TODO H6.4.2
        int index1 = template.indexOf('<');
        int index2 = template.indexOf('>');

        String symbol = template.substring(index1, index2+1);
        String newSymbol = tokenDictionary.lookup(symbol);

        template = template.replace(symbol, newSymbol);
        return template;
    }

    @Override
    public void use(int duration) {
        for (int i = 0; i < duration; i++) {
            System.out.println(replaceToken(messageReceiver.nextMessage()));
        }
    }

    @Override
    public PlugType getSupportedPlugType() {
        return plugType;
    }
}
