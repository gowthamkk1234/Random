package randomno.gowtham.com.random;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Objects;
import java.util.Random;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class CardFragment extends Fragment{
ImageView imageView;
Button button;
TextView textView;
AdView adViewcard;
    Random r;
    public CardFragment() {
        // Required empty public constructor
    }

    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    int i=0,j;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        try  {
            InputMethodManager imm = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(Objects.requireNonNull(getActivity().getCurrentFocus()).getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        View view=inflater.inflate(R.layout.fragment_card, container, false);
        final Integer[] images={
                R.drawable.c2,R.drawable.s2,R.drawable.d2,R.drawable.h2,
                R.drawable.c3,R.drawable.s3,R.drawable.d3,R.drawable.h3,
                R.drawable.c4,R.drawable.s4,R.drawable.d4,R.drawable.h4,
                R.drawable.c5,R.drawable.s5,R.drawable.d5,R.drawable.h5,
                R.drawable.c6,R.drawable.s6,R.drawable.d6,R.drawable.h6,
                R.drawable.c7,R.drawable.s7,R.drawable.d7,R.drawable.h7,
                R.drawable.c8,R.drawable.s8,R.drawable.d8,R.drawable.h8,
                R.drawable.c9,R.drawable.s9,R.drawable.d9,R.drawable.h9,
                R.drawable.c10,R.drawable.s10,R.drawable.d10,R.drawable.h10,
                R.drawable.ca,R.drawable.sa,R.drawable.da,R.drawable.ha,
                R.drawable.cj,R.drawable.sj,R.drawable.dj,R.drawable.hj,
                R.drawable.cq,R.drawable.sq,R.drawable.dq,R.drawable.hq,
                R.drawable.ck,R.drawable.sk,R.drawable.dk,R.drawable.hk
        };
        final String[] cardname={
                "Clubs 2","Spades 2","Diamonds 2","Hearts 2",
                "Clubs 3","Spades 3","Diamonds 3","Hearts 3",
                "Clubs 4","Spades 4","Diamonds 4","Hearts 4",
                "Clubs 5","Spades 5","Diamonds 5","Hearts 5",
                "Clubs 7","Spades 6","Diamonds 6","Hearts 6",
                "Clubs 7","Spades 7","Diamonds 7","Hearts 7",
                "Clubs 8","Spades 8","Diamonds 8","Hearts 8",
                "Clubs 9","Spades 9","Diamonds 9","Hearts 9",
                "Clubs 10","Spades 10","Diamonds 10","Hearts 10",
                "Clubs Ace","Spades Ace","Diamonds Ace","Hearts Ace",
                "Clubs Jack","Spades Jack","Diamonds Jack","Hearts Jack",
                "Clubs Queen","Spades Queen","Diamonds Queen","Hearts Queen",
                "Clubs King","Spades King","Diamonds King","Hearts King"

        };
        r=new Random();
        imageView= view.findViewById(R.id.cardimage);
        button= view.findViewById(R.id.cardgenbutton);
        textView= view.findViewById(R.id.cardtext);
        adViewcard=(AdView)view.findViewById(R.id.adViewcard);
        AdRequest adRequest = new AdRequest.Builder().build();
        adViewcard.loadAd(adRequest);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do {
                    i = r.nextInt(images.length);
                }
                while (i==j);
                j=i;
                if(i==0||i==1||i==4||i==5||i==8||i==9||i==12||i==13||i==16||i==17||i==21||i==20||i==24||i==25||i==28||i==29||i==32||i==33||
                        i==36||i==37||i==41||i==40||i==44||i==45||i==48||i==49) {
                    textView.setText(cardname[i].toUpperCase());
                    textView.setTextColor(Color.parseColor("#000000"));
                }
                else
                {
                    textView.setText(cardname[i].toUpperCase());
                    textView.setTextColor(Color.parseColor("#FF0000"));
                }
                imageView.setImageResource(images[i]);
            }
        });
        return view;
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