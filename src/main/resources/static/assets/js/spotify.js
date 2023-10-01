function fetchData() {
    fetch("https://api.lanyard.rest/v1/users/780889323162566697/")
        .then((response) => response.json())
        .then((data) => {
            const spotifyData = data.data.spotify;
            if (spotifyData) {
                const song = spotifyData.song;
                const artist = spotifyData.artist;
                const album = spotifyData.album;
                const albumArtUrl = spotifyData.album_art_url;

                const songColor = "#81A1C1";
                const artistColor = "#A3BE8C";
                const albumColor = "#B48EAD";

                const albumArt = document.querySelector(".album-art");
                albumArt.src = albumArtUrl;

                document.querySelector(".song-title").innerHTML = `<span style="color: ${songColor};">${song}</span>`;
                document.querySelector(".artist-name").innerHTML = `By <span style="color: ${artistColor};">${artist}</span>`;
                document.querySelector(".album-name").innerHTML = `On <span style="color: ${albumColor};">${album}</span>`;
            } else {
                document.querySelector(".other-info").innerHTML = "Not listening to Spotify.";
            }
        })
        .catch((error) => {
            console.error("[ERROR] Could not fetch/find Spotify.");
            document.querySelector(".other-info").innerHTML = "Error while fetching.";
        });
}

fetchData();

setInterval(fetchData, 5000);
