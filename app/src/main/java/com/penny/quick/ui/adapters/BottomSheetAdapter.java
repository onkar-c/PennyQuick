package com.penny.quick.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.penny.database.ProjectConstants;
import com.penny.database.entities.Operators;
import com.penny.quick.R;
import com.penny.quick.utils.CommonUtils;
import java.util.List;

public class BottomSheetAdapter extends
    RecyclerView.Adapter<BottomSheetAdapter.BottomSheetListItemVH> {

  private final List<Operators> objectList;
  private final BottomSheetListItemClickListener clickListener;

  public BottomSheetAdapter(List<Operators> objectList,
      BottomSheetListItemClickListener clickListener) {
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
    Operators object = objectList.get(position);
    holder.txtItemName.setText(object.getDisplay_name());
    CommonUtils.getImage(holder.itemView.getContext(), ProjectConstants.IMAGE_URL + object.getUrl(),
        holder.imgIcon, R.drawable.hamburger_icon);
    holder.setDetails(object, clickListener);
  }

  @Override
  public int getItemCount() {

    return objectList.size();
  }

  public interface BottomSheetListItemClickListener {

    void onBottomSheetListItemClick(Operators obj);
  }

  public static class BottomSheetListItemVH extends ViewHolder {

    private final TextView txtItemName;
    private final ImageView imgIcon;

    public BottomSheetListItemVH(@NonNull View itemView) {
      super(itemView);
      imgIcon = itemView.findViewById(R.id.iv_icon);
      txtItemName = itemView.findViewById(R.id.tv_name);
    }

    public void setDetails(Operators object, BottomSheetListItemClickListener clickListener) {
      itemView.setOnClickListener(view -> clickListener.onBottomSheetListItemClick(object));
    }
  }
}
