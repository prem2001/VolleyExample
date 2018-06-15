package com.example.r.volleyrequest;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.CircularProgressDrawable;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class AncynNetwork extends AsyncTask<String,Integer,String> {
    NetworkInterface networkInterface;
    Context context;
    ProgressDialog alertDialog;



    String TAG="MainActivity";

    public AncynNetwork(Context context, NetworkInterface networkInterface) {
        this.networkInterface= networkInterface;
        this.context=context;
        alertDialog=new ProgressDialog(this.context);

    }

    @Override
    protected void onPostExecute(String s) {
        alertDialog.dismiss();
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPreExecute() {
        alertDialog.show();

    }

    @Override
    protected String doInBackground(String... strings) {
        RequestQueue mRequestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = null;
        try {
            jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://192.168.0.111:8080/can/2", Utilities.getPaymentLinkJson(), new Response.Listener<JSONObject>() {
                //            jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://192.168.0.29:8080/pay", Utilities.getLogingJson(), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d(TAG, "onResponse: 1111"+response);
                    networkInterface.onResponce(response.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, "onErrorResponse: 222222");
                    networkInterface.onError(error.getMessage());
                }
            });
        } catch (Exception e) {
        }
        mRequestQueue.add(jsonObjectRequest);
        return null;
    }
}
