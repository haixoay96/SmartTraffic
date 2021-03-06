package com.gemvietnam.trafficgem.screen.leftmenu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.trafficgem.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * The LeftMenu Fragment
 */
public class LeftMenuFragment extends ViewFragment<LeftMenuContract.Presenter> implements LeftMenuContract.View {

  CircleImageView mProfileImg;
  TextView mNameTv;
  TextView mEmailTv;
  TextView mYourLocationTv;
  TextView mDirectionTv;
  ImageView mMinusImg;
  ImageView mPlusImg;
  TextView mNormalSearchTv;
  TextView mAdvanceSearchTv;
  TextView mTrafficStateTv;
  TextView mSignoutTv;
  LinearLayout mExpandLl;

  private final Map<TextView, MenuItem> mNavigationItemMap = new HashMap<>();

  public static LeftMenuFragment getInstance() {
    return new LeftMenuFragment();
  }

  private void bindView(View view){
    mProfileImg = (CircleImageView) view.findViewById(R.id.menu_profile_img);
    mNameTv = (TextView) view.findViewById(R.id.profile_name_tv);
    mEmailTv = (TextView) view.findViewById(R.id.profile_email_tv);
    mYourLocationTv = (TextView) view.findViewById(R.id.menu_your_location_tv);
    mDirectionTv = (TextView) view.findViewById(R.id.menu_direction_tv);
    mMinusImg = (ImageView) view.findViewById(R.id.menu_minus_img);
    mPlusImg = (ImageView) view.findViewById(R.id.menu_plus_img);
    mNormalSearchTv = (TextView) view.findViewById(R.id.menu_normal_search_tv);
    mAdvanceSearchTv = (TextView) view.findViewById(R.id.menu_advance_search_tv);
    mTrafficStateTv = (TextView) view.findViewById(R.id.menu_traffic_state_tv);
    mSignoutTv = (TextView) view.findViewById(R.id.menu_signout_tv);
    mExpandLl = (LinearLayout) view.findViewById(R.id.menu_expand_ll);

  }
  @Override
  protected int getLayoutId() {
    return R.layout.fragment_left_menu;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = super.onCreateView(inflater, container, savedInstanceState);
    //ButterKnife.bind(this, view);

    bindView(view);

    mMinusImg.setVisibility(View.VISIBLE);
    mPlusImg.setVisibility(View.GONE);
    mExpandLl.setVisibility(View.VISIBLE);

    onMenuItemClicked();

    return view;
  }

  private void onMenuItemClicked() {
    mYourLocationTv.setSelected(true);
    mYourLocationTv.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_location_selected, 0, 0, 0);

    mNavigationItemMap.put(mYourLocationTv, MenuItem.YOUR_LOCATION);
    mNavigationItemMap.put(mNormalSearchTv, MenuItem.NORMAL_SEARCH);
    mNavigationItemMap.put(mDirectionTv, MenuItem.DIRECTION);
    mNavigationItemMap.put(mAdvanceSearchTv, MenuItem.ADVANCE_SEARCH);
    mNavigationItemMap.put(mTrafficStateTv, MenuItem.TRAFFIC_STATE);

    mNavigationItemMap.put(mSignoutTv, MenuItem.SIGN_OUT);
    for (final Map.Entry<TextView, MenuItem> entry : mNavigationItemMap.entrySet()) {
      entry.getKey().setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          mPresenter.onMenuItemClicked(entry.getValue());

          // Change color
          unselectAll();
          entry.getKey().setSelected(true);

          int iconId = 0;
          switch (entry.getValue()) {
            case YOUR_LOCATION:
              iconId = R.drawable.ic_location_selected;
              break;
            case DIRECTION:
              iconId = R.drawable.ic_direction_selected;
              break;
            case TRAFFIC_STATE:
              iconId = R.drawable.ic_traffic_selected;
              break;
            case SIGN_OUT:
              iconId = R.drawable.ic_signout_white;
              break;
          }
          entry.getKey().setCompoundDrawablesRelativeWithIntrinsicBounds(iconId, 0,0,0);

          if (entry.getValue() == MenuItem.NORMAL_SEARCH || entry.getValue() == MenuItem.ADVANCE_SEARCH ||
              entry.getValue() == MenuItem.DIRECTION) {
            mDirectionTv.setSelected(true);
            mDirectionTv.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_direction_selected, 0, 0, 0);
          } else {
            mDirectionTv.setSelected(false);
            mDirectionTv.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_direction_white, 0, 0, 0);
          }
        }
      });
    }
  }

  private void unselectAll() {
    mYourLocationTv.setSelected(false);
    mYourLocationTv.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_location_white, 0,0,0);
    mDirectionTv.setSelected(false);
    mDirectionTv.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_direction_white, 0,0,0);
    mNormalSearchTv.setSelected(false);
    mAdvanceSearchTv.setSelected(false);
    mTrafficStateTv.setSelected(false);
    mTrafficStateTv.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_traffic_white, 0, 0,0);
    mSignoutTv.setSelected(false);
    mSignoutTv.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_signout_white, 0,0,0);
  }
}