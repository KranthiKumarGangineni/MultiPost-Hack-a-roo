package hackaroo.com.rockerzz;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterScreen extends AppCompatActivity {

    EditText nameText;
    EditText messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_to_twitter);

        nameText = findViewById(R.id.twitterName);
        messageText = findViewById(R.id.twitterMessage);

        if(getIntent() != null){
            String name = getIntent().getStringExtra("username");
            nameText.setText(name);
        }
    }

    public void postToTwitter(View view) {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();

        configurationBuilder.setOAuthConsumerKey("pd40ExgMwyR3asSuScMzt2JQ3");

        configurationBuilder.setOAuthConsumerSecret("Z8AcNcBi7nfPwGblipIBs5mveTVkZ2QE8jbzf0Dvjd0c9gpZUh");

        configurationBuilder.setOAuthAccessToken("864118438112796673-te5qYD8G7w6ucAag6ANVIfhv3ZMpyuY");

        configurationBuilder.setOAuthAccessTokenSecret("MaQgCbUb8BR3wFMm4hn9egFog0fIwWdDW1OEqPjEyF8A2");

        Configuration configuration = configurationBuilder.build();

        final Twitter twitter = new TwitterFactory(configuration).getInstance();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String message = messageText.getText().toString();
                        twitter.updateStatus(message);
                    }catch (TwitterException e) {
                        e.printStackTrace();
                    }
                }
            });
    }
}
