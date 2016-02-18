package com.ashtonchen.rssreader.subscription.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.base.ComponentFragment;
import com.ashtonchen.rssreader.reader.helper.FeedNetworkHelper;
import com.ashtonchen.rssreader.subscription.SubscriptionComponent;
import com.ashtonchen.rssreader.subscription.model.Channel;
import com.ashtonchen.rssreader.utility.NetworkUtility;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link SubscriptionNewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubscriptionNewFragment extends ComponentFragment<SubscriptionComponent> {
    public static final int NEW_SUBSCRIPTION = 1;
    private EditText mEditText;
    private String mRSSlink;

    public SubscriptionNewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SubscriptionNewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SubscriptionNewFragment newInstance() {
        SubscriptionNewFragment fragment = new SubscriptionNewFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FrameLayout frameLayout = new FrameLayout(mContext);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));

        return frameLayout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        TextView textView = new TextView(mContext);
        textView.setText(R.string.new_subscription);
        textView.setGravity(Gravity.CENTER);

        mEditText = new EditText(mContext);
        mEditText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mEditText.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

        Button cancelButton = new Button(mContext);
        cancelButton.setText(R.string.button_cancel);
        cancelButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        cancelButton.setOnClickListener(getOnCancelButtonClicked());

        Button okButton = new Button(mContext);
        okButton.setText(R.string.button_subscribe);
        okButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        okButton.setOnClickListener(getOnAddNewButtonClicked(getNetworkHelperCallback()));

        LinearLayout buttonLinearLayout = new LinearLayout(mContext);
        buttonLinearLayout.setGravity(Gravity.CENTER);
        buttonLinearLayout.addView(cancelButton);
        buttonLinearLayout.addView(okButton);

        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.addView(textView);
        linearLayout.addView(mEditText);
        linearLayout.addView(buttonLinearLayout);

        ((FrameLayout) view).addView(linearLayout);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mEditText.requestFocus();
        mContext.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.SHOW_FORCED);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        /*if (context instanceof OnFragmentInteractionListener) {
            mEmptyViewListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
        mEditText = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    protected SubscriptionComponent getComponent() {
        return new SubscriptionComponent(mContext);
    }

    private View.OnClickListener getOnAddNewButtonClicked(final FeedNetworkHelper.NetworkHelperCallback callback) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRSSlink = mEditText.getText().toString().trim();
                //link = "http://rss.cnn.com/rss/cnn_topstories.rss";
                if (!mRSSlink.isEmpty()) {
                    if (Patterns.WEB_URL.matcher(mRSSlink).matches()) {
                        if (NetworkUtility.isOnline(mContext)) {
                            if (!mComponent.findData(mRSSlink)) {
                                Log.d(getClass().getName(), "Add RSS link");
                                mComponent.loadSubscriptionInfo(mRSSlink, callback);
                            } else {
                                Toast.makeText(mContext, R.string.new_subscription_link_invalid, Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(mContext, R.string.no_network_connection, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(mContext, R.string.new_subscription_link_incorrect_format, Toast.LENGTH_LONG).show();
                    }

                    /*
                    try {
                        URL url = new URL(link);
                        Log.d(this.getClass().getSimpleName(), "URL ok");
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        Log.d(this.getClass().getSimpleName(), "HttpURLConnection ok");
                        int code = connection.getResponseCode();
                        if(code == 200) {
                            mSubscriptionComponent.loadSubscriptionInfo(link, callback);
                        } else {
                            Log.d(this.getClass().getSimpleName(), "Invalid RSS link");
                        }
                    } catch (Exception e) {
                        Log.d(this.getClass().getSimpleName(), "Exception when reading RSS link");
                    }
                    */

                } else {
                    Toast.makeText(mContext, R.string.new_subscription_no_link_entered, Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    private View.OnClickListener getOnCancelButtonClicked() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_CANCELED, null);
                mContext.onBackPressed();
            }
        };
    }

    protected FeedNetworkHelper.NetworkHelperCallback getNetworkHelperCallback() {
        return new FeedNetworkHelper.NetworkHelperCallback() {
            @Override
            public void onNetworkHelperFinished(Channel channel) {
                if (channel != null) {
                    Log.d(this.getClass().getSimpleName(), "got new channel");
                    channel.setUrl(mRSSlink);
                    if (mComponent != null) {
                        mComponent.addData(channel);
                        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, null);

                        Toast.makeText(mContext, R.string.toast_rss_added, Toast.LENGTH_SHORT).show();
                        mContext.onBackPressed();
                    }
                }
            }
        };
    }
    protected String getSubtitle() {
        return getString(R.string.action_bar_subtitle_new_subscription);
    }

    protected boolean shouldDisplayDrawerIcon() {
        return false;
    }
}
