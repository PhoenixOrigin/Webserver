function updateTime() {
    var e = new Date(new Date().getTime() + 36e5).toUTCString().replace(/GMT$/, "").split(" ")[4].split(":"),
        t = e[0] + ":" + e[1] + ":" + e[2];
    document.getElementById("timeDisplay").innerHTML = "My local time is <i>" + t + "</i>";
}

setInterval(updateTime, 1e3);
