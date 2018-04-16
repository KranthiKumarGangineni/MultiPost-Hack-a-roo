package hackaroo.com.rockerzz;

import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebookConfiguration;

/**
 * Created by gangi on 4/15/2018.
 */

public class FacebookConfiguration {

    Permission[] permissions=new Permission[]{Permission.EMAIL,Permission.USER_PHOTOS,Permission.PUBLISH_ACTION};
    static final String APP_ID="157178584985461";

    public SimpleFacebookConfiguration getMyConfigs()
    {
        SimpleFacebookConfiguration configs=new SimpleFacebookConfiguration.Builder()
                .setAppId(APP_ID)
                .setNamespace("publishfeed")
                .setPermissions(permissions)
                .build();

        return configs;
    }
}
