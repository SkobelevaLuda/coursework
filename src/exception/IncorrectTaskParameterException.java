package exception;

public class IncorrectTaskParameterException extends Exception{

    private String paramrter;

    public IncorrectTaskParameterException(String paramrter) {
        this.paramrter = paramrter;
    }

    @Override
    public String getMessage() {
        return "Параметр "+ paramrter+" задан не верно ";
    }
}
