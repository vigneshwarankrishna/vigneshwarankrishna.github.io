$("#sbtn").click(function(){
var a=$("#smsg").val()
$("#smsgs").append("<ul><li>"+a+"<br><span>"+msgTime+"</span></li></ul>")
$("#smsgs>ul>li").last().css({
    "display":"inline-block",
    "clear": "both",
    "padding": "20px",
    "border-radius": "30px",
    "margin-bottom": "2px",
    "font-family": "Helvetica",
    "font-weight": "100",
    "line-height": "18px",
    "font-size": "15px",
    "color": "#000",
    "word-wrap": "break-word",
"background": "#e1ffc7",
    "float": "right"
})

$("#rmsgs").append("<ul><li>"+a+"<br><span>"+msgTime+"</span></li></ul>")
$("#rmsgs>ul>li").last().css({
    "display":"inline-block",
    "clear": "both",
    "padding": "20px",
    "border-radius": "30px",
    "margin-bottom": "2px",
    "font-family": "Helvetica",
    "font-weight": "100",
    "line-height": "18px",
    "font-size": "15px",
    "color": "#000",
    "word-wrap": "break-word",
"background": "white",
    "float": "left"

})

})

var time1 = new Date()
    

var msgTime = time1.getHours() + ": 0"+ time1.getMinutes() +" pm"
$("#rbtn").click(function(){

    var a=$("#rmsg").val()
    $("#rmsgs").append("<ul><li>"+a+"<br><span>"+msgTime+"</span></li></ul>")
    $("#rmsgs>ul>li").last().css({
        "display":"inline-block",
        "clear": "both",
        "padding": "20px",
        "border-radius": "30px",
        "margin-bottom": "2px",
        "font-family": "Helvetica",
        "font-weight": "100",
        "line-height": "18px",
        "font-size": "15px",
        "color": "#000",
        "word-wrap": "break-word",
    "background": "#e1ffc7",
        "float": "right"
    })
    
        $("#smsgs").append("<ul><li>"+a+"<br><span>"+msgTime+"</span></li></ul>")
    $("#smsgs>ul>li").last().css({
        "display":"inline-block",
    "clear": "both",
    "padding": "20px",
    "border-radius": "30px",
    "margin-bottom": "2px",
    "font-family": "Helvetica",
    "font-weight": "100",
    "line-height": "18px",
    "font-size": "15px",
    "color": "#000",
    "word-wrap": "break-word",
"background": "white",
    "float": "left"
    
    })
    
    })