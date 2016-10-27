
package be.florien.poketeam.async;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public abstract class AbstractAsyncTaskLoader<D> extends AsyncTaskLoader<D> {

    private D mResult;

    public AbstractAsyncTaskLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        if (mResult != null) {
            deliverResult(mResult);
        }

        if (takeContentChanged() || mResult == null) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();

        onStopLoading();
        mResult = null;
    }

    @Override
    public void deliverResult(D data) {
        mResult = data;
        if (isStarted()) {
            super.deliverResult(data);
        }
    }

}
