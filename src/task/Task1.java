package task;

import exception.IncorrectTaskParameterException;
import util.Constant;

import java.time.LocalDateTime;

public class Task1 {

    private static int idGenerator= 1;


    private int id;
    private String titel;
    private String description;
    private Type type;
    private LocalDateTime dateTime;

    private Repiatability repiatability;

    public Task1(String titel, String description, Type type, LocalDateTime dateTime,
                 Repiatability repiatability) {
        id=idGenerator++;
        try {
            setTitel(titel);
        } catch (IncorrectTaskParameterException e) {
            throw new RuntimeException(e);
        }
        try {
            setDescription(description);
        } catch (IncorrectTaskParameterException e) {
            throw new RuntimeException(e);
        }
        setType(type);
        try {
            setDateTime(dateTime);
        } catch (IncorrectTaskParameterException e) {
            throw new RuntimeException(e);
        }
        setRepiatability(repiatability);
    }

    public int getId() {
        return id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) throws IncorrectTaskParameterException {
        if ( titel==null || titel.isEmpty()){
            throw new IncorrectTaskParameterException(" заголовок задач ");
        }
        this.titel=titel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws IncorrectTaskParameterException {
        if ( description==null || description.isEmpty()){
            throw new IncorrectTaskParameterException(" описание задач ");
        }
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        if ( type==null){
            type=Type.PERSONAL;
        }
        this.type = type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) throws IncorrectTaskParameterException {
        if ( dateTime==null){
            throw new IncorrectTaskParameterException(" дата и время задачи ");
        }

        this.dateTime = dateTime;
    }

    public Repiatability getRepiatability() {
        return repiatability;
    }

    public void setRepiatability(Repiatability repiatability) {
        if ( repiatability==null){
            repiatability=new Once();
        }
        this.repiatability = repiatability;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("id: ").append(id).append("\n")
                .append("название:").append("\"").append(titel).append("\"").append("\n")
                .append(" описание: ").append("\"").append(description).append("\"").append("\n")
                .append(" тип: ").append(type==Type.PERSONAL? "личная ": "рабочая").append("\n")
                .append(" дата и время: ").append(dateTime.format(Constant.DATE_TIME_FORMATTER)).append("\n")
                .append(" повторяемость: ").append(repiatability.titel()).append("\n");
        return stringBuilder.toString();
    }
}

