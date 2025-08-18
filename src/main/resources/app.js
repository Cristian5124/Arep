function loadGetMsg() {
  const name = document.getElementById("name").value.trim();
  fetch("/hello?name=" + encodeURIComponent(name))
    .then(r => r.text())
    .then(t => document.getElementById("getresp").innerText = t);
}

function loadPostMsg() {
  const name = document.getElementById("postname").value.trim();
  fetch("/hellopost?name=" + encodeURIComponent(name), { method: "POST" })
    .then(r => r.text())
    .then(t => document.getElementById("postresp").innerText = t);
}

function loadTime() {
  fetch("/api/time")
    .then(r => r.json())
    .then(data => {
      document.getElementById("timeresp").innerText = "Hora actual: " + data.time;
    });
}
