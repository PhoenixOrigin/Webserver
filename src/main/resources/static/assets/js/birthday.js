var countDownDate = new Date("08/27/2024").getTime(),
    x = setInterval(function () {
        var a = new Date().getTime(),
            b = countDownDate - a,
            c = Math.floor(b / 864e5),
            d = Math.floor((b % 864e5) / 36e5),
            e = Math.floor((b % 36e5) / 6e4),
            f = Math.floor((b % 6e4) / 1e3);
        (document.getElementById("birthday").innerHTML = c + "d " + d + "h " + e + "m " + f + "s "), b < 0 && (clearInterval(x), (document.getElementById("birthday").innerHTML = "Its my birthday! \ud83c\udf89"));
    }, 1e3);
