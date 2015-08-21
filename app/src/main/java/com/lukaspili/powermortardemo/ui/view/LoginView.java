package com.lukaspili.powermortardemo.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lukaspili.powermortardemo.R;
import com.lukaspili.powermortardemo.app.DaggerService;
import com.lukaspili.powermortardemo.app.presenter.LoginPresenter;
import com.lukaspili.powermortardemo.app.presenter.screen.LoginScreen;
import com.lukaspili.powermortardemo.app.presenter.screen.LoginScreenComponent;
import com.lukaspili.powermortardemo.app.presenter.screen.ViewPostScreen;
import com.lukaspili.powermortardemo.model.Post;
import com.lukaspili.powermortardemo.mortar.ScreenScoper;

import javax.inject.Inject;

import autodagger.AutoInjector;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import flow.Flow;
import mortar.MortarScope;

/**
 * @author Lukasz Piliszczuk <lukasz.pili@gmail.com>
 */
@AutoInjector(LoginPresenter.class)
public class LoginView extends LinearLayout {

    @Inject
    protected LoginPresenter presenter;

    @InjectView(R.id.hello)
    protected TextView hello;

    public LoginView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 1. Create screen
        LoginScreen screen = new LoginScreen();

        // 2. Create screen scoper
        ScreenScoper screenScoper = new ScreenScoper();

        // 3. Create scope
        MortarScope scope = screenScoper.getScreenScope(context, screen.getClass().getName(), screen);

        // OPTION 1
        // 4. Create child context wrapped with Mortar
        Context loginContext = scope.createContext(context);

        // 5. Inject
        DaggerService.<LoginScreenComponent>getDaggerComponent(loginContext).inject(this);


        // OPTION 2
        // 4. Get component from mortar scope
        LoginScreenComponent component = scope.getService(DaggerService.SERVICE_NAME);

        // 5. Inject
        component.inject(this);


        // REMINDER: Option 1 and option 2 are doing the same thing. Choose one.
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
        super.onFinishInflate();
        ButterKnife.inject(this);
    }

    @OnClick
    void click() {
        presenter.click();
    }

    @OnClick(R.id.hello)
    void helloClick() {
        Post post = new Post();
        post.setId(1);
        post.setUserId(10);
        post.setBody("my body");
        post.setTitle("title");
        Flow.get(getContext()).set(new ViewPostScreen(post));
    }
}
