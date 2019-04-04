package com.example.baijunling0405.view.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.baijunling0405.R;
import com.example.baijunling0405.model.ShowJson;

import java.util.List;

/**
 * @Auther: 白俊岭
 * @Date: 2019/4/4 10:31:27
 * @Description:
 */
public class MyAdapter extends BaseExpandableListAdapter {
    Context context; ShowJson showJson;
    private MyAdapter.parentViewHolder parentViewHolder;
    private Button del;
    private Button add;
    private List<ShowJson.DataBean.ListBean> list;
 int num1=0;

    public MyAdapter(Context context, ShowJson showJson) {
        this.context=context;
        this.showJson=showJson;
    }

    @Override
    public int getGroupCount() {
        return showJson.getData().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return showJson.getData().get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.parent, null);
        parentViewHolder = new parentViewHolder();
        parentViewHolder. fuBox = view.findViewById(R.id.fuBox);
        parentViewHolder. name = view.findViewById(R.id.name);

         parentViewHolder.fuBox.setChecked(showJson.getData().get(groupPosition).ischeck());
         parentViewHolder.name.setText(showJson.getData().get(groupPosition).getSellerName());

        //得到父类的数据
        ShowJson.DataBean dataBean = showJson.getData().get(groupPosition);

        //给父类设置点击事件
        parentViewHolder.fuBox.setChecked(dataBean.ischeck());
        parentViewHolder.fuBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox =(CheckBox) v;

                boolean checked = checkBox.isChecked();
                showJson.getData().get(groupPosition).ischeck(checked);
                selectGroup(groupPosition,checked);
                boolean selectAllGroup=isSelectAllGroup();
                checkBox.setChecked(selectAllGroup);
                notifyDataSetChanged();
            }
        });

        return view;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildViewHolder childViewHolder;
        View view = LayoutInflater.from(context).inflate(R.layout.child, null);
         childViewHolder = new ChildViewHolder();
        childViewHolder. zibox = view.findViewById(R.id.zibox);
        childViewHolder. img = view.findViewById(R.id.img);
        childViewHolder. Name = view.findViewById(R.id.Name);
        childViewHolder. price = view.findViewById(R.id.price);
        childViewHolder. num = view.findViewById(R.id.num);

        del = view.findViewById(R.id.del);
        add = view.findViewById(R.id.add);

        childViewHolder.zibox.setChecked(showJson.getData().get(groupPosition).getList().get(childPosition).ischeck());
        childViewHolder.Name.setText(showJson.getData().get(groupPosition).getList().get(childPosition).getTitle());
        childViewHolder.price.setText(showJson.getData().get(groupPosition).getList().get(childPosition).getPrice()+"");
        childViewHolder.num.setText(showJson.getData().get(groupPosition).getList().get(childPosition).getNum()+"");
        Glide.with(context)
                .load(showJson.getData().get(groupPosition).getList().get(childPosition).getImages())
                .into(childViewHolder.img);

        //得到子条目的数据
        list = showJson.getData().get(groupPosition).getList();
        //给子条目设置点击事件
        childViewHolder.zibox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox =(CheckBox)v;
                //得到父条目数据
                ShowJson.DataBean dataBean = showJson.getData().get(groupPosition);
                ShowJson.DataBean.ListBean listBean = showJson.getData().get(groupPosition).getList().get(childPosition);
                listBean.ischeck(checkBox.isChecked());
                boolean selectGroup=isSelectGroup(groupPosition);
                dataBean.ischeck(selectGroup);
                boolean selectAllGroup=isSelectAllGroup();
                checkBox.setChecked(selectAllGroup);
                notifyDataSetChanged();
            }
        });

        num1=showJson.getData().get(groupPosition).getList().get(childPosition).getNum();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1++;
                list.get(childPosition).setNum(num1);
              childViewHolder.num.setText(num1+"");
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num1<=1){
                    Toast.makeText(context,"不能再少了",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    num1--;
                    list.get(childPosition).setNum(num1);
                    childViewHolder.num.setText(num1+"");
                }
            }
        });

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    class  parentViewHolder{
         CheckBox fuBox;
         TextView name;
    }

    class ChildViewHolder{
        CheckBox zibox;
        ImageView img;
        TextView Name;
        TextView price;
        TextView num;
    }
    private void selectGroup(int groupPosition, boolean checked) {
        for (int i = 0; i <showJson.getData().get(groupPosition).getList().size() ; i++) {
            ShowJson.DataBean.ListBean listBean = showJson.getData().get(groupPosition).getList().get(i);
            listBean.ischeck(checked);
        }
    }
    private boolean isSelectAllGroup() {
        for (int i = 0; i <showJson.getData().size() ; i++) {
            ShowJson.DataBean dataBean = showJson.getData().get(i);
            boolean check =dataBean.ischeck();
            if (!check){
                return  false;
            }
        }
        return true;
    }
    private boolean isSelectGroup(int groupPosition) {
        for (int i = 0; i <showJson.getData().get(groupPosition).getList().size() ; i++) {
            ShowJson.DataBean.ListBean listBean = showJson.getData().get(groupPosition).getList().get(i);
            boolean check=listBean.ischeck();
            if (!check){
                return  false;
            }
        }
        return  true;
    }

}
