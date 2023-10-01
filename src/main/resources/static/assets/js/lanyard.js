fetch("https://api.lanyard.rest/v1/users/780889323162566697/")
    .then((e) => e.json())
    .then((e) => {
        const s = e.data,
            a = s.discord_user.username,
            n = s.discord_status;
        let t = n;
        switch (n) {
            case "dnd":
                t = '<span class="pastel-red">in DND mode</span>';
                break;
            case "idle":
                t = '<span class="pastel-yellow">idle</span>';
                break;
            case "online":
                t = '<span class="pastel-green">online</span>';
                break;
            case "offline":
                t = '<span class="pastel-gray">offline</span>';
        }
        (document.getElementById("user-status").innerHTML = `I am currently ${t}.`), (document.getElementById("user-fullname").innerHTML = `You can contact me on Discord: <span class="pastel-purple">@${a}</span>`);
    })
    .catch((e) => {
        console.error("[ERROR] Could not fetch Discord profile.");
    });
