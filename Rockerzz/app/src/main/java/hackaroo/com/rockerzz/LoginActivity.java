package hackaroo.com.rockerzz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import org.apache.commons.lang3.StringUtils;

public class LoginActivity extends AppCompatActivity {

    EditText emailText;
    EditText passwordText;
    TextView errorText;

    ProgressDialog progressDialog;

    // Database Helper
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText = findViewById(R.id.nameEditText);
        passwordText = findViewById(R.id.loginpassword);
        errorText = findViewById(R.id.loginmessage);

        dbHelper = new DatabaseHelper(this);
        progressDialog = new ProgressDialog(this);
    }
        //on click login

    public void login(View view){
        progressDialog.setMessage("Signing in...");
        progressDialog.show();
        if(!(isFieldNotNull(emailText) && isFieldNotNull(passwordText))){
            // Throwing Error if any of the field is empty
            errorText.setText("Email Address or Password fields cannot be Empty !!");
            errorText.setVisibility(View.VISIBLE);
        }else{

            Cursor userDetail = dbHelper.checkIfUserExist(emailText.getText().toString());
            if(userDetail.getCount() == 0){
                // Throw Error Saying Invalid Email, SignUp to Continue
                errorText.setText("You "+emailText.getText().toString()+" haven't signedup yet, Please signup for Smart" +
                        "Shopping");
                errorText.setVisibility(View.VISIBLE);
            }else{
                // If User Mail Exist, Check if Password is correct
                Cursor user = dbHelper.checkIfPasswordIsCorrect(emailText.getText().toString(),
                        passwordText.getText().toString());
                if(user.getCount() == 0){
                    // Throw Error for Wrong Password
                    errorText.setText("Invalid Password for "+ emailText.getText().toString() +", Please try with Valid One");
                    errorText.setVisibility(View.VISIBLE);
                }else{
                    while(user.moveToNext()) {
                        Intent pageRedirect = new Intent(LoginActivity.this, HomeActivity.class);
                        pageRedirect.putExtra("emailId",user.getString(3));
                        startActivity(pageRedirect);
                    }
                }
            }
        }
        progressDialog.hide();
    }


    // Common Method to check for NULL
    private boolean isFieldNotNull(EditText fieldValue) {
        if (fieldValue != null && StringUtils.isNotBlank(fieldValue.getText().toString())) {
            return true;
        }
        return false;
    }

    // On Click of Register
    public void register(View view) {
        Intent registerActivityRedirect = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(registerActivityRedirect);
    }
}
