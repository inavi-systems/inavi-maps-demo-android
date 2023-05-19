package com.inavi.maps.androiddemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Feature> features;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());
        recyclerView.setHasFixedSize(true);

        loadFeatures();


        ItemClickSupport.addTo(recyclerView).setOnItemClickListener((recyclerView, position, view) -> {
            Feature feature = features.get(position);
            if (!feature.getName().isEmpty())
                startFeature(feature);
        });
    }

    private void startFeature(Feature feature) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(getPackageName(), feature.getName()));
        startActivity(intent);
    }

    private void loadFeatures() {
        try {
            new LoadFeatureTask().execute(getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES | PackageManager.GET_META_DATA));
        } catch (PackageManager.NameNotFoundException exception) {
            exception.printStackTrace();
        }
    }
    private void onFeaturesLoaded(List<Feature> featuresList) {
        List<Feature> features = featuresList;
        if (featuresList == null || featuresList.isEmpty()) {
            return;
        }

        String currentCat = "";
        for (int i = 0; i < features.size(); i++) {
            String category = features.get(i).getCategory();
            if (!currentCat.equals(category)) {
                features.add(i, new Feature("", category, "", ""));
                currentCat = category;
            }
        }

        recyclerView.setAdapter(new FeatureAdapter(features));
    }

    private class LoadFeatureTask extends AsyncTask<PackageInfo, Void, List<Feature>> {

        @Override
        protected List<Feature> doInBackground(PackageInfo... params) {
             features = new ArrayList<>();
            PackageInfo app = params[0];

            String packageName = getApplicationContext().getPackageName();
            String metaDataKey = getString(R.string.inv_category);
            for (ActivityInfo info : app.activities) {
                if (info.labelRes != 0 && info.name.startsWith(packageName) && !info.name.equals(MainActivity.class.getName())) {
                    String label = getString(info.labelRes);
                    String description = resolveString(info.descriptionRes);
                    String category = resolveMetaData(info.metaData, metaDataKey);
                    features.add(new Feature(info.name, label, description, category));
                }
            }

            if (!features.isEmpty()) {
                Comparator<Feature> comparator = (lhs, rhs) -> {
                    int result = lhs.getCategory().compareToIgnoreCase(rhs.getCategory());
                    if (result == 0) {
                        result = lhs.getLabel().compareToIgnoreCase(rhs.getLabel());
                    }
                    return result;
                };
            }

            return features;
        }

        private String resolveMetaData(Bundle bundle, String key) {
            String category = null;
            if (bundle != null) {
                category = bundle.getString(key);
            }
            return category;
        }

        private String resolveString(@StringRes int stringRes) {
            try {
                return getString(stringRes);
            } catch (Resources.NotFoundException exception) {
                return "-";
            }
        }

        @Override
        protected void onPostExecute(List<Feature> features) {
            super.onPostExecute(features);
            onFeaturesLoaded(features);
        }
    }
}
