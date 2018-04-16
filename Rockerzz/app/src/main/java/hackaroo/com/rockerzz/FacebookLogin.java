package hackaroo.com.rockerzz;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.listeners.OnLoginListener;

import java.util.List;

/**
 * Created by gangi on 4/15/2018.
 */

public class FacebookLogin {

    SimpleFacebook simpleFacebook;
    Context context;
    FacebookPost facebookPost;

    public FacebookLogin(SimpleFacebook simpleFacebook, Context context, FacebookPost facebookPost) {
        this.simpleFacebook = simpleFacebook;
        this.context = context;
        this.facebookPost = facebookPost;
    }

    OnLoginListener onLoginListener = new OnLoginListener() {
        @Override
        public void onLogin(String accessToken, List<Permission> acceptedPermissions, List<Permission> declinedPermissions) {
            Toast.makeText(context,"Successfully Logged In",Toast.LENGTH_SHORT).show();
            new FacebookPostPublisher(context,simpleFacebook).publishFeed(facebookPost);
        }

        @Override
        public void onCancel() {
            Toast.makeText(context,"Cancelled",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onException(Throwable throwable) {
            Log.e("Exception",throwable.getMessage());
        }

        @Override
        public void onFail(String reason) {
        Log.i("Failed",reason);
        }
    };

    public void login(){
        simpleFacebook.login(onLoginListener);
    }
}
