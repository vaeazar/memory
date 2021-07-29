
document.addEventListener("DOMContentLoaded", function () {
  window.addEventListener('beforeunload', function(e) {
    (new Image()).src = "/outTest";
  });
});
