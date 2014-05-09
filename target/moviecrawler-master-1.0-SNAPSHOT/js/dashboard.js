/**
 * Created by sansi on 2014/5/8.
 */

function display(content){
    var textarea=$('#stdout');
    textarea.text=content;
}

function append(content){
    var textarea=$('#stdout');
    var old=textarea.text;
    textarea.text=old+content;
}

function send(){
    var url="log/write?method=write";
    http.post(url,{slaveId:2,content:'test stdout content'},function(data){
        display(data);
    })
}

