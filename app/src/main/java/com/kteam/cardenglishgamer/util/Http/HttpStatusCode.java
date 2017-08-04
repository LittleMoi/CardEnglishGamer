package com.kteam.cardenglishgamer.util.Http;

/**
 * Created by Mo on 2017/7/15.
 */


public class HttpStatusCode {

    enum Codes{
        _100,_101,_200,_201,_202,_203,_204,_205,_206,_300,_301,_302,_303,_304,_305,_307,_400,_401,_403,_404,_405,_406,_407,
        _408,_409,_410,_411,_412,_413,_414,_415,_416,_417,_500,_501,_502,_503,_504,_505;
    };
    static String getDescription(int code,boolean StatusCodeDescriptionOff){
        Codes.valueOf("_"+code);
        return null;
    }
}
