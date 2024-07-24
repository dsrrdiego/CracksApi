let divv = document.getElementById('respuestaDiv')
document.getElementById('form').addEventListener('submit', function (event) {
  event.preventDefault() // Evitar que se envíe el formulario de forma tradicional

  const selectElement = document.getElementById('consulta')
  const seleccion = selectElement.value

  // Crear un objeto FormData para enviar los datos
  // const formData = new FormData();
  // formData.append("consulta", seleccion);
  // let formData=
  console.log(consulta.value)
  // Enviar los datos usando fetch
  fetch('/consulta', {
    // fetch("http://localhost:8080/consulta", {
    method: 'post',
    // body: formData
    body: consulta.value
  })
    .then(response => response.text())
    .then(data => {
      console.log(data) // Manejar la respuesta del servidor
    //   divv.innerHtml = data;
    divv.innerHTML=data;

    })
    .catch(error => {
      console.error('Error al enviar el formulario:', error)
    })
})
