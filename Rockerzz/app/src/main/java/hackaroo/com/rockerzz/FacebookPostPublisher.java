package hackaroo.com.rockerzz;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.entities.Feed;
import com.sromku.simple.fb.listeners.OnPublishListener;

/**
 * Created by gangi on 4/15/2018.
 */

public class FacebookPostPublisher {

    Context context;
    SimpleFacebook simpleFacebook;

    public FacebookPostPublisher(Context context, SimpleFacebook simpleFacebook) {
        this.context = context;
        this.simpleFacebook = simpleFacebook;
    }

    OnPublishListener onPublishListener = new OnPublishListener() {
        @Override
        public void onComplete(String response) {
            super.onComplete(response);
            Toast.makeText(context,"Successfully Published",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onException(Throwable throwable) {
            super.onException(throwable);
            Log.e("Exception",throwable.getMessage());
        }

        @Override
        public void onFail(String reason) {
            super.onFail(reason);
            Log.i("Failed",reason);
        }

        @Override
        public void onThinking() {
            super.onThinking();
            Toast.makeText(context,"Thinking",Toast.LENGTH_SHORT).show();
        }
    };

    public  void publishFeed(FacebookPost fbPost){
        Feed feed = new Feed.Builder()
                .setName(fbPost.getName())
                .setMessage(fbPost.getMessage())
                .setDescription(fbPost.getDescription())
                .setLink(fbPost.getLink())
                .setPicture(fbPost.getImageURL())
                .build();

        simpleFacebook.publish(feed,onPublishListener);
    }
}
