package com.example.testapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class Helper {

    public static void loadImage(Context context, String url, CircleImageView imageView) {

        Glide.with(context)
                .applyDefaultRequestOptions(new RequestOptions().centerCrop())
                .load(url)
                .into(imageView);

    }

    public static void loadFragment(Fragment currentFragment, Bundle data, int fragmentID,
                                    boolean backStack, FragmentActivity fragmentActivity) {

        currentFragment.setArguments(data);
        FragmentTransaction fragmentTransaction = fragmentActivity
                .getSupportFragmentManager()
                .beginTransaction();

        if (backStack) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction
                .replace(fragmentID, currentFragment)
                .commit();
    }

    public static boolean checkNetworkConnection(Context context) {

        boolean wifiConnected = false;
        boolean mobileDataConnected = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = Objects.requireNonNull(connectivityManager).getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            wifiConnected = networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
            mobileDataConnected = networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
        }
        return wifiConnected || mobileDataConnected;

    }
}
