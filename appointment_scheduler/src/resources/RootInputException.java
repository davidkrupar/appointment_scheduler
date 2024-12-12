package resources;

public class RootInputException extends Exception {

    String s;

    public RootInputException(String s) {
        this.s = s;

    }

    public String toString() {
        return s;
    }
}

