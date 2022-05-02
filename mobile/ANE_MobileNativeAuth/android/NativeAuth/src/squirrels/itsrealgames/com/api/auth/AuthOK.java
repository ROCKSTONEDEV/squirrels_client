package squirrels.itsrealgames.com.api.auth;

import ru.ok.android.sdk.Odnoklassniki;
import ru.ok.android.sdk.util.OkScope;
import ru.ok.android.sdk.OkTokenRequestListener;
import android.app.Activity;
import android.content.Intent;

import com.adobe.fre.FREContext;
/**
 * ...
 * @author Sprinter
 */
public class AuthOK implements AuthInterface {

	private final static String MSG_ERROR_AUTH_CANCELED = "Authorization was canceled";
	private final static String MSG_ERROR_GETTING_TOKEN = "Error getting token";
	
	private Odnoklassniki mOdnoklassniki = null;
	private AuthListener mCallBack = null;
	private Activity mBaseContext = null;
	
	public AuthOK(FREContext context, String appId, AuthListener callBack) {
		appId = "125497344";
		final String appSecret = "E1B27795E3C2AF1A7B14CB11";
		final String appKey = "CBABPLHIABABABABA";
		
		mCallBack = callBack;
		mBaseContext = context.getActivity();
		
		mOdnoklassniki = Odnoklassniki.createInstance(mBaseContext, appId, appSecret, appKey);
		mOdnoklassniki.setTokenRequestListener(mListener);
		
		if (mOdnoklassniki.hasAccessToken()) {
			onSuccess(mOdnoklassniki.getCurrentAccessToken());
			return;
		}
	}
	
	private final OkTokenRequestListener mListener = new OkTokenRequestListener() {
		@Override
		public void onSuccess(final String accessToken) {
			onSuccess(accessToken);
		}

		@Override
		public void onCancel() {
			onFail(MSG_ERROR_AUTH_CANCELED);
		}
		
		@Override
		public void onError() {
			onFail(MSG_ERROR_GETTING_TOKEN);
		}
	};

	@Override
	public void login(String[] params) {
		mOdnoklassniki.requestAuthorization(mBaseContext, false, OkScope.VALUABLE_ACCESS);
	}

	@Override
	public void onSuccess(String token) {
		mCallBack.onSuccess(token);
	}

	@Override
	public void onFail(String msg) {
		mCallBack.onFail(msg);
	}

	@Override
	public void logout() {
		mOdnoklassniki.clearTokens(mBaseContext);
	}

	@Override
	public void onResume() {
		if (!mOdnoklassniki.hasAccessToken()) {
			onFail(MSG_ERROR_GETTING_TOKEN);
		}
	}

	@Override
	public void onDestroy() {
		mOdnoklassniki.removeTokenRequestListener();
	}

	@Override
	public Boolean isLoggedIn() {
		return mOdnoklassniki.hasAccessToken();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		// TODO Auto-generated method stub
	}

	@Override
	public void getUser() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onGetUser(String response) {
		// TODO Auto-generated method stub
	}

}
