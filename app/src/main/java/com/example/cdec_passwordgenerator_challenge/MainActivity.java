package com.example.cdec_passwordgenerator_challenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import java.util.Random;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.slider.Slider;


public class MainActivity extends AppCompatActivity {
    private TextView passwordOutPutText,Length;
    private ImageView copyBTN;
    private CheckBox Random,Uppercase,Lowercase,Numbers,Symbols;
    private Animation show,hide,vibrate;
    private Slider slider;
    private CardView GenerateBTN;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Inisialization
        InisializationOfFealds();
        //show and hide the options
        Random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Random.isChecked()){
                    setUppercaseHide();
                    setLowercaseHide();
                    setNumbersHide();
                    setSymbolsHide();
                }else {
                    Uppercase.setChecked(false);
                    Lowercase.setChecked(false);
                    Numbers.setChecked(false);
                    Symbols.setChecked(false);
                    setUppercaseShow();
                    setLowercaseShow();
                    setNumbersShow();
                    setSymbolsShow();
                }
            }
        });
        //Generate Random Password By clicking GenerateBTN
        GenerateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Generate Random Password
                String pass = generateRandomPassword(Integer.parseInt(Length.getText().toString()));
                //Display Password
                passwordOutPutText.setText(pass);
            }
        });
        //Generate Random Password By changing slider value
        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                //Display The Length
                Length.setText(String.valueOf((int) value));
                //Generate Random Password
                String pass = generateRandomPassword((int) value);
                //Display Password
                passwordOutPutText.setText(pass);
            }
        });
        //Copy password to clipboard
        copyBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((passwordOutPutText.getText().toString().isEmpty())
                        || passwordOutPutText.getText().toString().equals("choose an option")){
                    Toast.makeText(getApplicationContext(), "Generate password",Toast.LENGTH_SHORT).show();
                    GenerateBTN.startAnimation(vibrate);
                }else  {
                    myClip = ClipData.newPlainText("text", passwordOutPutText.getText().toString());
                    myClipboard.setPrimaryClip(myClip);
                    Toast.makeText(getApplicationContext(), "Text Copied",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void InisializationOfFealds(){
        passwordOutPutText = findViewById(R.id.passwordOutPutText);
        Length = findViewById(R.id.Length);
        Random = findViewById(R.id.Random);
        Uppercase = findViewById(R.id.Uppercase);
        Lowercase = findViewById(R.id.Lowercase);
        Numbers = findViewById(R.id.Numbers);
        Symbols = findViewById(R.id.Symbols);
        slider = findViewById(R.id.slider);
        copyBTN = findViewById(R.id.copyBTN);
        GenerateBTN = findViewById(R.id.GenerateBTN);
        show = AnimationUtils.loadAnimation(this, R.anim.show);
        hide = AnimationUtils.loadAnimation(this, R.anim.hide);
        vibrate = AnimationUtils.loadAnimation(this, R.anim.vibrate);
        myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
    }
    private String generateRandomPassword(int length) {
        if (Random.isChecked()){
            String randomCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789()_-+=<>/{}~|!@#$%^&*?";
            StringBuilder result = new StringBuilder(length);
            Random random = new Random();
            for(int i=result.length();i < length;++i){
                result.append(randomCharacters.charAt(random.nextInt(randomCharacters.length()-1)));
            }
            return result.toString();
        }else if((Uppercase.isChecked()) || (Lowercase.isChecked()) || (Numbers.isChecked())|| (Symbols.isChecked())) {
            String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String lowerCase = "abcdefghijklmnopqrstuvwxyz";
            String numbers = "0123456789";
            String symbols = "()_-+=<>/{}~|!@#$%^&*?";
            String passwordChars = "";

            Random random = new Random();
            StringBuilder result = new StringBuilder(length);

            if(Uppercase.isChecked()) {
                passwordChars += upperCase;
                result.append(upperCase.charAt(random.nextInt(upperCase.length()-1)));
            }

            if(Lowercase.isChecked()) {
                passwordChars += lowerCase;
                result.append(lowerCase.charAt(random.nextInt(lowerCase.length()-1)));
            }

            if(Numbers.isChecked()) {
                passwordChars += numbers;
                result.append(numbers.charAt(random.nextInt(numbers.length()-1)));
            }

            if(Symbols.isChecked()) {
                passwordChars += symbols;
                result.append(symbols.charAt(random.nextInt(symbols.length()-1)));
            }

            for(int i=result.length();i < length;++i){
                result.append(passwordChars.charAt(random.nextInt(passwordChars.length())));
            }
            return  result.toString();
        }else{
            Random.startAnimation(vibrate);
            Symbols.startAnimation(vibrate);
            Numbers.startAnimation(vibrate);
            Lowercase.startAnimation(vibrate);
            Uppercase.startAnimation(vibrate);
            Toast.makeText(getApplicationContext(), "choose an option",Toast.LENGTH_SHORT).show();
            return "choose an option";
        }

    }
    public void setUppercaseHide() {
        Uppercase.startAnimation(hide);
        Uppercase.setClickable(false);
        Uppercase.setVisibility(View.GONE);

    }
    public void setLowercaseHide() {
        Lowercase.startAnimation(hide);
        Lowercase.setClickable(false);
        Lowercase.setVisibility(View.GONE);

    }
    public void setNumbersHide() {
        Numbers.startAnimation(hide);
        Numbers.setClickable(false);
        Numbers.setVisibility(View.GONE);

    }
    public void setSymbolsHide() {
        Symbols.startAnimation(hide);
        Symbols.setClickable(false);
        Symbols.setVisibility(View.GONE);

    }
    public void setUppercaseShow() {
        Uppercase.startAnimation(show);
        Uppercase.setClickable(true);
        Uppercase.setVisibility(View.VISIBLE);

    }
    public void setLowercaseShow() {
        Lowercase.startAnimation(show);
        Lowercase.setClickable(true);
        Lowercase.setVisibility(View.VISIBLE);
    }
    public void setNumbersShow() {
        Numbers.startAnimation(show);
        Numbers.setClickable(true);
        Numbers.setVisibility(View.VISIBLE);
    }
    public void setSymbolsShow() {
        Symbols.startAnimation(show);
        Symbols.setClickable(true);
        Symbols.setVisibility(View.VISIBLE);
    }
}
