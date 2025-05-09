document.addEventListener('DOMContentLoaded', () => {
    const urlParams = new URLSearchParams(window.location.search);
    const movieId = urlParams.get('id');
    const detailPage = document.querySelector('.detail_page');

    if (!movieId) {
        detailPage.innerHTML = '<p>영화 ID가 없습니다.</p>';
        return;
    }

    fetch(`/tmdb/detail/${movieId}`)
        .then(res => res.json())
        .then(movie => {
            const posterUrl = movie.poster_path
                ? `https://image.tmdb.org/t/p/w300${movie.poster_path}`
                : 'https://via.placeholder.com/300x400?text=No+Image';

            detailPage.innerHTML = `
                <div class="movie_poster">
                    <img src="${posterUrl}" style="width:100%; height:auto; border-radius:10px;">
                </div>
                <div style="display:inline-block; margin-left:40px; vertical-align:top; max-width:1000px;">
                    <h2>${movie.title}</h2>
                    <p><strong>개봉일:</strong> ${movie.release_date}</p>
                    <p><strong>줄거리:</strong><br>${movie.overview}</p>
                    <p><strong>장르:</strong> ${movie.genres.map(g => g.name).join(', ')}</p>
                </div>
            `;
        })
        .catch(() => {
            detailPage.innerHTML = '<p>영화 정보를 불러오는 데 실패했습니다.</p>';
        });
});
