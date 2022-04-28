package com.eslam.mystore.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eslam.mystore.R;
import com.eslam.mystore.adapters.HomeCategoryAdabter;
import com.eslam.mystore.adapters.PopularAdabters;
import com.eslam.mystore.adapters.RecommendedAdabter;
import com.eslam.mystore.databinding.FragmentHomeBinding;
import com.eslam.mystore.models.HomeCategory;
import com.eslam.mystore.models.PopularModel;
import com.eslam.mystore.models.RecommendedModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    ScrollView scrollView;
    ProgressBar progressBar;
    RecyclerView pop_recycle, home_category_recycle, recommended_recy;
    FirebaseFirestore db;
    //popular items
    List<PopularModel> popularModelList;
    PopularAdabters popularAdabters;
    //Home_Category_items
    List<HomeCategory> categoryList;
    HomeCategoryAdabter homeCategoryAdabter;
    //Recommended_items
    List<RecommendedModel> recommendedModelList;
    RecommendedAdabter recommendedAdabter;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View root = binding.getRoot();


        pop_recycle = root.findViewById(R.id.pop_rec);
        home_category_recycle = root.findViewById(R.id.exp_rec);
        recommended_recy = root.findViewById(R.id.recomm_rec);
        scrollView = root.findViewById(R.id.scroll_view_home);
        progressBar = root.findViewById(R.id.progress_bar_home);
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        db = FirebaseFirestore.getInstance();
//popular
        pop_recycle.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        popularModelList = new ArrayList<>();
        popularAdabters = new PopularAdabters(getActivity(), popularModelList);
        pop_recycle.setAdapter(popularAdabters);
        db.collection("Popularproducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                PopularModel popularModel = document.toObject(PopularModel.class);
                                popularModelList.add(popularModel);
                                popularAdabters.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);

                            }
                        } else {
                            Toast.makeText(getActivity(), "Erorr" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//home ctegory


        home_category_recycle.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        categoryList = new ArrayList<>();
        homeCategoryAdabter = new HomeCategoryAdabter(getActivity(), categoryList);
        home_category_recycle.setAdapter(homeCategoryAdabter);
        db.collection("HomeCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                HomeCategory homeCategory = document.toObject(HomeCategory.class);
                                categoryList.add(homeCategory);
                                homeCategoryAdabter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Erorr" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//Recommended


        recommended_recy.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        recommendedModelList = new ArrayList<>();
        recommendedAdabter = new RecommendedAdabter(getActivity(), recommendedModelList);
        recommended_recy.setAdapter(recommendedAdabter);
        db.collection("Recommended")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                RecommendedModel recommendedModel = document.toObject(RecommendedModel.class);
                                recommendedModelList.add(recommendedModel);
                                recommendedAdabter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Erorr" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}