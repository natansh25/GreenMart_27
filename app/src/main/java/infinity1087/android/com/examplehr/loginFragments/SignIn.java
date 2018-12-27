package infinity1087.android.com.examplehr.loginFragments;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import infinity1087.android.com.examplehr.MainActivity;
import infinity1087.android.com.examplehr.R;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignIn extends Fragment implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener, Validator.ValidationListener {

    private static final String TAG = SignIn.class.getSimpleName();
    private static final int RC_SIGN_IN = 430;
    public static GoogleSignInClient mGoogleSignInClient;
    private Button btn_forgetPass;
    private EditText results;
    AlertDialog.Builder builder;
    public SignInButton google_sign_in;
    public LoginButton facebook_sign_in;
    public static String user_image, user_name, user_mail;
    public Button google, btn_login;
    public CallbackManager callbackManager;
    public String id, name;
    @NotEmpty(message = "Field should not be empty !!")
    @Email
    private EditText edt_email;
    @NotEmpty(message = "Filed should not be empty !!")
    @Length(min = 7)
    private EditText edt_password;
    protected Validator validator;
    protected boolean validated;
    final SignIn context = this;
    public SignIn() {

//        builder.setMessage("Enter Email Address")
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);
 //      Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        builder = new AlertDialog.Builder(getActivity());
        validator = new Validator(this);
        validator.setValidationListener(this);

        google_sign_in = view.findViewById(R.id.btn_google_sign_in);
        facebook_sign_in = view.findViewById(R.id.btn_fb_sign_in);
        btn_login = view.findViewById(R.id.btn_login);
        edt_email = view.findViewById(R.id.edt_lname);
        edt_password = view.findViewById(R.id.edt_signUp_password);
        edt_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               validator.validate();
            }
        });

        facebook_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                facebook();
            }
        });
        btn_forgetPass = view.findViewById(R.id.btn_forgetPass);
        btn_forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(getActivity());
                View promptsView = li.inflate(R.layout.forgetpass, null);
                builder.setMessage("") .setTitle("Reset Email");
                builder.setView(promptsView);
                final  EditText userin = (EditText)promptsView.findViewById(R.id.edt_forgetpass);

                builder .setCancelable(false)
                        .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
//                                results.setText(userin.getText());
                                Toast.makeText(getApplicationContext(),"Check you Email,Link Sent!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(),"Cancelled!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Reset Email");
                alert.show();
            }
        });
        googlesignin();

        return view;

    }

    private void finish() {

    }

    private void facebook() {

        CallbackManager callbackManager = CallbackManager.Factory.create();
        facebook_sign_in.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "Successful Login", Toast.LENGTH_SHORT).show();


                String accessToken = loginResult.getAccessToken()
                        .getToken();
                Log.i("accessToken", accessToken);
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object,
                                                    GraphResponse response) {
                                Log.i("LoginActivity",
                                        response.toString());
                                try {
                                    id = object.getString("id");
                                    try {
                                        URL profile_pic = new URL(
                                                "http://graph.facebook.com/" + id + "/picture?type=large");
                                        Log.i("profile_pic",
                                                profile_pic + "");

                                    } catch (MalformedURLException e) {
                                        e.printStackTrace();
                                    }
                                    Log.e("UserDate", String.valueOf(object));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                System.out.println("onCancel");
            }

            @Override
            public void onError(FacebookException exception) {
                System.out.println("onError");
                Log.v("LoginActivity", exception.getCause().toString());
            }
        });
    }

    private void googlesignin() {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        google_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });


    }


    @Override
    public void onClick(View v) {

        if (v == facebook_sign_in) {
            facebook_sign_in.performClick();
        } else if (v == google) {

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());


        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();


        if (account != null) {

            Intent i = new Intent(getActivity(), MainActivity.class);
            startActivity(i);

        } else if (isLoggedIn)

        {
            Intent i = new Intent(getActivity(), MainActivity.class);
            startActivity(i);
        }


        //OptionalPendingResult opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }


    @Override
    public void onActivityResult(int requestCode, int responseCode,
                                 Intent data) {
        if (requestCode == RC_SIGN_IN) {

         /*GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleGPlusSignInResult(result)*/;

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
        else
        {
            super.onActivityResult(requestCode, responseCode, data);
            callbackManager.onActivityResult(requestCode, responseCode, data);
        }
    }


    private void handleSignInResult(Task<GoogleSignInAccount> task) {

        Log.d("xaxa", "LoggedInsuccessfully");


        try {

            GoogleSignInAccount account = task.getResult(ApiException.class);

            if (account != null) {
                user_name = account.getDisplayName();
                user_mail = account.getEmail();
                user_image = String.valueOf(account.getPhotoUrl());
                Toast.makeText(getActivity(), "welcome : " + account.getDisplayName(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
            }

            // Signed in successfully, show authenticated UI.

        } catch (ApiException e) {

            Log.d("uuu", e.getMessage() + " : " + e.getLocalizedMessage());
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
          /*  Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);*/
        }

    }

    private void updateUI(boolean isSignedIn) {

        if (isSignedIn) {
            google_sign_in.setVisibility(View.GONE);
        } else {
            google_sign_in.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onValidationSucceeded() {

        validated = true;
        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {


        validated = false;

        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getActivity());

            // Display error messages

            if (view instanceof EditText) {
                edt_email = (EditText) view;
                edt_email.setError(message);
            }
            if (view instanceof EditText) {
                edt_password = (EditText) view;
                edt_password.setError(message);
            }


        }

    }


}
