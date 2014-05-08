package com.dianping.sansi.moviecrawler.master.resource;

import javax.ws.rs.*;
import java.util.HashMap;

/**
 * Created by sansi on 2014/5/8.
 */
@Path(value = "/test")
@Produces("application/*;charset=UTF-8")
@Consumes("application/*;charset=utf-8")
public class TestResource {
    @GET
    @Produces("application/json;charset=utf-8")
    @Path(value = "/test")
    public HashMap<String,Object> test(){
        HashMap<String,Object> ret=new HashMap<>();
        ret.put("code",0);
        ret.put("message","Success!");
        return ret;
    }
}
