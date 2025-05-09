document.addEventListener('DOMContentLoaded', () => {
    const searchBtn = document.querySelector('.search-button');
    const searchInput = document.querySelector('.input-container input');
    const movieList = document.querySelector('.movie_list');
    const rankingDiv = document.querySelector('.ontime_moive');

    // 영화 검색 기능
    searchBtn.addEventListener('click', () => {
        const query = searchInput.value.trim();
        if (!query) return;

        fetch(`/tmdb/search?query=${encodeURIComponent(query)}`)
            .then(res => res.json())
            .then(data => {
                movieList.innerHTML = '';

                if (!data.results || data.results.length === 0) {
                    movieList.innerHTML = '<p style="padding: 1rem;">검색 결과가 없습니다.</p>';
                    return;
                }

                data.results.forEach(movie => {
                    const card = document.createElement('div');
                    card.className = 'movie-card';
                    card.style = `
                        display: inline-block;
                        width: 180px;
                        margin: 10px;
                        vertical-align: top;
                        text-align: center;
                    `;

                    const posterUrl = movie.poster_path
                        ? `https://image.tmdb.org/t/p/w200${movie.poster_path}`
                        : 'https://via.placeholder.com/180x270?text=No+Image';

                    card.innerHTML = `
                        <img src="${posterUrl}" alt="${movie.title}" style="width:100%; border-radius:10px; cursor:pointer;" onclick="location.href='detail_page.html?id=${movie.id}'">
                        <h4>${movie.title}</h4>
                        <p style="font-size:0.9rem;">${movie.release_date || ''}</p>
                        <p style="font-size:0.8rem;">${movie.overview ? movie.overview.slice(0, 60) + '...' : ''}</p>
                    `;

                    movieList.appendChild(card);
                });
            })
            .catch(() => {
                movieList.innerHTML = '<p>검색 중 오류가 발생했습니다.</p>';
            });
    });

    // 실시간 인기 영화 순위
    fetch('/tmdb/popular')
        .then(res => res.json())
        .then(data => {
            rankingDiv.innerHTML = '<h4 style="text-align:center;">인기 영화</h4>';
            const list = document.createElement('ol');
            list.style = 'padding-left: 20px; font-size: 0.95rem;';

            data.results.slice(0, 10).forEach(movie => {
                const li = document.createElement('li');
                li.textContent = `${movie.title} (${movie.release_date?.split('-')[0] || 'N/A'})`;
                list.appendChild(li);
            });

            rankingDiv.appendChild(list);
        })
        .catch(() => {
            rankingDiv.innerHTML = '<p>순위를 불러올 수 없습니다.</p>';
        });
});

function goToSL() {
    window.location.href = "signup_login.html"; 
}
