package com.example.android.news;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class CategoryFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private static final int NBA_ZERO = 0;
    private static final int FINANCIAL_ONE = 1;
    private static final int INTERNATIONAL_TWO = 2;

    public CategoryFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }
    @Override
    public Fragment getItem(int position) {
        if (position == NBA_ZERO) {
            Log.d("CategoryFragmentPage", "===========NBA============");
            return new BasketballFragment();
        } else if (position == FINANCIAL_ONE) {
            Log.d("CategoryFragmentPage", "===========Financial============");
            return new FinancialFragment();
        } else if (position == INTERNATIONAL_TWO) {
            Log.d("CategoryFragmentPage", "===========international============");
            return new InternationalFragment();
        } else {
            Log.d("CategoryFragmentPage", "===========technology============");
            return new TechnologyFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_basketball);
        } else if (position == 1) {
            return mContext.getString(R.string.category_financial);
        } else if (position == 2) {
            return mContext.getString(R.string.category_international);
        } else {
            return mContext.getString(R.string.category_technology);
        }
    }
}
