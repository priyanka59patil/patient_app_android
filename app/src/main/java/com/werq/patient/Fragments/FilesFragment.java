package com.werq.patient.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Adapters.FilesAdapter;
import com.werq.patient.Models.Files;
import com.werq.patient.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilesFragment extends Fragment {

    @BindView(R.id.tvFilterDoctors)
    TextView tvFilterDoctors;
    @BindView(R.id.tvFilterFiles)
    TextView tvFilterFiles;
    @BindView(R.id.rvFiles)
    RecyclerView rvFiles;

Context mContext;
    ArrayList<Files> allFiles;
    private FilesAdapter filesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_files, container, false);
        ButterKnife.bind(this, view);
       initializeVariables();
        allFiles = getFilesData();
        filesAdapter=new FilesAdapter(mContext,allFiles);
        filesAdapter = new FilesAdapter(mContext, allFiles);
        rvFiles.setLayoutManager(new LinearLayoutManager(mContext));
        rvFiles.setAdapter(filesAdapter);

        return view;
    }

    private void initializeVariables() {
        mContext=getActivity();
    }

    private ArrayList<Files> getFilesData() {
        ArrayList<Files> files = new ArrayList<>();
        Files file = new Files(R.drawable.imageone, "image", "Image-Attachment-01.jpg", "receiver", "jeffery Crippin", "Yesterday 02:12:32 PM");
        Files file1 = new Files(R.drawable.imagetwo, "image", "Image-Attachment-02.jpg", "receiver", "jeffery Crippin", "Yesterday 02:12:32 PM");
        Files file2 = new Files(R.drawable.imagetwo, "pdf", "Intro-part-02.pdf", "sender", "David Crippin", "Yesterday 02:12:32 PM");
        Files file3 = new Files(R.drawable.imagetwo, "pdf", "Intro-part-02.pdf", "sender", "David Crippin", "Yesterday 02:12:32 PM");
        Files file4 = new Files(R.drawable.imagetwo, "image", "Image-Attachment-02.jpg", "sender", "David Crippin", "Yesterday 02:12:32 PM");
        Files file5 = new Files(R.drawable.imagetwo, "pdf", "Intro-part-02.pdf", "sender", "David Crippin", "Yesterday 02:12:32 PM");
        Files file6 = new Files(R.drawable.imagetwo, "image", "Image-Attachment-02.jpg", "sender", "David Crippin", "Yesterday 02:12:32 PM");
        Files file7 = new Files(R.drawable.imagetwo, "pdf", "Intro-part-02.pdf", "sender", "David Crippin", "Yesterday 02:12:32 PM");

        files.add(file);
        files.add(file1);
        files.add(file2);
        files.add(file3);
        files.add(file4);
        files.add(file5);
        files.add(file6);
        files.add(file7);


        return files;
    }

}
