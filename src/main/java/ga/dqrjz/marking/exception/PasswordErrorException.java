package ga.dqrjz.marking.exception;

/**
 * 用户名不能为空异常
 */
public class PasswordErrorException extends Exception {
    private static final long serialVersionUID = -2531621628141451209L;
    
    public PasswordErrorException(){

    }
    public PasswordErrorException(String errorMsg){
        super(errorMsg);
    }
}
