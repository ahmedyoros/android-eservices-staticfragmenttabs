package android.eservices.staticfragmenttabs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class FragmentOne extends Fragment {

    public static final String TAB_NAME = "ADD TO COUNTER";
    private View mView;
    private CounterManagerI counterI;

    public FragmentOne() {
        //super(R.layout.fragment_one);
    }

    public static FragmentOne newInstance() {
        return new FragmentOne();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_one, container, false);
        return mView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        setOnIncrementButtonClicked();
    }

    private void setOnIncrementButtonClicked(){
        Button incBtn = mView.findViewById(R.id.button_increment);
        incBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counterI != null)
                    counterI.increment();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CounterManagerI)
            this.counterI = (CounterManagerI) context;
    }
}

