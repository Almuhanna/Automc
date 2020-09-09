package com.creative.automc.APIHandler;


import androidx.annotation.NonNull;

import com.creative.automc.APIHandler.Response.LoginResponse;
import com.creative.automc.APIHandler.Response.LoginWithCarResponse;


public interface DatabaseInterfacee {


    void onSuccess(@NonNull Boolean value);

    void onFailed(@NonNull LoginWithCarResponse.MessagesBag value);

    void onError(@NonNull Throwable throwable);
}
