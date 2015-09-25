package cn.commonhelp.application;

import in.srain.cube.util.CLog;
import android.app.Application;
import cn.commonhelp.cache.Cube;
import cn.commonhelp.cache.RequestCacheManager;

public class ApplicationHelp extends Application {

	    public static ApplicationHelp instance;

	    @Override
	    public void onCreate() {
	        super.onCreate();
	        instance = this;

	        String environment = "";

	        if (environment.equals("production")) {
	            CLog.setLogLevel(CLog.LEVEL_ERROR);
	        } else if (environment.equals("beta")) {
	            CLog.setLogLevel(CLog.LEVEL_WARNING);
	        } else {
	            // development
	            CLog.setLogLevel(CLog.LEVEL_VERBOSE);
	        }

	      
	        String dir = "request-cache";
	        // ImageLoaderFactory.init(this);
	        RequestCacheManager.init(this, dir, 1024 * 10, 1024 * 10);
	        Cube.onCreate(this);
	    }
}
