package ru.medvedovo.tablayouttest.other;

import android.os.Bundle;

import ru.medvedovo.tablayouttest.presentation.tabs.TabsFragment;

public final class FragmentFabric {
    public enum Type {
        TABS,
        LIST,
        DETAILS
    }

    public static IFragment create(Type type, Bundle bundle) {
        switch (type) {
            case TABS:
                return TabsFragment.newInstance(bundle);
            case LIST:
                return null;
            case DETAILS:
                return null;
        }
        return null;
    }

    public interface IFragment {
        Type getType();
        void onBackPressed();
    }
}
