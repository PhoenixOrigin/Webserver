const cursor = document.querySelector(".custom-cursor"),
    easingFactor = 0.35;
document.addEventListener("mousemove", (o) => {
    const e = o.pageX,
        r = o.pageY,
        s = cursor.offsetLeft + 0.35 * (e - cursor.offsetLeft),
        t = cursor.offsetTop + 0.35 * (r - cursor.offsetTop);
    (cursor.style.left = s + "px"), (cursor.style.top = t + "px");
});