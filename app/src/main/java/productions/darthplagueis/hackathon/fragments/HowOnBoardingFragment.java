package productions.darthplagueis.hackathon.fragments;

import android.content.Intent;
import android.view.View;

import productions.darthplagueis.hackathon.MainActivity;
import productions.darthplagueis.hackathon.abstractclasses.AbstractOnBoardingFragment;

public class HowOnBoardingFragment extends AbstractOnBoardingFragment {

    @Override
    public void onCreateView() {
        parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentActivity().startActivity(new Intent(getParentActivity(), MainActivity.class));
                getParentActivity().finish();
            }
        });
    }
}
