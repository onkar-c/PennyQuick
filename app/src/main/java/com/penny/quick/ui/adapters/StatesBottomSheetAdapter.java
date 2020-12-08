package com.penny.quick.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.penny.database.entities.State;
import com.penny.quick.R;
import java.util.List;

public class StatesBottomSheetAdapter extends
    RecyclerView.Adapter<StatesBottomSheetAdapter.BottomSheetListItemVH> {

  private final List<State> objectList;
  private final StateBottomSheetListItemClickListener clickListener;

  public StatesBottomSheetAdapter(List<State> objectList,
      StateBottomSheetListItemClickListener clickListener) {
    this.objectList = objectList;
    this.clickListener = clickListener;
  }

  @NonNull
  @Override
  public BottomSheetListItemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.mobile_operator_list_item, parent, false);
    return new BottomSheetListItemVH(view);
  }

  @Override
  public void onBindViewHolder(@NonNull BottomSheetListItemVH holder, int position) {
    State object = objectList.get(position);
    holder.txtItemName.setText(object.getDisplayName());
    holder.imgIcon.setVisibility(View.GONE);
    holder.setDetails(object, clickListener);
  }

  @Override
  public int getItemCount() {

    return objectList.size();
  }

  public interface StateBottomSheetListItemClickListener {

    void onBottomSheetListItemClick(State obj);
  }

  public static class BottomSheetListItemVH extends ViewHolder {

    private final ImageView imgIcon;
    private final TextView txtItemName;

    public BottomSheetListItemVH(@NonNull View itemView) {
      super(itemView);
      imgIcon = itemView.findViewById(R.id.iv_icon);
      txtItemName = itemView.findViewById(R.id.tv_name);
    }

    public void setDetails(State object, StateBottomSheetListItemClickListener clickListener) {
      itemView.setOnClickListener(view -> clickListener.onBottomSheetListItemClick(object));
    }
  }
}
