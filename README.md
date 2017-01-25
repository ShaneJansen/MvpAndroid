# MvpAndroid
This repository contains a library for implementing MVP on Android.
The library makes it easier to use Fragments by automatically
handling the backstack, toolbar, and interaction between the Fragment
and Activity.  The repo contains two main packages: the library,
which is in the “mvpandroid” package, and a demo of the library,
which is in the “app” package.

## Creating MVP Fragments
The Models, Views, and Presenters are all loosely bound which allows
them to be reused and interchanged easily.  In the example application
we use the following code to add a new MVP Fragment to a parent View:

```
MvpExampleFragment mvpExampleFragment = new MvpExampleFragment();
mvpExampleFragment.bindPresenter(new MvpExamplePresenter());
mvpExampleFragment.bindViewModel(new MvpExampleViewModel());
getTransactionHandler().addFragment(mvpExampleFragment, MainActivity.MAIN_CONTAINER, true);
```

1 - We instantiate the new MVP Fragment.

2-3 - We bind a Presenter and ViewModel to the MVP Fragment.  Binding
the Presenter and ViewModel in this way allows for a lot of flexibility.
If in another screen of the application you wanted to display the same
MVP Fragment with different data, you could simply create and bind a
different ViewModel.  Or if your application has different Views with
the same functionality, you could bind the same Presenter.

4 - We add the MVP Fragment to the FragmentManager using the
TransactionHander Interface.  The TransactionHandler Interface allows
Fragments to interact with their parent Activity for transaction based
operations such as adding and removing Fragments.

## MVP Fragments and Activity Destruction
When the user navigates away from an MVP Fragment, the Presenter and
ViewModel are unbound from each other to be garbage collected.  If
the MVP Fragment is destroyed for a reason other than user
navigation, such as an orientation change or if the OS is low on
memory, the MVP Fragment’s Presenter and ViewModel will be persisted
to a PresenterMaintainer.  When the MVP Fragment is recreated by its
parent Activity, The persisted Presenter will automatically be re-bound
to the View.

## Gradle
```groovy
compile 'com.shanejansen.mvpandroid:mvpandroid:1.0.8'
```
