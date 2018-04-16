package hackaroo.com.rockerzz;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by gangi on 4/15/2018.
 */

public class PicassoClient {

    Context context;
    String imageURL;

    public PicassoClient(Context context, String imageURL) {
        this.context = context;
        this.imageURL = imageURL;
    }

    public boolean downloadImage(ImageView imageView){
        if (StringUtils.isNotEmpty(imageURL)){
            Picasso.with(context).load(imageURL).placeholder(R.drawable.com_facebook_button_background).into(imageView);
            return true;
        }else {
            Picasso.with(context).load(R.drawable.com_facebook_button_background).into(imageView);
            return false;
        }
    }
}
