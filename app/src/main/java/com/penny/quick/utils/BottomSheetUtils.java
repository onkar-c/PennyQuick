package com.penny.quick.utils;

import android.app.Activity;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.penny.quick.R;
import com.penny.quick.models.BottomSheetListObject;
import com.penny.quick.ui.adapters.BottomSheetAdapter;
import com.penny.quick.ui.adapters.BottomSheetAdapter.BottomSheetListItemClickListener;
import java.util.List;

public class BottomSheetUtils {

  private Activity context;
  private BottomSheetBehavior sheetBehavior;
  private RelativeLayout bottomSheet;
  private RecyclerView bottomSheetRV;

  public BottomSheetBehavior setUpBottomSheet(Activity context) {
    this.context = context;
    bottomSheet = context.findViewById(R.id.bottom_sheet);
    sheetBehavior = BottomSheetBehavior.from(bottomSheet);

    bottomSheet.findViewById(R.id.bt_close)
        .setOnClickListener(onCloseClick);
    return sheetBehavior;
  }

  public void setList( List<BottomSheetListObject> objectList,BottomSheetListItemClickListener clickListener){
    bottomSheetRV = bottomSheet.findViewById(R.id.rv_bottom_sheet_list);
    bottomSheetRV.setLayoutManager(new LinearLayoutManager(context));
    BottomSheetAdapter bottomSheetAdapter = new BottomSheetAdapter(context, objectList,
        clickListener);
    bottomSheetRV.setAdapter(bottomSheetAdapter);
  }

  OnClickListener onCloseClick = view -> {
    sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
  };
}
