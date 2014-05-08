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
    var url="log/stdout";
    http.post(url,{content:'test stdout content'},function(data){
        Console.log(data);
    })
}

