package ga.dqrjz.marking.exception;

/**
 * 用户名不能为空异常
 */
public class UserNotFoundException extends Exception {
    private static final long serialVersionUID = -7655737905958568447L;
    
    public UserNotFoundException(){

    }
    public UserNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
