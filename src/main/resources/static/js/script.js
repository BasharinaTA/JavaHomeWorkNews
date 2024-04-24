"use strict"

document.addEventListener("scroll", () => {
    const btn = document.getElementById("up");
    if (document.documentElement.scrollTop > 150) {
        btn.classList.remove("hidden");
    } else {
        btn.classList.add("hidden");
    }
})
