package com.penny.quick.ui.activities.recent_recharge;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.penny.quick.R;

public class MonthBottomSheetDialog extends BottomSheetDialogFragment {

  private BottomSheetListener bottomSheetListener;
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.recent_recharge_month_bottom_sheet, container, false);
    v.findViewById(R.id.hi).setOnClickListener(view ->  {
      bottomSheetListener.onButtonClick("hi");
      dismiss();
    });
    v.findViewById(R.id.bye).setOnClickListener(view -> {
      bottomSheetListener.onButtonClick("bye");
      dismiss();
    });
    return v;
  }

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    bottomSheetListener = (BottomSheetListener) context;
  }

  public interface BottomSheetListener{
    void onButtonClick(String text);
  }
}
