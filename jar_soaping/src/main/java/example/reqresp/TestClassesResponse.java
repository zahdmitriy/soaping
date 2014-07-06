package example.reqresp;

import java.io.Serializable;

/**
 * Created by Somebody on 06.07.14.
 */
public class TestClassesResponse implements Serializable {

    private static final long serialVersionUID = -3811021109670592568L;

    private String outputMessage;

    public String getOutputMessage() {
        return outputMessage;
    }

    public void setOutputMessage(String outputMessage) {
        this.outputMessage = outputMessage;
    }

    @Override
    public String toString() {
        return "TestClassesResponse{" +
                "outputMessage='" + outputMessage + '\'' +
                '}';
    }
}
