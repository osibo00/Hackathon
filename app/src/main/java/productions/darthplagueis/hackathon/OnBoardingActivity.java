package productions.darthplagueis.hackathon;

import productions.darthplagueis.hackathon.abstractclasses.AbstractOnBoardingActivity;

public class OnBoardingActivity extends AbstractOnBoardingActivity {

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
}
