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
    var url="log/load";
    http.post(url,{start:"2014/5/11 15:23:00", end:"2014/5/11 15:28:00"},function(data){
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