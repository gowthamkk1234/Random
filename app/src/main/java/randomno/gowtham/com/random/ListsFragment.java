package randomno.gowtham.com.random;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import es.dmoral.toasty.Toasty;

import static android.content.ContentValues.TAG;
import static android.content.Context.INPUT_METHOD_SERVICE;


public class ListsFragment extends Fragment {
     Button addbtn,showbtn;
     EditText list_et;
     ListView listview;
     ListView listview2;
     AdView adViewlist;
     Random random;
     int i;
    String[] ListElements=new String[]{};
    String[] ShuffledElements=new String[]{};
    public ListsFragment() {
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
        final View view=inflater.inflate(R.layout.fragment_lists, container, false);
        //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        // Inflate the layout for this fragment
        addbtn= view.findViewById(R.id.addbtn);
        list_et= view.findViewById(R.id.list_editText);
        listview= view.findViewById(R.id.listview);
        listview2= view.findViewById(R.id.suflledlist);
        showbtn= view.findViewById(R.id.showbtn);
        adViewlist=(AdView)view.findViewById(R.id.adViewlists);
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Rect r = new Rect();
                view.getWindowVisibleDisplayFrame(r);
                int screenHeight = view.getRootView().getHeight();

                // r.bottom is the position above soft keypad or device button.
                // if keypad is shown, the r.bottom is smaller than that before.
                int keypadHeight = screenHeight - r.bottom;

                Log.d(TAG, "keypadHeight = " + keypadHeight);

                if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
                    // keyboard is opened
                    adViewlist.setVisibility(View.INVISIBLE);
                }
                else {
                    // keyboard is closed
                    adViewlist.setVisibility(View.VISIBLE);
                }
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        adViewlist.loadAd(adRequest);
        final List< String > ListElementsArrayList = new ArrayList< String >
                (Arrays.asList(ListElements));

        final List< String > ShuffledListElements = new ArrayList< String >
                (Arrays.asList(ShuffledElements));

        addbtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                String text=list_et.getText().toString().trim();
                if(text.equals(""))
                {
                    Toasty.error(Objects.requireNonNull(getContext()),"Enter a value", Toast.LENGTH_SHORT).show();
                }
                else {
                    ListElementsArrayList.add(text);
                    ShuffledListElements.add(text);
                    list_et.setText("");
                }
                Collections.shuffle(ShuffledListElements);
                final ArrayAdapter< String > adapter = new ArrayAdapter < String >
                        (Objects.requireNonNull(getActivity()), android.R.layout.simple_list_item_1,
                                ListElementsArrayList);
                listview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                //random=new Random();
                //i=random.nextInt(ListElementsArrayList.size());
                //ShuffledListElements.add(text);
            }
        });
        showbtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Collections.shuffle(ShuffledListElements);
                final ArrayAdapter< String > shuffledadapter = new ArrayAdapter < String >
                        (Objects.requireNonNull(getActivity()), android.R.layout.simple_list_item_1,
                                ShuffledListElements);
                listview2.setAdapter(shuffledadapter);
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
