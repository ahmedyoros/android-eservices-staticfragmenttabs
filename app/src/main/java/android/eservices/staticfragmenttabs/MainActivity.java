package android.eservices.staticfragmenttabs;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends FragmentActivity implements CounterManagerI {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private int currentCounter;
    private TextView counterTextView;

    private FragmentStateAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViewPagerAndTabs();
    }

    private void setupViewPagerAndTabs() {
        viewPager = findViewById(R.id.tab_viewpager);

        // increment and decrement counter, use the already provided String ressource (see strings.xml)
        counterTextView = findViewById(R.id.counter_textview);
        counterTextView.setText(getString(R.string.counter_text, currentCounter));
        // we want two fragments with layouts : fragment_one, fragment_two.
/*
        final FragmentOne fragmentOne = FragmentOne.newInstance();
        final FragmentOne fragmentTwo = FragmentOne.newInstance();
*/

        final List<Fragment> frags = Arrays.asList(
            FragmentOne.newInstance(), FragmentTwo.newInstance()
        );
        // set adapter to viewpager and handle fragment change inside

        pagerAdapter = new MyPagerAdapter(this, frags);

        viewPager.setAdapter(pagerAdapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(position == 0 ? FragmentOne.TAB_NAME : FragmentTwo.TAB_NAME);
            }
        });

        tabLayoutMediator.attach();

    }

    @Override
    public void increment() {
        currentCounter++;
        counterTextView.setText(getString(R.string.counter_text, currentCounter));
    }

    @Override
    public void decrement() {
        currentCounter--;
        counterTextView.setText(getString(R.string.counter_text, currentCounter));
    }


    private class MyPagerAdapter extends FragmentStateAdapter {

        List<Fragment> mFrags;

        public MyPagerAdapter(FragmentActivity fa, List<Fragment> frags) {
            super(fa);
            mFrags = frags;
        }

        @Override
        public Fragment createFragment(int position) {
            return mFrags.get(position);
        }

        @Override
        public int getItemCount() {
            return mFrags.size();
        }
    }
}
