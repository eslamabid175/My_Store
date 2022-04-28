package com.eslam.mystore.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eslam.mystore.R;
import com.eslam.mystore.adapters.NavCategoryadapter;
import com.eslam.mystore.databinding.FragmentCategoryBinding;
import com.eslam.mystore.models.NavCategoryModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment {
    FirebaseFirestore db;
    RecyclerView recyclerView;
    List<NavCategoryModel> categoryModelList;
    NavCategoryadapter navCategoryadapter;
    private FragmentCategoryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCategoryBinding.inflate(inflater, container, false);

        View root = binding.getRoot();
        db = FirebaseFirestore.getInstance();
        recyclerView = root.findViewById(R.id.cat_recy);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        categoryModelList = new ArrayList<>();
        navCategoryadapter = new NavCategoryadapter(getActivity(), categoryModelList);
        recyclerView.setAdapter(navCategoryadapter);
        db.collection("NavCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                NavCategoryModel navCategoryModel = document.toObject(NavCategoryModel.class);
                                categoryModelList.add(navCategoryModel);
                                navCategoryadapter.notifyDataSetChanged();


                            }
                        } else {
                            Toast.makeText(getActivity(), "Erorr" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return root;

    }
}