/**
 * Created by sansi on 2014/5/8.
 */

var http={};

http.ajax=function(url,method,params,callback){
    $.ajax({
        type: method,
        dataType: "json",
        url : url,
        data: params,
        success: function(data){
            if(callback!=null){
                callback(data);
            }
        },
        error: function(jqXHR, textStatus, errorThrown){
            alert("error@ajax_post:"+errorThrown);
        }
    });
}

http.get=function(url,params,callback){
    if(param!=null){
        url=url+"?";
        var arr=new Array();
        for(var k in params){
            arr.push(url+k+"="+params[k]);
        }
        url=url+arr.join('&');
    }
    http.ajax(url,"get",null,callback);
}

http.post=function(url,params,callback){
    http.ajax(url,"post",params,callback);
}