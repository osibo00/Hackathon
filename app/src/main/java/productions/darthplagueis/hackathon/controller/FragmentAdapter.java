package productions.darthplagueis.hackathon.controller;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import productions.darthplagueis.hackathon.abstractclasses.AbstractActivityFragment;
import productions.darthplagueis.hackathon.abstractclasses.AbstractOnBoardingFragment;

public class FragmentAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitleList = new ArrayList<>();

    public FragmentAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addActivityFragment(AbstractActivityFragment abstractActivityFragment, String title) {
        fragmentList.add(abstractActivityFragment);
        fragmentTitleList.add(title);
    }

    public void addOnBoardingFragments(AbstractOnBoardingFragment abstractOnBoardingFragment) {
        fragmentList.add(abstractOnBoardingFragment);
        fragmentTitleList.add("");
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }
}
