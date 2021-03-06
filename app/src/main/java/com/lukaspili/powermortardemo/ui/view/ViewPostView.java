package com.lukaspili.powermortardemo.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lukaspili.powermortardemo.R;
import com.lukaspili.powermortardemo.app.DaggerService;
import com.lukaspili.powermortardemo.app.presenter.ViewPostPresenter;
import com.lukaspili.powermortardemo.app.presenter.screen.ViewPostScreenComponent;

import javax.inject.Inject;

import autodagger.AutoInjector;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Lukasz Piliszczuk <lukasz.pili@gmail.com>
 */
@AutoInjector(ViewPostPresenter.class)
public class ViewPostView extends LinearLayout {

    @Inject
    protected ViewPostPresenter presenter;

    @InjectView(R.id.title)
    public TextView titleTextView;

    @InjectView(R.id.content)
    public TextView contentTextView;

    public ViewPostView(Context context, AttributeSet attrs) {
        super(context, attrs);
        DaggerService.<ViewPostScreenComponent>getDaggerComponent(context).inject(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        presenter.takeView(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        presenter.dropView(this);
        super.onDetachedFromWindow();
    }

    @Override
    protected void onFinishInflate() {
        ButterKnife.inject(this);
    }
}
