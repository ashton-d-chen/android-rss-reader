package com.ashtonchen.rssreader.Subscription.View;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ashtonchen.rssreader.BaseFragment;
import com.ashtonchen.rssreader.R;
import com.ashtonchen.rssreader.Reader.Interface.FeedNetworkCallbackInterface;
import com.ashtonchen.rssreader.Reader.Model.Channel;
import com.ashtonchen.rssreader.Subscription.Model.Subscription;
import com.ashtonchen.rssreader.Subscription.SubscriptionComponent;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SubscriptionNewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SubscriptionNewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubscriptionNewFragment extends BaseFragment implements FeedNetworkCallbackInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText mEditText;
    private SubscriptionComponent mSubscriptionComponent;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
/*        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mSubscriptionComponent = new SubscriptionComponent(mContext);

/*        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
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

    private View.OnClickListener onAddNewButtonClicked(final FeedNetworkCallbackInterface callback) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = mEditText.getText().toString().trim();
                if (!url.isEmpty()) {
                    mSubscriptionComponent.getSubscriptionInfo(url, callback);
                }
            }
        };
    }

    public void onDownloadFinished(Channel channel) {
        Subscription subscription = new Subscription();
        subscription.setUrl(channel.getUrl());
        subscription.setTitle(channel.getTitle());
        subscription.setDescription(channel.getDescription());
        subscription.setThumbnailURL(channel.getThumbnailURL());

        mSubscriptionComponent.addNewSubscription(subscription);
        getActivity().onBackPressed();
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
