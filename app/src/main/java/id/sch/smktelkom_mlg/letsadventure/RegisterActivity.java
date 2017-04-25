package id.sch.smktelkom_mlg.letsadventure;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;

public class RegisterActivity extends AppCompatActivity {
    int pos;
    private String TAG = "RegisterActivity";
    private LinearLayout llProv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "OnStart");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Register");

        //                ---  Initialize spinner province
        final Spinner spnProvince = (Spinner) findViewById(R.id.spinnerProvince);
        //Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.province_array, android.R.layout.simple_spinner_item);
        //Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Apply the adapter to the spinner
        spnProvince.setAdapter(adapter);
        spnProvince.setPrompt(getResources().getString(R.string.choose1));

        final Spinner spnCity = (Spinner) findViewById(R.id.spinnerCity);
        llProv = (LinearLayout) findViewById(R.id.linearLayoutProv);

        spnProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String hm = spnProvince.getSelectedItem().toString();
                Log.d(TAG, "ItemSelected : " + position + ". " + hm);

                //HMMM
                pos = position;
                int[] array = {R.array.aceh_array, R.array.test_array, R.array.test_array, R.array.test_array, R.array.test_array,
                        R.array.test_array, R.array.test_array, R.array.test_array, R.array.test_array, R.array.test_array,
                        R.array.dki_array, R.array.jabar_array, R.array.banten_array, R.array.jateng_array, R.array.diy_array,
                        R.array.jatim_array, R.array.bali_array, R.array.test_array, R.array.test_array, R.array.test_array,};

                if (spnProvince.getSelectedItemPosition() == pos) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                            (getApplicationContext(), array[pos], android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnCity.setAdapter(adapter);
                    spnCity.setPrompt(getResources().getString(R.string.choose1));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_wna:
                if (checked)
                    llProv.setVisibility(View.GONE);
                break;
            case R.id.radio_wni:
                if (checked)
                    llProv.setVisibility(View.VISIBLE);
                break;
        }
    }
}
