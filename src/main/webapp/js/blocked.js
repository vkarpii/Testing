function blocked(){
    var info = document.getElementById('menu-info');
    var about = document.getElementById('menu-about');
    var home = document.getElementById('menu-home');
    var contacts = document.getElementById('menu-contacts');

    info.style.pointerEvents = 'none';
    about.style.pointerEvents = 'none';
    home.style.pointerEvents = 'none';
    contacts.style.pointerEvents = 'none';
}