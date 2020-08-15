package com.penny.quick.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.penny.database.dao.RecentRecharge;
import com.penny.quick.R;
import com.penny.quick.models.BottomSheetCheckBox;
import java.util.List;

public class RecentRechargeBottomSheetAdapter extends
    RecyclerView.Adapter<RecentRechargeBottomSheetAdapter.MyViewHolder> {

  private List<BottomSheetCheckBox> values;
  private Context context;

  public RecentRechargeBottomSheetAdapter(List<BottomSheetCheckBox> values, Context context) {
    this.values = values;
    this.context = context;
  }

  @NonNull
  @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.bottom_sheet_check_box_item, parent, false);
    return new MyViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    holder.text.setText(values.get(position).getTitle());
  }

  @Override
  public int getItemCount() {
    return values.size();
  }

  static class MyViewHolder extends ViewHolder {

    private TextView text, checkBox;

    public MyViewHolder(@NonNull View itemView) {
      super(itemView);
      text = itemView.findViewById(R.id.text);
      checkBox = itemView.findViewById(R.id.check_box);
    }
  }
}
