package hackaroo.com.rockerzz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * Created by gangi on 4/13/2018.
 */

public class RegistrationActivity extends AppCompatActivity{

    EditText regUserName;
    EditText regEmailAddress;
    EditText regPassword;
    EditText regConfirmPassword;

    TextView regMessage;
    // Database Helper
    DatabaseHelper dbHelper;

    // Email Address RegExpression
    private static final String EMAIL_PATTERN = "^(.+)@(.+)$";

    ProgressDialog progressDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        dbHelper = new DatabaseHelper(this);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        regUserName = findViewById(R.id.regusername);
        regEmailAddress = findViewById(R.id.regemailaddress);
        regPassword = findViewById(R.id.regpassword);
        regConfirmPassword = findViewById(R.id.regconfirmpassword);
        regMessage = findViewById(R.id.regmessage);
        progressDialog = new ProgressDialog(this);

         }

         //On click on login button
         public void loginredirect(View view){
            Intent loginRedirect = new Intent(RegistrationActivity.this,LoginActivity.class);
            startActivity(loginRedirect);
         }

        //On reset button clcik
        public void resetfields(View view){
             regUserName.setText("");
             regEmailAddress.setText("");
             regPassword.setText("");
             regConfirmPassword.setText("");
             regMessage.setText("");
        }

        //On Register Button Click
        public void register(View view){
            //Basic validation for text fields
            if (!(isFieldNotNull(regUserName)
                    && isFieldNotNull(regEmailAddress) && isFieldNotNull(regPassword)
                    && isFieldNotNull(regConfirmPassword))) {
                regMessage.setText("Please fill all the fields, fields cannot be empty");
                regMessage.setVisibility(View.VISIBLE);
            }else if(!Pattern.matches(EMAIL_PATTERN, regEmailAddress.getText().toString())){
                regMessage.setText("Please enter valid email address, EX: Hackaroo@mail.com");
                regMessage.setVisibility(View.VISIBLE);
            }else if (!regPassword.getText().toString().equals(regConfirmPassword.getText().toString())) {
                regMessage.setText("Password & Confirm passwords should match");
                regMessage.setVisibility(View.VISIBLE);
            }else if(regPassword.getText().toString().length() < 6){
                regMessage.setText("Password lenght should be more thatn 5");
                regMessage.setVisibility(View.VISIBLE);
            }else{

                progressDialog.setMessage("Registering User");
                progressDialog.show();

                // Checking If User already exist
                Cursor userDetail = dbHelper.checkIfUserExist(regEmailAddress.getText().toString());
                if(userDetail.getCount() == 0){
                    boolean isInsertSuccessful = dbHelper.insertData(regUserName.getText().toString(), regEmailAddress.getText().toString(),
                            regPassword.getText().toString());
                    // If Insertion is Successful, return proper message to let the User know
                    // that he got signed Up Successfully
                    if (isInsertSuccessful) {
                        // Setting Successful Message
                        regMessage.setText("You '" + regEmailAddress.getText().toString() + "' have successfully signed up, LOGIN to continue");
                        regMessage.setVisibility(View.VISIBLE);
                    }else{
                        // Setting Successful Message
                        regMessage.setText("SignUp is unsuccessful due to some technical Issues, Please try again later");
                        regMessage.setVisibility(View.VISIBLE);
                    }
                }else {
                    // If User Already Exist
                    // Throwing Error
                    regMessage.setText("You '" + regEmailAddress.getText().toString() + "' have already signed up, Please LOGIN to continue");
                    regMessage.setVisibility(View.VISIBLE);
                }

                progressDialog.hide();

                // Keep a Toast to say registration is successful.
            }
        }

        //StringUtils empty
    private boolean isFieldNotNull(EditText fieldValue){
            if(fieldValue != null && StringUtils.isNotBlank(fieldValue.getText().toString())){
                return true;
            }
            return false;
    }
}
