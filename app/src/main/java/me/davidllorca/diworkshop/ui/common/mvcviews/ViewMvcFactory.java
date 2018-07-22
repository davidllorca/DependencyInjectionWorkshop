package me.davidllorca.diworkshop.ui.common.mvcviews;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import me.davidllorca.diworkshop.ui.detail.DetailViewMvc;
import me.davidllorca.diworkshop.ui.detail.DetailViewMvcImpl;
import me.davidllorca.diworkshop.ui.main.ListViewMvc;
import me.davidllorca.diworkshop.ui.main.ListViewMvcImpl;

public class ViewMvcFactory {

    private final LayoutInflater mLayoutInflater;

    public ViewMvcFactory(LayoutInflater layoutInflater) {
        this.mLayoutInflater = layoutInflater;
    }

    /**
     * Instantiate a new implementation of MVC view. The returned instance will be casted to MVC view
     * type inferred by java's automatic type inference.
     * @param mvcViewClass the class of the required MVC view
     * @param container this container will be used as MVC view's parent. See {@link LayoutInflater#inflate(int, ViewGroup)}
     * @param <T> the type of the required MVC view
     * @return new instance of MVC view
     */
    public <T extends ViewMvc> T newInstance(Class<T> mvcViewClass, @Nullable ViewGroup container){
        ViewMvc viewMvc;

        if(mvcViewClass == ListViewMvc.class){
            viewMvc = new ListViewMvcImpl(mLayoutInflater, container);
        } else if(mvcViewClass == DetailViewMvc.class) {
            viewMvc = new DetailViewMvcImpl(mLayoutInflater, container);
        } else {
            throw new IllegalArgumentException("Unsupported MVC view class " + mvcViewClass.getName());
        }
        return (T) viewMvc;
    }
}
