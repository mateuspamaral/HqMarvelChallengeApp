package br.com.digitalhouse.hqmarvelchallengeapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.digitalhouse.hqmarvelchallengeapp.model.Result;
import br.com.digitalhouse.hqmarvelchallengeapp.repository.ComicsRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ComicsViewModel extends AndroidViewModel {

    // Variáveis que serão usadas para buscar os quadrinhos na API
    private MutableLiveData<List<Result>> resultLiveData = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private ComicsRepository repository = new ComicsRepository();

    public ComicsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Result>> getResults() {
        return resultLiveData;
    }

    public void getComics() {
        // Adicionamos a chamada a um disposible para podermos eliminar o disposable da destruição do viewmodel
        disposable.add(repository.getComics()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(subscription -> {})
                .doAfterTerminate(() -> {})
                .subscribe(response -> {
                    // Chegou aqui então alteramos o live data, assim a View que está observando ele pode atualizar a tela
                    resultLiveData.setValue(response.getData().getResults());
                }, throwable -> {
                    Log.i("LOG", "Error: " + throwable.getMessage());
                }));
    }

}