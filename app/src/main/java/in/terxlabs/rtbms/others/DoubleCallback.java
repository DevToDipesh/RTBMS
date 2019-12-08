package in.terxlabs.rtbms.others;

public abstract class DoubleCallback<T,S> {

    public abstract void onSuccess(T t, S s);

    public void onError(String error){ }
}
