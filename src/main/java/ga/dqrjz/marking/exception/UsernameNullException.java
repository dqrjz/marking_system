package ga.dqrjz.marking.exception;

/**
 * 用户名不能为空异常
 */
public class UsernameNullException extends Exception {
    private static final long serialVersionUID = -8539380442143530591L;
    
    public UsernameNullException(){

    }
    public UsernameNullException(String errorMsg){
        super(errorMsg);
    }
}
