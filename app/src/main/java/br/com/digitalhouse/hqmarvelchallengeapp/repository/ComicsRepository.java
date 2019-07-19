package br.com.digitalhouse.hqmarvelchallengeapp.repository;

import br.com.digitalhouse.hqmarvelchallengeapp.model.ComicsResponse;
import io.reactivex.Single;

import static br.com.digitalhouse.hqmarvelchallengeapp.data.network.RetrofitService.getApiService;
import static br.com.digitalhouse.hqmarvelchallengeapp.util.AppUtils.md5;

public class ComicsRepository {

    public static final String PUBLIC_KEY = "6eb7e8896ec5850c52515a8a23ee97f0";
    public static final String PRIVATE_KEY = "0dd0c16fedb8a02985977eafca66b49f5e6a526f";

    public Single<ComicsResponse> getComics() {
        String ts = Long.toString(System.currentTimeMillis() / 1000);
        String hash = md5(ts + PRIVATE_KEY + PUBLIC_KEY);
        return getApiService().getComics("magazine", "comic", true, "focDate", "50", ts, hash, PUBLIC_KEY);
    }
}

// https://gateway.marvel.com/v1/public/comics?format=magazine&formatType=comic&noVariants=true&orderBy=focDate&ts=1551213595&hash=9584823101fd40c435d4c028df6daca3&apikey=6eb7e8896ec5850c52515a8a23ee97f0
