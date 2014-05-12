function now(){
    return new Date();
}

//yyyy MM dd HH mm ss
function date2String(date, format){
    var str=format.replace("yyyy",date.getFullYear())
        .replace("MM",date.getMonth())
        .replace("dd",date.getDate())
        .replace("HH",date.getHours())
        .replace("mm",date.getMinutes())
        .replace("ss",date.getSeconds());
    return str;
}

function date2String(date){
    date2String(date,"yyyy/MM/dd HH:mm:ss");
}

