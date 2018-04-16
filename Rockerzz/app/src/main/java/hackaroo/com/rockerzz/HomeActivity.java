package hackaroo.com.rockerzz;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.listeners.OnLogoutListener;

/**
 * Created by gangi on 4/13/2018.
 */

public class HomeActivity extends AppCompatActivity {

    SimpleFacebook simpleFacebook;
    Context context;

    @Override
    protected void onCreate(Bundle bundleInstance) {

        super.onCreate(bundleInstance);
        setContentView(R.layout.activity_home);

        simpleFacebook = SimpleFacebook.getInstance(this);
    }

    // On Logout Click
    public void logout(View view) {

        Intent loginRedirect = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(loginRedirect);
        OnLogoutListener onLogoutListener = new OnLogoutListener() {
            @Override
            public void onLogout() {
               // Toast.makeText(context,"Logged Out",Toast.LENGTH_LONG).show();
            }
        };
        simpleFacebook.logout(onLogoutListener);
    }

    public void navigateToFacebook(View view){
        Intent fbRedirect = new Intent(HomeActivity.this, FacebookScreen.class);
        startActivity(fbRedirect);
    }

    public void naivgateToTwitter(View view){
        Intent twitterRedirect = new Intent(HomeActivity.this,TwitterLogin.class);
        startActivity(twitterRedirect);
    }
}
