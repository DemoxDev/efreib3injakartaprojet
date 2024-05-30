document.addEventListener('DOMContentLoaded', function() {
    fetch('/getAllAnime')
        .then(response => response.json())
        .then(animes => {
            var centerList = document.querySelector('.centerlist');
            animes.forEach(anime => {
                var div = document.createElement('div');
                div.className = 'animebg';

                var img1 = document.createElement('img');
                img1.className = 'cardimg';
                img1.src = 'data:image/png;base64,' + anime.image;
                div.appendChild(img1);

                var img2 = document.createElement('img');
                img2.className = 'card';
                img2.src = '/img/card.PNG';
                div.appendChild(img2);

                var h1 = document.createElement('h1');
                h1.className = 'text';
                h1.textContent = anime.name;
                div.appendChild(h1);

                centerList.appendChild(div);
            });
        });
});

function toggleMenu() {
    var burgerhider = document.getElementById('burgerhider');
    var burger = document.getElementById('burger');
    var burgerbutton1 = document.getElementById('burgerbutton1');
    var burgerbutton2 = document.getElementById('burgerbutton2');
    var burgerbutton3 = document.getElementById('burgerbutton3');

    if (burgerhider.classList.contains('height-zero')) {
        burgerhider.classList.remove('height-zero');
        burgerhider.classList.add('height-full2');

        burger.classList.remove('height-zero');
        burger.classList.add('height-full');

        burgerbutton1.classList.remove('hidden');
        burgerbutton1.classList.add('visible');
        burgerbutton2.classList.remove('hidden');
        burgerbutton2.classList.add('visible');
        burgerbutton3.classList.remove('hidden');
        burgerbutton3.classList.add('visible');
    } else {
        burgerhider.classList.remove('height-full2');
        burgerhider.classList.add('height-zero');

        burger.classList.remove('height-full');
        burger.classList.add('height-zero');

        burgerbutton1.classList.remove('visible');
        burgerbutton1.classList.add('hidden');
        burgerbutton2.classList.remove('visible');
        burgerbutton2.classList.add('hidden');
        burgerbutton3.classList.remove('visible');
        burgerbutton3.classList.add('hidden');
    }
}

document.getElementById('toggleButton').addEventListener('click', toggleMenu);
document.getElementById('burgerbutton3').addEventListener('click', toggleMenu);

document.addEventListener('DOMContentLoaded', function() {
    var crossBtn = document.querySelector('.crossbtn');
    var hidderSection = document.getElementById('hidder');
    var showHidderButton = document.getElementById('showHidderButton');
    var animebgDivs = document.querySelectorAll('.animebg');

    crossBtn.addEventListener('click', function() {
        hidderSection.style.display = 'none';
    });

    showHidderButton.addEventListener('click', function() {
        hidderSection.style.display = 'flex';
    });

    animebgDivs.forEach(function(div) {
        div.addEventListener('click', function() {
            hidderSection.style.display = 'flex';
        });
    });
});