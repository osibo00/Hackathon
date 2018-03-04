package productions.darthplagueis.hackathon;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import productions.darthplagueis.hackathon.abstractclasses.AbstractOnBoardingActivity;
import productions.darthplagueis.hackathon.controller.FragmentAdapter;
import productions.darthplagueis.hackathon.fragments.DefaultOnBoardingFragment;
import productions.darthplagueis.hackathon.fragments.HowOnBoardingFragment;

public class OnBoardingActivity extends AbstractOnBoardingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setViews();
    }

    private void setViews() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setViewPager(viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(viewPager, true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_on_boarding;
    }

    @Override
    protected Class<?> getNextActivity() {
        return MainActivity.class;
    }

    @Override
    protected void setAnimation() {

    }

    private void setViewPager(ViewPager viewPager) {
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        DefaultOnBoardingFragment whatFragment = new DefaultOnBoardingFragment();
        whatFragment.setLayoutId(R.layout.fragment_on_boarding_intro);
        DefaultOnBoardingFragment whyFragment = new DefaultOnBoardingFragment();
        whyFragment.setLayoutId(R.layout.fragment_on_boarding_why);
        HowOnBoardingFragment howFragment = new HowOnBoardingFragment();
        howFragment.setLayoutId(R.layout.fragment_on_boarding_how);
        adapter.addOnBoardingFragments(whatFragment);
        adapter.addOnBoardingFragments(whyFragment);
        adapter.addOnBoardingFragments(howFragment);
        viewPager.setAdapter(adapter);
    }
}
