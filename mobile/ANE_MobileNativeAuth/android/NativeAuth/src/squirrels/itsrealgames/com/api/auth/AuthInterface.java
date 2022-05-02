package squirrels.itsrealgames.com.api.auth;

import android.content.Intent;
/**
 * ...
 * @author Sprinter
 */
public interface AuthInterface {

	public abstract void login(String[] params);
	
	public abstract void logout();
	
	public abstract void onSuccess(String token);

	public abstract void onFail(String msg);

	public abstract void getUser();
	
	public abstract void onGetUser(String response);
	
	public abstract Boolean isLoggedIn();
	
	public abstract void onResume();
	
	public abstract void onDestroy();
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent);
	
}