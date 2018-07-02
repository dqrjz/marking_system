package ga.dqrjz.marking.exception;

/**
 * 用户名不能为空异常
 */
public class UserExistsException extends Exception {
    private static final long serialVersionUID = 6397516340074265158L;
    
    public UserExistsException(){

    }
    public UserExistsException(String errorMsg){
        super(errorMsg);
    }
}
