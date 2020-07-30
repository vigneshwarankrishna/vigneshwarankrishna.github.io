function dispSentMsg(){
    var para1 = document.createElement("P")
    var time1 = new Date()
    

    var msgTime = time1.getHours() + ":"+ time1.getMinutes()  
    var rmsg=document.getElementById("smsg").value
    var text1 = document.createTextNode(rmsg)
    

    para1.appendChild(text1)
   
    document.getElementById("rhim").appendChild(para1)
    document.getElementById("rhimtime").append(msgTime)
    
    document.getElementById("rhim").style.visibility="visible"

    document.getElementById("sme").appendChild(para1.cloneNode(true))
    document.getElementById("smetime").append(msgTime)
    document.getElementById("sme").style.visibility="visible"
    
    }


function dispRMsg(){
    var para1 = document.createElement("P")
    var time1 = new Date()
    

    var msgTime = time1.getHours() + ":"+ time1.getMinutes()  
    var smsg=document.getElementById("rmsg").value
    var text1 = document.createTextNode(smsg)
    

    para1.appendChild(text1)
   
    document.getElementById("shim").appendChild(para1)
    document.getElementById("shim").append(msgTime)
    
    document.getElementById("shim").style.visibility="visible"

    document.getElementById("rme").appendChild(para1.cloneNode(true))
    document.getElementById("rme").append(msgTime)
    document.getElementById("rme").style.visibility="visible"
    
}
