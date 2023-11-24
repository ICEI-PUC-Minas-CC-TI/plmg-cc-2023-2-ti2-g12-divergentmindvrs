function getNeurodivergenciaParameter() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get("neurodivergencia");
}

function exibirConteudo() {
    const neurodivergencia = getNeurodivergenciaParameter();

    document.getElementById(neurodivergencia + "-content").classList.remove("d-none");
    document.getElementById(neurodivergencia + "-content").classList.add("d-block");
}

document.addEventListener("DOMContentLoaded", exibirConteudo);
