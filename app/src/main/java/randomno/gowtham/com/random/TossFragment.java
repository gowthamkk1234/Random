package randomno.gowtham.com.random;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Locale;
import java.util.Objects;
import java.util.Random;

import es.dmoral.toasty.Toasty;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class TossFragment extends Fragment{
    Button b_flip;
    ImageView iv_coin;
    Random r ;
    int coinSide;

AdView adViewtoss;
    public TossFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try  {
            InputMethodManager imm = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(Objects.requireNonNull(getActivity().getCurrentFocus()).getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_toss, container, false);
        b_flip = v.findViewById(R.id.b_flip);
        adViewtoss=(AdView)v.findViewById(R.id.adViewtoss);
        AdRequest adRequest = new AdRequest.Builder().build();
        adViewtoss.loadAd(adRequest);
        iv_coin = v.findViewById(R.id.rass);

        r = new Random();
        b_flip.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View view) {
                coinSide = r.nextInt(2);
                if (coinSide == 0){
                    iv_coin.setImageResource(R.drawable.headicon);
                    Toast t=Toasty.normal(Objects.requireNonNull(getContext()),"HEADS",R.drawable.headicon);
                    t.setGravity(Gravity.BOTTOM,0,0);
                    t.setDuration(Toast.LENGTH_SHORT);
                    t.show();
                }else if(coinSide == 1) {
                    iv_coin.setImageResource(R.drawable.tailicon);

                    Toast t=Toasty.normal(Objects.requireNonNull(getContext()),"TAILS",R.drawable.tailicon);
                    t.setGravity(Gravity.BOTTOM,0,0);
                    t.setDuration(Toast.LENGTH_SHORT);
                    t.show();

                }


                RotateAnimation rotat = new RotateAnimation(0,1000 ,
                        RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
                rotat.setDuration(1000);
                iv_coin.startAnimation(rotat);

            }
        });

        return v;
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}