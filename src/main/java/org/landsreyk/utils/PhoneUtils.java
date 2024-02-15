package org.landsreyk.utils;

import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class PhoneUtils {
    private static final Pattern PATTERN = Pattern.compile("^(\\+?\\d+( )?)?((\\(\\d+\\))|\\d+)[- .]?\\d+[- .]?\\d+$");

    public static boolean isValidPhoneNumber(String phoneNumber) {
        Matcher matcher = PATTERN.matcher(phoneNumber);
        return matcher.matches();
    }
}
