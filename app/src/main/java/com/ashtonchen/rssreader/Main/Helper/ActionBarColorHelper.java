package com.ashtonchen.rssreader.main.helper;

import android.app.Activity;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Build;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ashtonchen.rssreader.R;

import java.util.ArrayList;

/**
 * Created by Ashton Chen on 15-12-15.
 */
public final class ActionBarColorHelper {

    /**
     * Use this method to colorize toolbar icons to the desired target color
     *
     * @param toolbarView       toolbar view being colored
     * @param toolbarIconsColor the target color of toolbar icons
     */
 /*   public static void colorizeToolbar(Toolbar toolbarView, int toolbarIconsColor, Activity activity) {
        final PorterDuffColorFilter colorFilter
                = new PorterDuffColorFilter(toolbarIconsColor, PorterDuff.Mode.MULTIPLY);

        Log.d("ActionBarColorHelper", " toolbar view chlid count = " + toolbarView.getChildCount());
        for (int i = 0; i < toolbarView.getChildCount(); i++) {
            final View v = toolbarView.getChildAt(i);

            Log.d("ActionBarColorHelper", "view is " + v.getClass().getName());
            //Step 1 : Changing the color of back button (or open drawer button).
            if (v instanceof ImageButton) {
                //Action Bar back button
                ((ImageButton) v).getDrawable().setColorFilter(colorFilter);
            }

            if(v instanceof ImageView) {
                ((ImageView)v).getDrawable().setAlpha(255);
                ((ImageView)v).getDrawable().setColorFilter(colorFilter);
            }

            if(v instanceof AutoCompleteTextView) {
                ((AutoCompleteTextView)v).setTextColor(toolbarIconsColor);
            }

            if(v instanceof TextView) {
                ((TextView)v).setTextColor(toolbarIconsColor);
            }

            if(v instanceof EditText) {
                ((EditText)v).setTextColor(toolbarIconsColor);
            }

            if (v instanceof ViewGroup){
                for (int lli =0; lli< ((ViewGroup)v).getChildCount(); lli ++){
                    colorizeToolbar(((ViewGroup) v).getChildAt(lli), toolbarIconsColor, activity);
                }
            }

            if (v instanceof ActionMenuView) {
                Log.d("ActionBarColorHelper", " it is action menu view");
                Log.d("ActionBarColorHelper", " chlid count = " + ((ActionMenuView) v).getChildCount());
                if (((ActionMenuView) v).getChildCount() == 0) {

                }
                for (int j = 0; j < ((ActionMenuView) v).getChildCount(); j++) {

                    //Step 2: Changing the color of any ActionMenuViews - icons that
                    //are not back button, nor text, nor overflow menu icon.
                    final View innerView = ((ActionMenuView) v).getChildAt(j);

                    if (innerView instanceof ActionMenuItemView) {
                        int drawablesCount = ((ActionMenuItemView) innerView).getCompoundDrawables().length;
                        for (int k = 0; k < drawablesCount; k++) {
                            if (((ActionMenuItemView) innerView).getCompoundDrawables()[k] != null) {
                                final int finalK = k;

                                //Important to set the color filter in seperate thread,
                                //by adding it to the message queue
                                //Won't work otherwise.
                                innerView.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((ActionMenuItemView) innerView).getCompoundDrawables()[finalK].setColorFilter(colorFilter);
                                    }
                                });
                            }
                        }
                    }
                }
            }

            //Step 3: Changing the color of title and subtitle.
            toolbarView.setTitleTextColor(toolbarIconsColor);
            toolbarView.setSubtitleTextColor(toolbarIconsColor);

            //Step 4: Changing the color of the Overflow Menu icon.
            setOverflowButtonColor(activity, colorFilter);
        }
    }*/


    /* Use this method to colorize toolbar icons to the desired target color
    * @param toolbarView toolbar view being colored
    * @param toolbarIconsColor the target color of toolbar icons
    * @param activity reference to activity needed to register observers
    */
    public static void colorizeToolbar(Toolbar toolbarView, int toolbarIconsColor) {
        final PorterDuffColorFilter colorFilter
                = new PorterDuffColorFilter(toolbarIconsColor, PorterDuff.Mode.SRC_IN);

        //Log.d("ActionBarColorHelper", "view is " + v.getClass().getName());

        for (int i = 0; i < toolbarView.getMenu().size(); i++) {
            toolbarView.getMenu().getItem(i).getIcon().setColorFilter(colorFilter);
        }

        for (int i = 0; i < toolbarView.getChildCount(); i++) {
            final View v = toolbarView.getChildAt(i);

            doColorizing(v, colorFilter, toolbarIconsColor);
        }

        //Step 3: Changing the color of title and subtitle.
        toolbarView.setTitleTextColor(toolbarIconsColor);
        toolbarView.setSubtitleTextColor(toolbarIconsColor);
    }

    public static void doColorizing(View v, final ColorFilter colorFilter, int toolbarIconsColor) {
        Log.d("ActionBarColorHelper", "view is " + v.getClass().getName());
        if (v instanceof ImageButton) {
            ((ImageButton) v).getDrawable().setAlpha(255);
            ((ImageButton) v).getDrawable().setColorFilter(colorFilter);
        }

        if (v instanceof ImageView) {
            ((ImageView) v).getDrawable().setAlpha(255);
            ((ImageView) v).getDrawable().setColorFilter(colorFilter);
        }

        if (v instanceof AutoCompleteTextView) {
            ((AutoCompleteTextView) v).setTextColor(toolbarIconsColor);
        }

        if (v instanceof TextView) {
            ((TextView) v).setTextColor(toolbarIconsColor);
        }

        if (v instanceof EditText) {
            ((EditText) v).setTextColor(toolbarIconsColor);
        }

        if (v instanceof ViewGroup) {
            for (int lli = 0; lli < ((ViewGroup) v).getChildCount(); lli++) {
                doColorizing(((ViewGroup) v).getChildAt(lli), colorFilter, toolbarIconsColor);
            }
        }

        if (v instanceof MenuItem) {

        }

        if (v instanceof ActionMenuView) {
            ActionMenuView view = (ActionMenuView) v;

            Log.d("ActionBarColorHelper", " chlid count = " + ((ActionMenuView) v).getChildCount());

            for (int j = 0; j < ((ActionMenuView) v).getChildCount(); j++) {

                //Step 2: Changing the color of any ActionMenuViews - icons that
                //are not back button, nor text, nor overflow menu icon.
                final View innerView = ((ActionMenuView) v).getChildAt(j);

                if (innerView instanceof ActionMenuItemView) {
                    int drawablesCount = ((ActionMenuItemView) innerView).getCompoundDrawables().length;
                    for (int k = 0; k < drawablesCount; k++) {
                        if (((ActionMenuItemView) innerView).getCompoundDrawables()[k] != null) {
                            final int finalK = k;

                            //Important to set the color filter in seperate thread,
                            //by adding it to the message queue
                            //Won't work otherwise.
                            //Works fine for my case but needs more testing

                            ((ActionMenuItemView) innerView).getCompoundDrawables()[finalK].setColorFilter(colorFilter);

//                              innerView.post(new Runnable() {
//                                  @Override
//                                  public void run() {
//                                      ((ActionMenuItemView) innerView).getCompoundDrawables()[finalK].setColorFilter(colorFilter);
//                                  }
//                              });
                        }
                    }
                }
            }
        }
    }


    /**
     * It's important to set overflowDescription atribute in styles, so we can grab the reference
     * to the overflow icon. Check: res/values/styles.xml
     *
     * @param activity
     * @param colorFilter
     */
    private static void setOverflowButtonColor(final Activity activity, final PorterDuffColorFilter colorFilter) {
        final String overflowDescription = activity.getString(R.string.abc_action_menu_overflow_description);
        final ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
        final ViewTreeObserver viewTreeObserver = decorView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                final ArrayList<View> outViews = new ArrayList<View>();
                decorView.findViewsWithText(outViews, overflowDescription,
                        View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION);
                if (outViews.isEmpty()) {
                    return;
                }

                //ActionMenuItemView overflow = (ActionMenuItemView)outViews.get(0);
                //overflow.getCompoundDrawables()[0].setColorFilter(colorFilter);
                // TintImageView overflow = (TintImageView) outViews.get(0);
                //overflow.setColorFilter(colorFilter);
                removeOnGlobalLayoutListener(decorView, this);
            }
        });
    }

    private static void removeOnGlobalLayoutListener(View v, ViewTreeObserver.OnGlobalLayoutListener listener) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            v.getViewTreeObserver().removeGlobalOnLayoutListener(listener);
        } else {
            v.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
        }
    }
}
