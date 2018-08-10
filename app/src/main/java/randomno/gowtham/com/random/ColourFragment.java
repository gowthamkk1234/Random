package randomno.gowtham.com.random;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Objects;
import java.util.Random;

import static android.content.Context.CLIPBOARD_SERVICE;
import static android.content.Context.INPUT_METHOD_SERVICE;


public class ColourFragment extends Fragment {
LinearLayout linearLayout;
Button button;
TextView textView;
AdView adViewcolor;
private ClipboardManager myClipboard;
private ClipData clipData;
    public ColourFragment() {
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
        View view=inflater.inflate(R.layout.fragment_colour, container, false);
        // Inflate the layout for this fragment
        final String[] colour={"#FF5733","#CD5C5C","#F08080","#FA8072","#E9967A","#FFA07A","#DC143C","#FF0000","#B22222","#8B0000","#FFC0CB","#FFB6C1","#FF69B4","#FF1493","#C71585","#DB7093","#FFA07A","#FF7F50","#FF6347","#FF4500","#FF8C00","#FFA500","#FFD700","#FFFF00","#FFFFE0","#FFFACD","#FAFAD2","#FFEFD5","#FFE4B5","#FFDAB9","#EEE8AA","#F0E68C","#BDB76B","#E6E6FA",
        "#D8BFD8","#DDA0DD","#EE82EE","#DA70D6","#FF00FF","#FF00FF","#BA55D3","#9370DB","#663399","#8A2BE2","#9400D3","#9932CC","#8B008B","#800080","#4B0082","#6A5ACD","#483D8B","#7B68EE","#ADFF2F","#7FFF00","#7CFC00","#00FF00","#32CD32","#98FB98","#90EE90","#00FA9A","#00FF7F","#3CB371","#2E8B57","#228B22","#008000","#006400","#9ACD32","#6B8E23","#808000","#556B2F","#66CDAA",
        "#8FBC8B","#20B2AA","#008B8B","#008080","#00FFFF","#E0FFFF","#AFEEEE","#7FFFD4","#40E0D0","#48D1CC","#00CED1","#5F9EA0","#4682B4","#B0C4DE","#B0E0E6","#ADD8E6","#87CEEB","#87CEFA","#00BFFF","#1E90FF","#6495ED","#7B68EE","#4169E1","#0000FF","#0000CD","#00008B","#000080","#191970","#FFF8DC","#FFEBCD","#FFE4C4","#FFDEAD","#F5DEB3","#DEB887","#D2B48C","#BC8F8F","#F4A460",
        "#DAA520","#B8860B","#CD853F","#D2691E","#8B4513","#A0522D","#A52A2A","#800000","#FFFFFF","#FFFAFA","#F0FFF0","#F5FFFA","#F0FFFF","#F0F8FF","#F8F8FF","#F5F5F5","#FFF5EE","#F5F5DC","#FDF5E6","#FFFAF0","#FFFFF0","#FAEBD7","#FAF0E6","#FFF0F5","#FFE4E1","#DCDCDC","#D3D3D3","#C0C0C0","#A9A9A9","#808080","#696969","#778899","#708090","#2F4F4F","#000000"};
        linearLayout= view.findViewById(R.id.colorlayout);
        button= view.findViewById(R.id.colorbtn);
        textView= view.findViewById(R.id.colortext);
        adViewcolor=(AdView)view.findViewById(R.id.adViewcolour);
        AdRequest adRequest = new AdRequest.Builder().build();
        adViewcolor.loadAd(adRequest);
        myClipboard = (ClipboardManager) Objects.requireNonNull(getActivity()).getSystemService(Context.CLIPBOARD_SERVICE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idx=new Random().nextInt(colour.length);
                String r=(colour[idx]);
                linearLayout.setBackgroundColor(Color.parseColor(r));
                textView.setText(r);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=textView.getText().toString().trim();
                clipData=ClipData.newPlainText("s",s);
                myClipboard.setPrimaryClip(clipData);
                Toast.makeText(getContext(), "Text Copied",
                        Toast.LENGTH_SHORT).show();
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
