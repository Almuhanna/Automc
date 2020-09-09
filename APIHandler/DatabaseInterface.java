package com.creative.automc.APIHandler;


import androidx.annotation.NonNull;

import com.creative.automc.APIHandler.Response.LoginResponse;

public interface DatabaseInterface {


    void onSuccess(@NonNull Boolean value);

    void onFailed(@NonNull LoginResponse.MessagesBag value);

    void onError(@NonNull Throwable throwable);
}
