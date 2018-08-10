package randomno.gowtham.com.random;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Objects;
import java.util.Random;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static android.widget.Toast.makeText;


public class DiceFragment extends Fragment{
ImageView imageView1,imageView2,imageView3,imageView4;
TextView textView1,textView2,textView3,textView4;
Button button1,button2,button3,button4;
AdView adViewdice;
    public DiceFragment() {
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
        final View view=inflater.inflate(R.layout.fragment_dice,container,false);
        // Inflate the layout for this fragment
        imageView1= view.findViewById(R.id.d1);
        imageView2= view.findViewById(R.id.d2);
        imageView3= view.findViewById(R.id.d3);
        imageView4= view.findViewById(R.id.d4);
        textView1= view.findViewById(R.id.t1);
        textView2= view.findViewById(R.id.t2);
        textView3= view.findViewById(R.id.t3);
        textView4= view.findViewById(R.id.t4);
        button1= view.findViewById(R.id.b1);
        button2= view.findViewById(R.id.b2);
        button3= view.findViewById(R.id.b3);
        button4= view.findViewById(R.id.b4);
        adViewdice=(AdView)view.findViewById(R.id.adViewdice);
        AdRequest adRequest = new AdRequest.Builder().build();
        adViewdice.loadAd(adRequest);
        final int[] images={R.drawable.dice1,
                R.drawable.dice2,
                R.drawable.dice3,
                R.drawable.dice4,
                R.drawable.dice5,
                R.drawable.dice6};
        final int whitebg=R.drawable.hidedice;
        final String[] count={"ONE","TWO","THREE","FOUR","FIVE","SIX"};
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=new Random().nextInt(images.length);
                imageView1.setImageResource(images[i]);
                textView1.setText(count[i]);
                imageView2.setImageResource(whitebg);
                textView2.setText("");
                imageView3.setImageResource(whitebg);
                textView3.setText("");
                imageView4.setImageResource(whitebg);
                textView4.setText("");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=new Random().nextInt(images.length);
                int j=new Random().nextInt(images.length);
                imageView1.setImageResource(images[i]);
                textView1.setText(count[i]);
                imageView2.setImageResource(images[j]);
                textView2.setText(count[j]);
                imageView3.setImageResource(whitebg);
                textView3.setText("");
                imageView4.setImageResource(whitebg);
                textView4.setText("");

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int i=new Random().nextInt(images.length);
                int j=new Random().nextInt(images.length);
                int k=new Random().nextInt(images.length);
                imageView1.setImageResource(images[i]);
                textView1.setText(count[i]);
                imageView2.setImageResource(images[j]);
                textView2.setText(count[j]);
                imageView3.setImageResource(images[k]);
                textView3.setText(count[k]);
                imageView4.setImageResource(whitebg);
                textView4.setText("");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int i=new Random().nextInt(images.length);
                int j=new Random().nextInt(images.length);
                int k=new Random().nextInt(images.length);
                int l=new Random().nextInt(images.length);
                imageView1.setImageResource(images[i]);
                textView1.setText(count[i]);
                imageView2.setImageResource(images[j]);
                textView2.setText(count[j]);
                imageView3.setImageResource(images[k]);
                textView3.setText(count[k]);
                imageView4.setImageResource(images[l]);
                textView4.setText(count[l]);
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
