package rodhis.java.api.handler;

public class MandatoryFieldException extends BusinessException{

    public MandatoryFieldException(String field) {
        super("O campo %s é obrigatório!", field);
    }
}
