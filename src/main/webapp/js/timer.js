var endDate;
function start(time,date){
    let beginDate = new Date(date);
    let beginTime = beginDate.getTime();
    endDate = new Date( beginTime + time * 60 * 1000);
    timer();
}

setInterval(timer,1000);

function timer(){
    let now = new Date();
    let endTime = endDate.getTime();
    let nowTime = now.getTime();
    let timeleft = endTime - nowTime;

    let seconds = Math.floor(timeleft / 1000);
    let minutes = Math.floor(seconds / 60);
    let hours = Math.floor(minutes / 60);

    seconds = seconds % 60;
    minutes = minutes % 60;
    hours = hours % 24;

    hours = hours < 10 ? '0' + hours : hours;
    minutes = minutes < 10 ? '0' + minutes : minutes;
    seconds = seconds < 10 ? '0' + seconds : seconds;

    document.getElementById("timer").innerHTML = hours+':'+minutes+':'+seconds ;//"Time left: "+hours+":"+minutes+":"+seconds;
    if (timeleft === 0)
        document.getElementById("form").click();
}


