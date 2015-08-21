package com.lukaspili.powermortardemo.mortar;

import android.content.Context;

import com.lukaspili.powermortardemo.app.DaggerService;
import com.lukaspili.powermortardemo.ui.activity.RootActivity;

import automortar.ScreenComponentFactory;
import flow.path.Path;
import mortar.MortarScope;

/**
 * @author Lukasz Piliszczuk - lukasz.pili@gmail.com
 */
public class ScreenScoper {

    public MortarScope getScreenScope(Context context, String name, Path path) {
        MortarScope parentScope = MortarScope.getScope(context);

        if (parentScope == null) {
            parentScope = RootActivity.buildScope(context.getApplicationContext(),
                    RootActivity.class.getName());
        }

        MortarScope childScope = parentScope.findChild(name);
        if (childScope != null) {
            return childScope;
        }

        if (!(path instanceof ScreenComponentFactory)) {
            throw new IllegalStateException("Path must imlement ComponentFactory");
        }
        ScreenComponentFactory screenComponentFactory = (ScreenComponentFactory) path;
        Object component = screenComponentFactory.createComponent(parentScope.getService(DaggerService.SERVICE_NAME));

        MortarScope.Builder builder = parentScope.buildChild()
                .withService(DaggerService.SERVICE_NAME, component);

        return builder.build(name);
    }
}