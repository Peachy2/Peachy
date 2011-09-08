package yetanotherx.peachy.event;

import java.util.HashMap;

public class Event {

    protected Object returned;
    protected boolean processed;
    protected Object subject;
    protected String name;
    protected HashMap<String, Object> parameters;

    public Event(Object subject, String name, HashMap<String, Object> parameters) {
        this.subject = subject;
        this.name = name;
        this.parameters = parameters;
    }
    
    public Event(Object subject, String name) {
        this.subject = subject;
        this.name = name;
        this.parameters = new HashMap<String, Object>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(HashMap<String, Object> parameters) {
        this.parameters = parameters;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public Object getReturned() {
        return returned;
    }

    public void setReturned(Object returned) {
        this.returned = returned;
    }

    public Object getSubject() {
        return subject;
    }

    public void setSubject(Object subject) {
        this.subject = subject;
    }
}
