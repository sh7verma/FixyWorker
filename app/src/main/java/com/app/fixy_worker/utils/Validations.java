package com.app.fixy_worker.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;


import com.app.fixy_worker.R;
import com.app.fixy_worker.customviews.FloatingEditText;
import com.app.fixy_worker.customviews.MaterialEditText;
import com.app.fixy_worker.fragments.BaseFragment;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by app on 28-Nov-17.
 */

public class Validations {

    public static boolean checkEmailValidation(Context con, MaterialEditText editText) {
        String email =editText.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(TextUtils.isEmpty(email)){
            editText.setError(con.getString(R.string.email_not_empty));
            return false;        }
        else  if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editText.setError(con.getString(R.string.email_not_valid));
            return false;
        }
        else {
            return true;
        }
    }

    public static boolean checkPasswordValidation(Context con, MaterialEditText editText) {
        String pass =editText.getText().toString().trim();
//        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Za-z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        if(TextUtils.isEmpty(pass)){
            editText.setError(con.getString(R.string.password_empty));
            return false;
        }
        else  if (pass.length() <= 7){
            editText.setError(con.getString(R.string.password_min_char));
            return false;
        }
        else {
            return true;
        }
    }
    public static boolean checkPhoneValidation(Context con, MaterialEditText editText) {
        String text =editText.getText().toString().trim();
        if(TextUtils.isEmpty(text)){
//            editText.setError(con.getString(R.string.enter_phone_number));
            Dialogs.showPopup(con,editText,con.getString(R.string.enter_phone_number));
            return false;        }
        else  if (text.length() < 5){
            Dialogs.showPopup(con,editText,con.getString(R.string.invalid_phone_number));
//            editText.setError(con.getString(R.string.invalid_phone_number));
            return false;
        }
        else  if (text.startsWith("0")){
            Dialogs.showPopup(con,editText,con.getString(R.string.start_with_zero));
//            editText.setError(con.getString(R.string.start_with_zero));
            return false;
        }
        else {
            return true;
        }
    }

    public static boolean checkOTPValidation(Context con, MaterialEditText editText) {
        String text =editText.getText().toString().trim();
        if(TextUtils.isEmpty(text)){
//            editText.setError(con.getString(R.string.enter_otp));
            Dialogs.showPopup(con,editText,con.getString(R.string.enter_otp));
            return false;
        }
        else {
            return true;
        }
    }
    public static String checkNameValidation(Context con, FloatingEditText editText) {
        String text =editText.getText().toString().trim();
//        String valiation ="[a-zA-Z0-9._@ -]+";
        String valiation ="[a-zA-Z0-9 -]+";
        String digit ="[0-9]+";
        if(TextUtils.isEmpty(text)){
//            editText.setError(con.getString(R.string.full_name_empty));
            return con.getString(R.string.full_name_empty);
        }
        else if (!text.matches(valiation)){
            editText.setError(con.getString(R.string.not_alphabet));
            return con.getString(R.string.full_name_empty);
        }
        else if (text.length() <= 1){
            editText.setError(con.getString(R.string.fullname_length));
            return con.getString(R.string.full_name_empty);
        }else if (text.matches(digit)){
            editText.setError(con.getString(R.string.not_alphabet));
            return con.getString(R.string.full_name_empty);
        }
        else {
            return "";
        }
    }
    public static String checkDocNameValidation(Context con, FloatingEditText editText) {
        String text =editText.getText().toString().trim();
//        String valiation ="[a-zA-Z0-9._@ -]+";
        String valiation ="[a-zA-Z0-9 -]+";
        String digit ="[0-9]+";
        if(TextUtils.isEmpty(text)){
//            editText.setError(con.getString(R.string.full_name_empty));
            return con.getString(R.string.doc_name_empty);
        }
        else if (!text.matches(valiation)){
            editText.setError(con.getString(R.string.not_alphabet));
            return con.getString(R.string.doc_name_invalid);
        }
        else if (text.length() <= 1){
            editText.setError(con.getString(R.string.fullname_length));
            return con.getString(R.string.doc_name_invalid);
        }else if (text.matches(digit)){
            editText.setError(con.getString(R.string.not_alphabet));
            return con.getString(R.string.doc_name_invalid);
        }
        else {
            return "";
        }
    }
    public static boolean checkBdayValidation(Context con, MaterialEditText editText) {
        String text =editText.getText().toString().trim();
        if(TextUtils.isEmpty(text)){
            editText.setError(con.getString(R.string.set_your_bday));
            return false;
        }
        else {
            return true;
        }
    }
    public static boolean checkCountryValidation(Context con, MaterialEditText editText) {
        String text =editText.getText().toString().trim();
        if(TextUtils.isEmpty(text)){
            editText.setError(con.getString(R.string.choose_a_country));
            return false;
        }
        else {
            return true;
        }
    }
    public static boolean checkCardNumberValidation(Context con, MaterialEditText editText, LinearLayout llCardNumber) {
        String text =editText.getText().toString().trim();
//        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Za-z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        if(TextUtils.isEmpty(text)){
            showAlert(llCardNumber,con.getString(R.string.error_card_no));
            return false;
        }
        else  if (!validateCardNumber(text)){
            showAlert(llCardNumber,con.getString(R.string.error_valid_card_no));
            return false;
        }
        else {
            return true;
        }
    }
    public static boolean checkCardMonthYearValidation(Context con, MaterialEditText edmonth,
                                                       MaterialEditText edyear, LinearLayout llExpiryDate) {
        String month =edmonth.getText().toString().trim();
        String year =edyear.getText().toString().trim();

//        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Za-z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        if(TextUtils.isEmpty(month)){
            showAlert(llExpiryDate,con.getString(R.string.error_month));
            return false;
        }
        else if (month.length()<2){
            showAlert(llExpiryDate,con.getString(R.string.invalid_month));
            return false;
        }
        else if(TextUtils.isEmpty(year)){
            showAlert(llExpiryDate,con.getString(R.string.error_year));
            return false;
        }
        else if (!validateExpiryDate(month + " / " + year)) {
            showAlert(llExpiryDate,con.getString(R.string.invalid_expiry_date));
            return false;
        }
        else {
            return true;
        }
    }
    static boolean validateCardNumber(String mCardNumber) { // length check to 20 because spaces are also included
        String mNumber = (mCardNumber.replaceAll(" ", ""));
        Pattern pattern = Pattern.compile("^[0-9]{12,19}$");
        Matcher matcher = pattern.matcher(mNumber);
        if (matcher.matches()) {
            return validateCardNumber_LuhnAlgo(mNumber);
        } else {
            return false;
        }
    }

    static boolean validateCardNumber_LuhnAlgo(String mCardNumber) {
        char[] digitsChar = mCardNumber.toCharArray();
        int total = 0, flag = 0, digit;
        for (int i = digitsChar.length - 1; i >= 0; i--) {
            flag += 1;
            digit = Integer.parseInt("" + digitsChar[i]);
            if (flag % 2 == 0) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = digit - 9;
                }
            }
            total = total + digit;
        }

        if (total % 10 == 0) {
            return true;
        } else {
            return false;
        }
    }

    static boolean validateExpiryDate(String expiryDate) {
        if (expiryDate.length() > 3) { //is expiry field even filled once
            String[] startEnd = expiryDate.split(" / ");
            if (startEnd.length == 2) { //both month and year are filled
                Pattern pattern = Pattern.compile("^[0-9]{2}$");
                for (int i = 0; i < startEnd.length; i++) {
                    Matcher matcher = pattern.matcher(startEnd[i]);
                    if (!matcher.matches()) {
                        return false;
                    }
                    if (i == 0) { //month should not be less than / equal to 0 or greater than 12
                        if (Integer.parseInt(startEnd[0]) <= 0 ||
                                Integer.parseInt(startEnd[0]) > 12) {
                            return false;
                        }
                    }
                }

                Calendar currentCal = Calendar.getInstance();
                Log.e("current month", "is " + (currentCal.get(Calendar.MONTH)));
                if (Integer.parseInt("20" + startEnd[1]) > currentCal.get(Calendar.YEAR)) { // if year is greater than current year. all cool
                    return true;
                } else if (Integer.parseInt("20" + startEnd[1]) == currentCal.get(Calendar.YEAR)) { //if year is same as my current year, please check month
                    if (Integer.parseInt(startEnd[0]) >= (currentCal.get(Calendar.MONTH) + 1)) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }

            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean checkConfirmNewPasswordValidation(Context con, MaterialEditText oldPass, MaterialEditText newPass, MaterialEditText confirmPass) {
        String pass =oldPass.getText().toString().trim();
        String newpass =newPass.getText().toString().trim();
        String confirmpass =confirmPass.getText().toString().trim();
//        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Za-z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        if(TextUtils.isEmpty(pass)){
            oldPass.setError(con.getString(R.string.password_empty));
            oldPass.requestFocus();
            return false;
        }
        else  if(TextUtils.isEmpty(newpass)){
            newPass.setError(con.getString(R.string.password_empty));
            newPass.requestFocus();
            return false;
        }
        else  if(TextUtils.isEmpty(confirmpass)){
            confirmPass.setError(con.getString(R.string.password_empty));
            confirmPass.requestFocus();
            return false;
        }
        else  if (newpass.length() <= 7){
            newPass.setError(con.getString(R.string.password_min_char));
            newPass.requestFocus();
            return false;
        }
        else  if (!TextUtils.equals(newpass,confirmpass)){
            confirmPass.setError(con.getString(R.string.confirm_password_validation));
            confirmPass.requestFocus();
            return false;
        }
        else {
            return true;
        }
    }
    public static void showAlert(View view, String mess) {
       Snackbar mSnackbar = Snackbar.make(view, mess, Snackbar.LENGTH_SHORT);
        mSnackbar.show();
    }

    public static String checkChangeEmailValidation(Context con, FloatingEditText editText) {
        String email =editText.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(TextUtils.isEmpty(email)){
//            editText.setError(con.getString(R.string.email_not_empty));
            return con.getString(R.string.email_not_empty);        }
        else  if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            editText.setError(con.getString(R.string.email_not_valid));
            return con.getString(R.string.email_not_valid);
        }
        /*else  if (!sp.getString(Consts.EMAIL,"").equalsIgnoreCase(editText.getText().toString())){
//            editText.setError(con.getString(R.string.current_email_not_valid));
            return false;
        }*/
        else {
            return "";
        }
    }
}
