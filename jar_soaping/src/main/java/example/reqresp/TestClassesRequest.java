package example.reqresp;

import java.io.Serializable;

/**
 * Created by Somebody on 06.07.14.
 */
public class TestClassesRequest implements Serializable {

    private static final long serialVersionUID = -8610510086479147061L;

    private String inputMessage;

    public String getInputMessage() {
        return inputMessage;
    }

    public void setInputMessage(String inputMessage) {
        this.inputMessage = inputMessage;
    }

    @Override
    public String toString() {
        return "TestClassesRequest{" +
                "inputMessage='" + inputMessage + '\'' +
                '}';
    }
}
