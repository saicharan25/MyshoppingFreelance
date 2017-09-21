package info.devexchanges.navvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import info.devexchanges.navvp.R;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private Map<String, List<String>> mExpandableListDetail;
    private List<String> mExpandableListTitle;
    private LayoutInflater mLayoutInflater = ((LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE));

    public CustomExpandableListAdapter(Context context, List<String> expandableListTitle, Map<String, List<String>> expandableListDetail) {
        this.mContext = context;
        this.mExpandableListTitle = expandableListTitle;
        this.mExpandableListDetail = expandableListDetail;
    }

    public Object getChild(int listPosition, int expandedListPosition) {
        return ((List) this.mExpandableListDetail.get(this.mExpandableListTitle.get(listPosition))).get(expandedListPosition);
    }

    public long getChildId(int listPosition, int expandedListPosition) {
        return (long) expandedListPosition;
    }

    public View getChildView(int listPosition, int expandedListPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            convertView = this.mLayoutInflater.inflate(R.layout.list_item, null);
        }
        ((TextView) convertView.findViewById(R.id.expandedListItem)).setText(expandedListText);
        return convertView;
    }

    public int getChildrenCount(int listPosition) {
        return ((List) this.mExpandableListDetail.get(this.mExpandableListTitle.get(listPosition))).size();
    }

    public Object getGroup(int listPosition) {
        return this.mExpandableListTitle.get(listPosition);
    }

    public int getGroupCount() {
        return this.mExpandableListTitle.size();
    }

    public long getGroupId(int listPosition) {
        return (long) listPosition;
    }

    public View getGroupView(int listPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            convertView = this.mLayoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, 1);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    public boolean hasStableIds() {
        return false;
    }

    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
