package com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.github.aakumykov.basic_mvp_recyclerview_2.BuildConfig;
import com.github.aakumykov.basic_mvp_recyclerview_2.R;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.basic_activity.BMVP_Activity;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.enums.eBasicSortingMode;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.exceptions.UnknownViewModeException;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iBMVP_PageView;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iBasicViewState;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iSortingMode;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.system_config.ActivityCodes;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.utils.BasicMVPList_Utils;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.utils.DeviceUtils;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.utils.ImageUtils;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.utils.RecyclerViewUtils;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.utils.TextUtils;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.utils.ViewUtils;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.utils.builders.SortingMenuItem;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_config.GridViewConfig;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_model.BasicMVPList_ViewModel;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_model.BasicMVPList_ViewModelFactory;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_modes.BasicViewMode;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_modes.FeedViewMode;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_modes.GridViewMode;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_modes.ListViewMode;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_states.AllItemsSelectedViewState;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_states.CancelableProgressViewState;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_states.ErrorViewState;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_states.ListFilteredViewState;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_states.NeutralViewState;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_states.ProgressViewState;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_states.RefreshingViewState;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_states.SomeItemsSelectedViewState;

import butterknife.BindView;

public abstract class BasicMVPList_View
        extends BMVP_Activity
        implements iBMVP_PageView
{
    @Nullable @BindView(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.messageView) TextView messageView;

    private static final String TAG = BasicMVPList_View.class.getSimpleName();

    protected BasicMVPList_ViewModel mViewModel;
    protected BasicMVPList_Presenter mPresenter;
    protected BasicMVPList_DataAdapter mDataAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected RecyclerView.ItemDecoration mItemDecoration;

    protected Menu mMenu;
    protected MenuInflater mMenuInflater;
    protected SubMenu mSortingSubmenu;

    protected int mActivityRequestCode = Integer.MAX_VALUE;
    protected int mActivityResultCode = Integer.MAX_VALUE;
    protected Intent mActivityResultData;

    private SearchView mSearchView;

    // Абстрактные методы
    protected abstract void setActivityView();
    protected abstract BasicMVPList_Presenter preparePresenter();
    protected abstract BasicMVPList_DataAdapter prepareDataAdapter();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActivityView();

        mViewModel = prepareViewModel();
        mPresenter = preparePresenter();
        mDataAdapter = prepareDataAdapter();

        reconfigureRecyclerView();
        configureSwipeRefresh();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mActivityRequestCode = requestCode;
        mActivityResultCode = resultCode;
        mActivityResultData = data;
    }

    @Override
    protected void onStart() {
        super.onStart();

        mPresenter.bindViews(this, mDataAdapter);

        processActivityResult();
        forgetActivityResult();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (null != mPresenter)
            mPresenter.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        mMenu = menu;
        mMenuInflater = getMenuInflater();

        setMenuIconsVisible(menu);

        mPresenter.onOptionsMenuCreated();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                // FIXME: сделать корректно с т.з. навигации
                onBackPressed();
                break;

            case R.id.actionSortByName:
                mPresenter.onSortMenuItemClicked(eBasicSortingMode.BY_NAME);
                break;

            case R.id.actionSortByDate:
                mPresenter.onSortMenuItemClicked(eBasicSortingMode.BY_DATE);
                break;

            case R.id.actionSelectAll:
                mPresenter.onSelectAllClicked();
                break;

            case R.id.actionClearSelection:
                mPresenter.onClearSelectionClicked();
                break;

            case R.id.actionInvertSelection:
                mPresenter.onInvertSelectionClicked();
                break;

            case R.id.actionInterrupt:
                mPresenter.onInterruptRunningProcessClicked();
                break;

            case R.id.actionViewModeList:
                mPresenter.onViewModeListClicked();
                break;

            case R.id.actionViewModeGrid:
                mPresenter.onViewModeGridClicked();
                break;

            case R.id.actionViewModeFeed:
                mPresenter.onViewModeFeedClicked();
                break;

            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (!mPresenter.onBackPressed())
            super.onBackPressed();
    }

    @Override
    public abstract void setDefaultPageTitle();

    @Override
    public RecyclerView.ItemDecoration createBasicItemDecoration(BasicViewMode viewMode) {
        if (viewMode instanceof ListViewMode)
            return RecyclerViewUtils.createSimpleDividerItemDecoration(this, R.drawable.simple_list_item_divider);
        return null;
    }

    @Override
    public void setViewState(iBasicViewState viewState) {
        if (viewState instanceof NeutralViewState) {
            setNeutralViewState();
        }
        else if (viewState instanceof ListFilteredViewState) {
            setListFilteredViewState((ListFilteredViewState) viewState);
        }
        else if (viewState instanceof CancelableProgressViewState) {
            setCancelableProgressViewState((CancelableProgressViewState) viewState);
        }
        else if (viewState instanceof ProgressViewState) {
            setProgressViewState((ProgressViewState) viewState);
        }
        else if (viewState instanceof RefreshingViewState) {
            setRefreshingViewState();
        }
        // AllItemsSelectedViewState - частный случай SomeItemsSelectedViewState,
        // поэтому должен идти перед ним.
        else if (viewState instanceof AllItemsSelectedViewState) {
            setAllSelectedViewState((AllItemsSelectedViewState) viewState);
        }
        else if (viewState instanceof SomeItemsSelectedViewState) {
            setItemSelectedViewState((SomeItemsSelectedViewState) viewState);
        }
        else if (viewState instanceof ErrorViewState) {
            setErrorViewState((ErrorViewState) viewState);
        }
        else
            throw new RuntimeException("Unknown view state: "+viewState);
    }

    private void setListFilteredViewState(ListFilteredViewState listFilteredViewState) {
//        setNeutralViewState();
        restoreSearchView(listFilteredViewState.getFilterText());
    }

    @Override
    public Intent getInputIntent() {
        return getIntent();
    }

    @Override
    public void refreshMenu() {
        invalidateOptionsMenu();
    }

    @Override
    public void restoreSearchView(String filterText) {
//        invalidateOptionsMenu();

        if (null != mSearchView) {
            mSearchView.setQuery(filterText, false);
            mSearchView.setIconified(false);
            mSearchView.clearFocus();
        }
    }

    @Override
    public void scroll2position(int position) {
        if (mLayoutManager instanceof LinearLayoutManager)
            ((LinearLayoutManager) mLayoutManager).scrollToPositionWithOffset(position, 0);
        else
            mLayoutManager.scrollToPosition(position);
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }

    @Override
    public Context getPageContext() {
        return this;
    }

    @Override
    public String getText(int stringResourceId, Object... formatArgs) {
        return TextUtils.getText(this, stringResourceId, formatArgs);
    }

    @Override
    public void reconfigureRecyclerView() {

        mLayoutManager = prepareLayoutManager(mPresenter.getCurrentViewMode());

        mItemDecoration = prepareItemDecoration(mPresenter.getCurrentViewMode());

        BasicMVPList_Utils.configureRecyclerview(
                getRecyclerView(),
                mDataAdapter,
                mLayoutManager,
                mItemDecoration
        );
    }

    @Override
    public abstract int getListScrollOffset();

    @Override
    public abstract void setListScrollOffset(int offset);

    @Override
    public void runDelayed(@NonNull Runnable runnable, long delay) {
        findViewById(android.R.id.content)
                .postDelayed(runnable, delay);
    }


    public abstract void assembleMenu();

    @Override
    public void resetMenu() {
        clearMenu();
        assembleMenu();
    }

    public abstract RecyclerView.ItemDecoration prepareItemDecoration(BasicViewMode viewMode);


    public void hideProgressMessage() {
        hideProgressBar();
        hideMessage();
    }

    public void hideMessage() {
        ViewUtils.hide(messageView);
    }

    public void hideProgressBar() {
        ViewUtils.hide(progressBar);
    }



    protected abstract RecyclerView getRecyclerView();

    protected RecyclerView.LayoutManager createGridModeLayoutManager() {
        int colsCount = getColumnsCountForGridLayout(DeviceUtils.getOrientation(this));

        return new StaggeredGridLayoutManager(colsCount, StaggeredGridLayoutManager.VERTICAL);
    }

    protected int getColumnsCountForGridLayout(int orientation) {
        return (Configuration.ORIENTATION_PORTRAIT == orientation) ?
                GridViewConfig.GRID_COLUMNS_COUNT_PORTRAIT :
                GridViewConfig.GRID_COLUMNS_COUNT_LANDSCAPE;
    }

    protected RecyclerView.LayoutManager createLinearModeLayoutManager() {
        return new LinearLayoutManager(this);
    }

    protected void setNeutralViewState() {
        setDefaultPageTitle();

        resetMenu();

        hideRefreshThrobber();
        hideProgressMessage();
    }

    protected void setCancelableProgressViewState(CancelableProgressViewState viewState) {
        setProgressViewState(viewState);
        showInterruptButton();
    }

    protected void setProgressViewState(ProgressViewState progressViewState) {
        if (progressViewState.hasStringMessage())
            showProgressMessage(progressViewState.getStringMessage());
        else
            showProgressMessage(progressViewState.getMessageId());
    }

    protected void setRefreshingViewState() {
        showRefreshThrobber();
    }

    protected void setErrorViewState(ErrorViewState errorViewState) {
        setNeutralViewState();
        showErrorMsg(errorViewState.getMessageId(), errorViewState.getDebugMessage());
    }

    protected void setItemSelectedViewState(SomeItemsSelectedViewState viewState) {
        showSelectionMenu();
        showSelectedItemsCount(viewState.getSelectedItemsCount());
    }

    protected void setAllSelectedViewState(AllItemsSelectedViewState viewState) {
        showAllSelectedMenu();
        showSelectedItemsCount(viewState.getSelectedItemsCount());
    }

    protected void processActivityResult() {
        switch (mActivityRequestCode) {
            case ActivityCodes.CODE_LOGIN_REQUEST:
                processLoginResult();
                break;

            default:
                Log.w(TAG, "Unknown request code: "+mActivityRequestCode);
                break;
        }
    }



    // Внутренние
    private BasicMVPList_ViewModel prepareViewModel() {
        return new ViewModelProvider(this, new BasicMVPList_ViewModelFactory())
                .get(BasicMVPList_ViewModel.class);
    }

    private void configureSwipeRefresh() {
        if (null == swipeRefreshLayout)
            return;

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onRefreshRequested();
            }
        });
    }

    private RecyclerView.LayoutManager prepareLayoutManager(BasicViewMode viewMode) {

        if (viewMode instanceof ListViewMode || viewMode instanceof FeedViewMode) {
            return createLinearModeLayoutManager();
        }
        else if (viewMode instanceof GridViewMode) {
            return createGridModeLayoutManager();
        }
        else {
            throw new UnknownViewModeException(viewMode);
        }
    }

    protected void addSingleItemMenu(int menuResourceId, int itemId, int showAsAction) {

        mMenuInflater.inflate(menuResourceId, mMenu);

        // Если пункт меню настроен как скрытый, меняю цвет иконки на серый
        if (MenuItem.SHOW_AS_ACTION_NEVER == showAsAction)
        {
            MenuItem menuItem = mMenu.findItem(itemId);

            Drawable menuItemIcon = menuItem.getIcon();

            if (null != menuItemIcon) {
                Drawable wrappedDrawable = DrawableCompat.wrap(menuItemIcon);
                int color = getResources().getColor(R.color.menu_icon_grey);
                DrawableCompat.setTint(wrappedDrawable, color);
                menuItem.setIcon(wrappedDrawable);
            }
        }
    }

    protected void addChangeViewModeMenu() {
        inflateMenu(R.menu.change_view_mode);
    }

    protected void addSearchView() {

        if (null == mMenuInflater || null == mMenu)
            return;

        mMenuInflater.inflate(R.menu.search_view, mMenu);

        MenuItem searchMenuItem = mMenu.findItem(R.id.actionSearch);

        if (null != searchMenuItem) {
            mSearchView = (SearchView) searchMenuItem.getActionView();
            mSearchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

            mSearchView.setOnSearchClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.onSearchViewOpened();
                }
            });

            mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    mPresenter.onSearchViewClosed();
                    return false;
                }
            });

            mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    mPresenter.onSearchViewTextSubmitted(s);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    mPresenter.onSearchViewTextChanged(s);
                    return false;
                }
            });

            mPresenter.onSearchViewCreated();
        }
    }

    protected void addSortingMenuRootIfNotExists() {
        if (null != mMenu) {
            if (null == mMenu.findItem(R.id.actionSort)) {
                mMenuInflater.inflate(R.menu.sort, mMenu);
                mSortingSubmenu = mMenu.findItem(R.id.actionSort).getSubMenu();
            }
        }
    }

    protected void addAuthorizationMenu() {
        /*if (AuthSingleton.isLoggedIn())
            inflateMenu(R.menu.logout);
        else
            inflateMenu(R.menu.login);*/
    }

    protected void addSortByNameMenu() {

        addSortingMenuRootIfNotExists();

        new SortingMenuItem.Builder()
                .addMenuInflater(mMenuInflater)
                .addRootMenu(mSortingSubmenu)
                .addInflatedMenuResource(R.menu.sort_by_name)
                .addInflatedMenuItemId(R.id.actionSortByName)
                .addSortingMode(mPresenter.getCurrentSortingMode())
                .addSortingOrder(mPresenter.getCurrentSortingOrder())
                .addSortingModeParamsCallback(new SortingMenuItem.iSortingModeParamsCallback() {
                    @Override
                    public boolean isSortingModeComplains(iSortingMode sortingMode) {
                        return sortingMode instanceof eBasicSortingMode;
                    }

                    @Override
                    public boolean isSortingModeActive(iSortingMode sortingMode) {
                        return eBasicSortingMode.BY_NAME.equals(sortingMode);
                    }
                })
                .create();
    }

    protected void addSortByDateMenu() {

        addSortingMenuRootIfNotExists();

        new SortingMenuItem.Builder()
                .addMenuInflater(mMenuInflater)
                .addRootMenu(mSortingSubmenu)
                .addInflatedMenuResource(R.menu.sort_by_date)
                .addInflatedMenuItemId(R.id.actionSortByDate)
                .addSortingMode(mPresenter.getCurrentSortingMode())
                .addSortingOrder(mPresenter.getCurrentSortingOrder())
                .addSortingModeParamsCallback(new SortingMenuItem.iSortingModeParamsCallback() {
                    @Override
                    public boolean isSortingModeComplains(iSortingMode sortingMode) {
                        return sortingMode instanceof eBasicSortingMode;
                    }

                    @Override
                    public boolean isSortingModeActive(iSortingMode sortingMode) {
                        return eBasicSortingMode.BY_DATE.equals(sortingMode);
                    }
                })
                .create();
    }

    protected void makeMenuItemVisible(int menuItemId) {
        if (null != mMenu) {

            MenuItem sortMenuItem = mMenu.findItem(menuItemId);

            if (null != sortMenuItem) {

                sortMenuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

                Drawable menuItemIcon = sortMenuItem.getIcon();

                if (null != menuItemIcon) {
                    Drawable drawable = menuItemIcon.mutate();
                    ImageUtils.setDrawableColor(this, drawable, R.color.visible_menu_icon_color);
                }
            }
        }
    }

    private void processLoginResult() {
        refreshMenu();
    }

    private void showProgressMessage(Object data) {
        showMessage(data);
        showProgressBar();
    }

    public void showProgressBar() {
        View progressBar = findViewById(R.id.progressBar);
        if (null != progressBar)
            ViewUtils.show(progressBar);
    }

    public void showRefreshThrobber() {
        if (null != swipeRefreshLayout)
            swipeRefreshLayout.setRefreshing(true);
    }

    public void hideRefreshThrobber() {
        if (null != swipeRefreshLayout)
            swipeRefreshLayout.setRefreshing(false);
    }

    public void showErrorMsg(int userMessageId, String debugMessage) {
        setNeutralViewState();

        String msg = TextUtils.getText(this, userMessageId);

        if (BuildConfig.DEBUG)
            msg += ": " + debugMessage;

        showMessage(msg);
        setMessageColor(R.color.colorErrorText);
    }

    public void showErrorMsg(Object data) {
        setNeutralViewState();

        showMessage(data);
        setMessageColor(R.color.colorErrorText);
    }

    private void showMessage(Object data) {
        if (data instanceof Integer)
            messageView.setText((int) data);
        else if (data instanceof String)
            messageView.setText((String) data);
        else {
            Log.e(TAG, "Unsupported data type: " + data);
            return;
        }
        ViewUtils.show(messageView);
    }

    private void setMessageColor(int colorId) {
        int color = getResources().getColor(colorId);
        messageView.setTextColor(color);
    }

    private void showSelectionViewState(Object viewStateData) {
        showSelectionMenu();
        showSelectedItemsCount(viewStateData);
    }

    private void showSelectionMenu() {
        clearMenu();
        inflateMenu(R.menu.item_select_all);
        inflateMenu(R.menu.item_invert_selection);
        inflateMenu(R.menu.item_clear_selection);
    }

    private void showAllSelectedViewState(Object viewStateData) {
        showAllSelectedMenu();
        showSelectedItemsCount(viewStateData);
    }

    private void showAllSelectedMenu() {
        clearMenu();
        mMenuInflater.inflate(R.menu.item_clear_selection, mMenu);
    }

    private void showSelectedItemsCount(Object viewStateData) {
        String title = getString(R.string.page_title_selected_items_count, (Integer) viewStateData);
        BMVP_setPageTitle(title);
    }

    private void showInterruptButton() {
        clearMenu();
        inflateMenu(R.menu.progress_interrupt);
        BMVP_setPageTitle(R.string.menu_interrupt);
    }

    private void forgetActivityResult() {
        mActivityRequestCode = Integer.MAX_VALUE;
        mActivityResultCode = Integer.MAX_VALUE;
        mActivityResultData = null;
    }

    protected void clearMenu() {
        if (null != mMenu)
            mMenu.clear();
    }

    protected void inflateMenu(int menuResourceId) {
        if (null != mMenuInflater && null != mMenu)
            mMenuInflater.inflate(menuResourceId, mMenu);
    }


    @SuppressLint("RestrictedApi")
    private void setMenuIconsVisible(Menu menu) {
        if(menu instanceof MenuBuilder){
            MenuBuilder menuBuilder = (MenuBuilder) menu;
            menuBuilder.setOptionalIconsVisible(true);
        }
    }
}
