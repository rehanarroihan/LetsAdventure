package id.sch.smktelkom_mlg.letsadventure.interfaces;

import android.view.View;

/**
 * Created by Rehan on 26/04/2017.
 */

public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}