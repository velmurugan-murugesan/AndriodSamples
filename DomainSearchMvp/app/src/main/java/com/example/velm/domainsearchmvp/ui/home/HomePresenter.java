package com.example.velm.domainsearchmvp.ui.home;

import com.example.velm.domainsearchmvp.model.Words;

import java.util.List;

/**
 * Created by velmmuru on 10/11/2017.
 */

public interface HomePresenter {

    void loadDataIntoDb();

    List<Words> showDataFromDb();
}
