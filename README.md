# MvpAndroid
This repository contains a library for implementing MVP on Android.
The library also makes it easier to use Fragments by automatically
handling the backstack, toolbar, and interaction between the Fragment
and Activity.  The repo contains two main packages: the library,
which is in the “mvpandroid” package, and a demo of the library,
which is in the “app” package.

## Creating MVP Fragments
The Models, Views, and Presenters are all loosely bound which allows
them to be reused and interchanged easily.  As seen in the example
application, the MVP Fragment is created as follows:

```java
// FirstFragment.java
MvpExampleFragment mvpExampleFragment = new MvpExampleFragment();
getTransactionHandler().addFragment(mvpExampleFragment, MainActivity.MAIN_CONTAINER, true);

// MvpExampleFragment.java
@Override public BasePresenter getMvpPresenter() {
    return new MvpExamplePresenter();
}

@Override public BaseViewModel getMvpViewModel() {
    return new MvpExampleViewModel();
}
```

### FirstFragment.java
We create and add the MVP Fragment to the FragmentManager using the
TransactionHandler Interface.  The TransactionHandler Interface allows
Fragments to interact with their parent Activity for transaction based
operations such as adding and removing Fragments.  Since we are nesting
Fragments in this case (MvpExampleFragment is being added to
FirstFragment), we must use the TransactionHandler.  If we were adding a
Fragment directly to an Activity, we would call addFragment(..) without
using the TransactionHandler.

### MvpExampleFragment.java
Binding the Presenter and ViewModel in this way allows for a lot of
flexibility.  If in another screen of the application you wanted to
display the same MVP Fragment with different data, you could simply
create and bind a different ViewModel.

## MVP Fragments and Activity Destruction
When the user navigates away from an MVP Fragment, the Presenter and
ViewModel are unbound from each other to be garbage collected.  If
the MVP Fragment is destroyed for a reason other than user
navigation, such as an orientation change or if the OS is low on
memory, the MVP Fragment’s Presenter and ViewModel will be persisted
to a PresenterMaintainer.  When the MVP Fragment is recreated by its
parent Activity, the persisted Presenter will automatically be re-bound
to the View.  A major benefit of this is that long running operations
such as network calls will continue to execute at the ViewModel level
regardless of orientation changes.

## Testing
Easier unit tests are a major benefit of MVP.  The example application
contains a test class for MvpExamplePresenter.

## Gradle
```groovy
compile 'com.shanejansen.mvpandroid:mvpandroid:1.1.3'
```
