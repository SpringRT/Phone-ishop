function addProductToBucket(productId) {
    var url = "/add_to_bucket?product_id=" + productId;
    var xhr = new XMLHttpRequest();
    xhr.open('GET', url, false);
    xhr.send();
    if (xhr.status != 200) {
        alert( xhr.status + ': ' + xhr.statusText );
    } else {
        document.getElementById("bucket_size_id").innerHTML=xhr.responseText;
        alert('Product is added to bucket successfully!');

    }
}

function changeLoginSize(input) {
    if (input.value.length > 10){
        input.value = input.value.substring(0,10);
    }
    document.getElementById("login_size_id").innerHTML=input.value.length;

}