package by.bsuir.blog.service.util;

import by.bsuir.blog.service.exception.ValidationException;

public class ValidationUtil {

    public static <T> void isPresented(T t) throws ValidationException {
        if (t == null)
            throw new ValidationException("null refrence");
    }

    public static void isZeroOrLess(long x) throws ValidationException {
        if (x <= 0)
            throw new ValidationException("less or equal zero");
    }

    public static void isZeroLength(String str) throws ValidationException {
        ValidationUtil.isPresented(str);
        if (str.length() == 0)
            throw new ValidationException("zero legth");
    }

    public static void isValidEmail(String email) throws ValidationException {
        ValidationUtil.isZeroLength(email);
        
    }

    public static void isValidPassword(String passwd) throws ValidationException {
        ValidationUtil.isZeroLength(passwd);
    }

    public static void isValidLogin(String login) throws ValidationException {
        ValidationUtil.isZeroLength(login);
    }
}
