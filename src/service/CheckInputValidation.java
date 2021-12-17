package service;

import exceptions.InvalidEmailExp;
import exceptions.InvalidNameExp;

import java.util.regex.Pattern;

public class CheckInputValidation {
    public static boolean checkName(String name) {
        if (name.length() < 2) {
            throw new InvalidNameExp("length of name < 2 !! ");
        }
        if (!(Pattern.matches("[a-zA-Z]{2,20}", name))) {
            throw new InvalidNameExp("there are a number or unauthorized character ");

        }
        return true;
    }

    public static boolean checkEmail(String email) {
        if (!email.contains(".") && !email.contains("@")) {
            throw new InvalidEmailExp("format email not valid");
        }
        return true;
    }
}
