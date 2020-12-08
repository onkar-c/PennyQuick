package com.penny.quick.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.penny.quick.R;
import com.penny.quick.adapters.TabLayoutViewPager.TabViewHolder;
import com.penny.quick.models.TabModel;
import com.penny.quick.ui.adapters.PlansAdapter;
import java.util.List;

public class TabLayoutViewPager extends RecyclerView.Adapter<TabViewHolder> {

  private final List<TabModel> tabModels;
  private final Context context;

  public TabLayoutViewPager(List<TabModel> tabModels, Context context) {
    this.tabModels = tabModels;
    this.context = context;
  }

  @NonNull
  @Override
  public TabViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.view_plans_list, parent, false);
    return new TabViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull TabViewHolder holder, int position) {
    holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
    PlansAdapter plansAdapter = new PlansAdapter(tabModels.get(position).getPlanModels(), context);
    holder.recyclerView.setAdapter(plansAdapter);
  }

  @Override
  public int getItemCount() {
    return tabModels.size();
  }

  public static class TabViewHolder extends RecyclerView.ViewHolder {

    RecyclerView recyclerView;

    public TabViewHolder(@NonNull View itemView) {
      super(itemView);
      recyclerView = itemView.findViewById(R.id.plans);
    }
  }
}
