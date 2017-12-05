package com.example.demo.youtubesampleapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeScopes;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.Subscription;
import com.google.api.services.youtube.model.SubscriptionSnippet;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LandingActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    /*for using youutbe player view*/

    private static final int RECOVERY_DIALOG_REQUEST = 1;
    Button subscribe;
    PreferenceUtils utils;
    // YouTube player view
    private YouTubePlayerView youTubeView;
    private GoogleAccountCredential mCredential;
    private static final String[] SCOPES = {YouTubeScopes.YOUTUBE};
    static final int REQUEST_ACCOUNT_PICKER = 1000;
    static final int REQUEST_AUTHORIZATION = 1001;
    static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;
    static final int REQUEST_PERMISSION_GET_ACCOUNTS = 1003;
    private static final String PREF_ACCOUNT_NAME = "accountName";
    private TextView mOutputText;
    private static Retrofit retrofit = null;
    private ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_landing);
        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        mOutputText = (TextView) findViewById(R.id.mOutputText);
        getDetailsOfVideo();




        utils = new PreferenceUtils(this);
        // Initializing video player with developer key
        youTubeView.initialize(Config.YOUTUBE_KEY, this);

        mCredential = GoogleAccountCredential.usingOAuth2(
                getApplicationContext(), Arrays.asList(SCOPES))
                .setSelectedAccountName(utils.getGmailId())
                .setBackOff(new ExponentialBackOff());

    }



    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(
                    getString(R.string.error_player), errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {

            // loadVideo() will auto play video
            // Use cueVideo() method, if you don't want to play it automatically
            player.loadVideo(Config.YOUTUBE_VIDEO_CODE);


            player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

            // this is set to CROMELESS all controls will br hidden.
        }
    }


    public void subscribe(View view) {
        getResultsFromApi();
    }

    private void getResultsFromApi() {
        if (!isGooglePlayServicesAvailable()) {
            acquireGooglePlayServices();
        } else if (mCredential.getSelectedAccountName() == null) {
            chooseAccount();
        } else {

//            subscribeYouTubeChannel(mCredential, channelId, this);
        }

    }

    public void subscribeYouTubeChannel(GoogleAccountCredential mCredential, String channelId, LandingActivity landingACtivity) {
        new MakeRequestTask(mCredential,channelId,landingACtivity).execute();
    }

    private void chooseAccount() {
        if (EasyPermissions.hasPermissions(
                this, android.Manifest.permission.GET_ACCOUNTS)) {
            String accountName = getPreferences(Context.MODE_PRIVATE)
                    .getString(PREF_ACCOUNT_NAME, null);
            if (accountName != null) {
                mCredential.setSelectedAccountName(accountName);
                getResultsFromApi();
            } else {
                // Start a dialog from which the user can choose an account
                startActivityForResult(
                        mCredential.newChooseAccountIntent(),
                        REQUEST_ACCOUNT_PICKER);
            }
        } else {
            // Request the GET_ACCOUNTS permission via a user dialog
            EasyPermissions.requestPermissions(
                    this,
                    "This app needs to access your Google account for YouTube channel subscription.",
                    REQUEST_PERMISSION_GET_ACCOUNTS, android.Manifest.permission.GET_ACCOUNTS);
        }
    }


    private void acquireGooglePlayServices() {
        GoogleApiAvailability apiAvailability =
                GoogleApiAvailability.getInstance();
        final int connectionStatusCode =
                apiAvailability.isGooglePlayServicesAvailable(this);
        if (apiAvailability.isUserResolvableError(connectionStatusCode)) {
            showGooglePlayServicesAvailabilityErrorDialog(connectionStatusCode);
        }

    }

    private void showGooglePlayServicesAvailabilityErrorDialog(int connectionStatusCode) {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        Dialog dialog = apiAvailability.getErrorDialog(
                LandingActivity.this,
                connectionStatusCode,
                REQUEST_GOOGLE_PLAY_SERVICES);
        dialog.show();

    }

    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability apiAvailability =
                GoogleApiAvailability.getInstance();
        final int connectionStatusCode =
                apiAvailability.isGooglePlayServicesAvailable(this);
        return connectionStatusCode == ConnectionResult.SUCCESS;

    }

    private class MakeRequestTask extends AsyncTask<Object, Object, Subscription> {
        private com.google.api.services.youtube.YouTube mService = null;
        private Exception mLastError = null;
        private String channelId;

        MakeRequestTask(GoogleAccountCredential credential, String channelId, LandingActivity landingACtivity) {
            this.channelId = channelId;
            HttpTransport transport = AndroidHttp.newCompatibleTransport();
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
            mService = new com.google.api.services.youtube.YouTube.Builder(
                    transport, jsonFactory, credential)
                    .setApplicationName(landingACtivity.getResources().getString(R.string.app_name))
                    .build();
        }


        @Override
        protected Subscription doInBackground(Object... params) {

            Subscription response = null;

            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("part", "snippet");


            Subscription subscription = new Subscription();
            SubscriptionSnippet snippet = new SubscriptionSnippet();
            ResourceId resourceId = new ResourceId();
            resourceId.set("channelId", channelId);
            resourceId.set("kind", "youtube#channel");

            snippet.setResourceId(resourceId);
            subscription.setSnippet(snippet);

            YouTube.Subscriptions.Insert subscriptionsInsertRequest = null;
            try {
                subscriptionsInsertRequest = mService.subscriptions().insert(parameters.get("part").toString(), subscription);
                response = subscriptionsInsertRequest.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        }

        @Override
        protected void onPostExecute(Subscription subscription) {
            super.onPostExecute(subscription);
            if(subscription!=null){
//                view.onSubscribetionSuccess(subscription.getSnippet().getTitle());
            }else {
//                view.onSubscribetionFail();
            }
        }
    }


   public void getDetailsOfVideo()
    {
        Retrofit retrofit = null;


        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.googleapis.com/youtube/v3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();



            Map<String,String> params = new HashMap<>();
            params.put("part","contentDetails,snippet,statistics");
            params.put("key", Config.YOUTUBE_KEY);
            params.put("fields","items(id,snippet,contentDetails,statistics)");
            params.put("id",Config.YOUTUBE_VIDEO_CODE);
            final ApiInterface apiInterface = APIClient.getAPIClient();

            Call<VideoDetailsPojo> call = apiInterface.getYouTubeDetail(params);
            call.enqueue(new Callback<VideoDetailsPojo>() {
                @Override
                public void onResponse(Call<VideoDetailsPojo> call, Response<VideoDetailsPojo> response) {
                    if(response.body()!=null){
                        Toast.makeText(LandingActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    }
                    else {
//                        view.errorToLoad();
                        Toast.makeText(LandingActivity.this, "Fail1", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<VideoDetailsPojo> call, Throwable t) {
//                    view.errorToLoad();
                    Toast.makeText(LandingActivity.this, "Fail2", Toast.LENGTH_SHORT).show();

                }
            });



        }
    }
    }
