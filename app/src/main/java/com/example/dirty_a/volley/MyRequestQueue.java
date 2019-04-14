package com.example.dirty_a.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MyRequestQueue {

    private static MyRequestQueue mInstance;
    private RequestQueue requestQueue;
    private static Context mContext;

    private MyRequestQueue(Context context){
        mContext = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue(){
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized MyRequestQueue getInstance(Context context){
        if (mInstance == null){
            mInstance = new MyRequestQueue(context);
        }
        return mInstance;
    }

    public void addToRequestQueue(Request request){
        requestQueue.add(request);
    }

}
