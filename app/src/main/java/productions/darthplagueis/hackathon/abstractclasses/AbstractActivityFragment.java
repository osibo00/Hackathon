package productions.darthplagueis.hackathon.abstractclasses;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class AbstractActivityFragment extends Fragment {

    private AbstractActivity parentActivity;
    public View parentView;

    public AbstractActivityFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setParentActivity(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(getLayoutId(), container, false);
        onCreateView();
        return parentView;
    }

    public abstract int getLayoutId();

    public abstract void onCreateView();

    private void setParentActivity(@NonNull Context context) {
        parentActivity = ((AbstractActivity) context);
    }

    public AbstractActivity getParentActivity() {return parentActivity;}
}
