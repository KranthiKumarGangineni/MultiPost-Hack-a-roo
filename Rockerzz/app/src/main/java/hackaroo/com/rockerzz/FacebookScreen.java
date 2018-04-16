package hackaroo.com.rockerzz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.sromku.simple.fb.SimpleFacebook;

/**
 * Created by gangi on 4/15/2018.
 */

public class FacebookScreen extends AppCompatActivity{

    EditText messageText;
    EditText nameText;

    SimpleFacebook simpleFacebook;
    FacebookPost facebookPost;

    @Override
    protected void onCreate(Bundle bundleInstance){

        super.onCreate(bundleInstance);
        setContentView(R.layout.post_to_facebook);

        nameText = findViewById(R.id.nameEditText);
        messageText = findViewById(R.id.fbMessage);

        SimpleFacebook.setConfiguration(new FacebookConfiguration().getMyConfigs());
        simpleFacebook = SimpleFacebook.getInstance(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        simpleFacebook = SimpleFacebook.getInstance(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        simpleFacebook.onActivityResult(requestCode,resultCode,data);
    }

    public void postToFacebook(View view){
        facebookPost = new FacebookPost();
        facebookPost.setName(nameText.getText().toString());
        facebookPost.setMessage(messageText.getText().toString());

        new FacebookLogin(simpleFacebook,FacebookScreen.this,facebookPost).login();
    }
}
