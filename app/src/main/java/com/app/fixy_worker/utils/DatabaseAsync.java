//package com.app.fixy_worker.utils;
//
//import android.os.AsyncTask;
//
///**
// * Created by Shubham verma on 25-04-2018.
// */
//
//public class DatabaseAsync extends AsyncTask<Void, Void, Void> {
////
////    int i;
////    private SampleDatabase sampleDatabase;
////    private List<CommitteeModel.DataBean> modelRoom;
////
////    public DatabaseAsync(SampleDatabase sampleDatabase, List<CommitteeModel.DataBean> modelRoom, int i) {
////        this.sampleDatabase = sampleDatabase;
////        this.modelRoom = modelRoom;
////        this.i = i;
////    }
////
////    @Override
////    protected void onPreExecute() {
////        super.onPreExecute();
////        //Perform pre-adding operation here.
////    }
////
////    @Override
////    protected Void doInBackground(Void... voids) {
////        //Let's add some dummy data to the database.
//////        University university = new University();
//////        university.setName("MyUniversity");
//////
//////        College college = new College();
//////        college.setId(1);
//////        college.setName("MyCollege");
//////
//////        university.setCollege(college);
////
////        //Now acess all the methods defined in DaoAccess with sampleDatabase object
////        if (i == 1) {
////            sampleDatabase.daoAccess().insertMultipleListRecord(modelRoom);
////            Log.d("response", "done");
////        } else {
////            sampleDatabase.daoAccess().fetchAllData();
////            Log.d("response", String.valueOf(sampleDatabase.daoAccess().fetchAllData()));
////        }
////        return null;
////    }
////
////    @Override
////    protected void onPostExecute(Void aVoid) {
////        super.onPostExecute(aVoid);
////
////        //To after addition operation here.
////    }
//}
