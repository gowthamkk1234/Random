package randomno.gowtham.com.random;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TabHost;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        toolbar.setTitle("RANDOMIZE");
        ViewPager viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);
     TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        MobileAds.initialize(this,"ca-app-pub-2392738236072166~7925324738");
        mInterstitialAd=new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-2392738236072166/3696830330");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                Intent mintent=new Intent(MainActivity.this,AboutActivity.class);
                startActivity(mintent);
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });


        final AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName("RANDOMIZE").withIcon(getResources().getDrawable(R.drawable.ricon))
                )
                .withSelectionListEnabledForSingleProfile(false)
                .build();
        final PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Home").withIcon(getResources().getDrawable(R.drawable.home));
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("About").withIcon(getResources().getDrawable(R.drawable.about));
        SecondaryDrawerItem item3=new SecondaryDrawerItem().withIdentifier(3).withName("Share").withIcon(getResources().getDrawable(R.drawable.share));
        SecondaryDrawerItem item4=new SecondaryDrawerItem().withIdentifier(4).withName("Communicate").withIcon(getResources().getDrawable(R.drawable.communicate));
        SecondaryDrawerItem item5=new SecondaryDrawerItem().withIdentifier(5).withName("Rate Us").withIcon(getResources().getDrawable(R.drawable.rateus));



//create the drawer and remember the `Drawer` result object
         final  Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        item2,
                        new DividerDrawerItem(),
                        item3,item4,item5

                )

                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position)
                        {
                            case 1:

                               break;
                            case 2:
                                if(mInterstitialAd.isLoaded())
                                {
                                    mInterstitialAd.show();
                                }else {
                                    Intent i = new Intent(MainActivity.this, AboutActivity.class);
                                    startActivity(i);
                                }
                                break;
                            case 4:
                                Intent intent2 = new Intent();
                                intent2.setAction(Intent.ACTION_SEND);
                                intent2.setType("text/plain");
                                intent2.putExtra(Intent.EXTRA_TEXT, "\"Hey check out my app at: https://play.google.com/store/apps/details?id=randomno.gowtham.com.random" );
                                startActivity(Intent.createChooser(intent2, "Share via"));
                                break;
                            case 5:
                                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                        "mailto","kkgowtham1234@gmail.com", null));
                                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                                startActivity(Intent.createChooser(emailIntent, "Send email..."));
                                break;
                            case 6:
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse("market://details?id=" + getPackageName()));
                                startActivity(intent);
                                break;
                        }

                        return false;
                    }
                }).withSavedInstance(savedInstanceState).build();


    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new RandomnoFragment(), "Number");
        adapter.addFrag(new SeriesFragment(), "Series");
        adapter.addFrag(new TossFragment(), "Toss");
        adapter.addFrag(new DiceFragment(), "Dice");
        adapter.addFrag(new CardFragment(), "Card");
        adapter.addFrag(new ColourFragment(), "Colour");
        adapter.addFrag(new ListsFragment(), "LISTS");
        //adapter.addFrag(new EightFragment(), "EIGHT");
        //adapter.addFrag(new NineFragment(), "NINE");
        //adapter.addFrag(new TenFragment(), "TEN");
        viewPager.setAdapter(adapter);
    }
   class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}