function fetchAndDisplayGitHubRepos(username) {
    fetch("https://api.github.com/users/PhoenixOrigin/repos")
        .then((response) => {
            if (!response.ok) {
                throw new Error(`GitHub API Error: ${response.status} (Don't worry, It's probably rate limited!)`);
            }
            return response.json();
        })
        .then((repos) => {
            document.getElementById("github-repos").innerHTML = generateRepoArticles(repos);
        })
        .catch((error) => {
            console.error("Error fetching GitHub repos:", error);
            document.getElementById("github-repos").innerHTML = generateErrorArticle(error.message);
        });
}

function generateErrorArticle(errorMessage) {
    return `<article class="article-box">
                <div class="article-textbox">
                    <div>
                        <h3 class="betterh3">Error</h3>
                        <p class="article-text">${errorMessage}</p>
                    </div>
                </div>
            </article>`;
}

function generateRepoArticles(repos) {
    let repoHTML = "";
    repos.forEach((repo) => {
        repoHTML += `
            <article class="article-box">
                <div class="article-textbox">
                    <div>
                        <h3 class="betterh3">${repo.name}</h3>
                        <p class="article-text">${repo.description || "No description available."}</p>
                    </div>
                    <div class="article-info">
                        <a href="${repo.html_url}" class="link" target="_blank">View on GitHub</a>
                    </div>
                </div>
            </article>`;
    });
    return repoHTML;
}

const githubUsername = "PhoenixOrigin";
fetchAndDisplayGitHubRepos(githubUsername);
