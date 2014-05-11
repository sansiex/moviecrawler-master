/**
 * Created by sansi on 2014/5/8.
 */

function display(content){
    var textarea=$('#stdout')[0];
    var txt=content;
    if(typeof content=="object"){
        txt=JSON.stringify(content);
    }
    textarea.innerText=txt;
}

function append(content){
    var textarea=$('#stdout')[0];
    var txt=content;
    if(typeof content=="object"){
        txt=JSON.stringify(content);
    }
    var old=textarea.innerText;
    textarea.innerText=old+txt;
}

function send(){
    var url="log/write?method=write";
    http.post(url,{slaveId:2,content:'test stdout content'},function(data){
        display(data);
    })
}

function loadRecent(){
    var date = new Date();
    var url="log/load";
    http.post(url,{start:date2String(date), end:date2String(date)},function(data){
        display(data);
    })
}

function loadLog(){
    var url="log/load";
    var start=$('#start')[0].value;
    var end=$('#end')[0].value;
    http.post(url,{start:start, end:end},function(data){
        display(data);
    })
}

function startTask(){
    var url="controller/startTask";
    http.post(url,{},function(data){
        var params=data.params;
        var code=params.code;
        if(code==0){
            display("Task starts.");
        }else{
            display("There ia still a task running.");
        }
    })
}