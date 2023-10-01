fetch("https://api.lanyard.rest/v1/users/1147999469854523414/")
    .then((t) => t.json())
    .then((t) => {
        const e = t.data.activities;
        if (e && e.length > 0) {
            let t = !1;
            for (const o of e)
                if ("Visual Studio Code" === o.name) {
                    const e = o.details,
                        n = o.state;
                    (document.getElementById("vscode-details").textContent = `${e}`), (document.getElementById("vscode-state").textContent = `${n}`), (t = !0);
                    break;
                }
            t || ((document.getElementById("vscode-details").textContent = ""), (document.getElementById("vscode-state").textContent = "I'm not coding right now."));
        } else console.log("[NOTHING] No VS Code detected."), (document.getElementById("vscode-details").textContent = "I'm not coding right now."), (document.getElementById("vscode-state").textContent = "TIP: ` + LWYRUP + Console");
    })
    .catch((t) => {
        console.error("[ERROR] Could not fetch VS Code status.", t);
    });
