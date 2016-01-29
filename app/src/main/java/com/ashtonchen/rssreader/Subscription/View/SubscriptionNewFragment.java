package com.ashtonchen.rssreader.subscription.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ashtonchen.rssreader.base.BaseFragment;
import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.base.ComponentFragment;
import com.ashtonchen.rssreader.reader.listener.FeedNetworkCallbackInterface;
import com.ashtonchen.rssreader.reader.model.Channel;
import com.ashtonchen.rssreader.subscription.SubscriptionComponent;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SubscriptionNewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SubscriptionNewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubscriptionNewFragment extends ComponentFragment<SubscriptionComponent> implements FeedNetworkCallbackInterface {
    EditText mEditText;
    private String mRSSlink;
    private OnFragmentInteractionListener mListener;

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

        setSubtitle(R.string.action_bar_subtitle_new_subscription);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        TextView textView = new TextView(mContext);
        textView.setText(R.string.new_subscription);
        textView.setGravity(Gravity.CENTER);

        mEditText = new EditText(mContext);
        mEditText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mEditText.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

        Button cancelButton = new Button(mContext);
        cancelButton.setText(R.string.button_cancel);
        cancelButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        Button button = new Button(mContext);
        button.setText(R.string.button_subscribe);
        button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        button.setOnClickListener(onAddNewButtonClicked(this));

        LinearLayout buttonLinearLayout = new LinearLayout(mContext);
        buttonLinearLayout.setGravity(Gravity.CENTER);
        buttonLinearLayout.addView(cancelButton);
        buttonLinearLayout.addView(button);

        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.addView(textView);
        linearLayout.addView(mEditText);
        linearLayout.addView(buttonLinearLayout);

        FrameLayout frameLayout = new FrameLayout(mContext);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        frameLayout.addView(linearLayout);

        //return inflater.inflate(R.layout.fragment_subscription_new, container, false);
        return frameLayout;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    protected SubscriptionComponent getComponent() {
        return new SubscriptionComponent(mContext);
    }

    private View.OnClickListener onAddNewButtonClicked(final FeedNetworkCallbackInterface callback) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRSSlink = mEditText.getText().toString().trim();
                //link = "http://rss.cnn.com/rss/cnn_topstories.rss";
                if (!mRSSlink.isEmpty()) {
                    if (Patterns.WEB_URL.matcher(mRSSlink).matches()) {
                        if (!mComponent.subscriptionExists(mRSSlink)) {
                            Log.d(getClass().getName(), "Subscription does not exist in database");
                            mComponent.loadSubscriptionInfo(mRSSlink, callback);
                        }
                    }

                    /*
                    try {
                        URL url = new URL(link);
                        Log.d(this.getClass().getName(), "URL ok");
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        Log.d(this.getClass().getName(), "HttpURLConnection ok");
                        int code = connection.getResponseCode();
                        if(code == 200) {
                            mSubscriptionComponent.loadSubscriptionInfo(link, callback);
                        } else {
                            Log.d(this.getClass().getName(), "Invalid RSS link");
                        }
                    } catch (Exception e) {
                        Log.d(this.getClass().getName(), "Exception when reading RSS link");
                    }
                    */
                }
            }
        };
    }

    public void onDownloadFinished(Channel channel) {
        if (channel != null) {
            Log.d(this.getClass().getName(), "got new channel");
            channel.setUrl(mRSSlink);
            mComponent.addNewSubscription(channel);
            Toast.makeText(mContext, R.string.toast_rss_added, Toast.LENGTH_SHORT).show();
            getActivity().onBackPressed();
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
