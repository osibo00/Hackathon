package productions.darthplagueis.hackathon.abstractclasses;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class AbstractOnBoardingFragment extends Fragment {

    private AbstractOnBoardingActivity parentActivity;
    private int layoutId;
    public View parentView;

    public AbstractOnBoardingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setParentActivity(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView = inflater.inflate(layoutId, container, false);
        onCreateView();
        return parentView;
    }

    public abstract void onCreateView();

    private void setParentActivity(@NonNull Context context) {
        parentActivity = ((AbstractOnBoardingActivity) context);
    }

    public AbstractOnBoardingActivity getParentActivity() {
        return parentActivity;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }
}
