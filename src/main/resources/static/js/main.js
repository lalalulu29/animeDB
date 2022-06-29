function backToPage() {
    const page = document.getElementById("page")

    if(page.innerHTML !== '1') {
        const new_page_value = page.innerHTML - 1
        location.href = `/?page=${new_page_value}`;
    }
}

function nextToPage() {
    const page = document.getElementById("page")
    const max_page = document.getElementById("max_page")

    if(page.innerHTML !== max_page.innerHTML) {
        const new_page_value = Number(page.innerHTML) + 1
        location.href = `/?page=${new_page_value}`;
    }
}

function checkFirstAndLastPage() {
    const page = document.getElementById("page")
    const max_page = document.getElementById("max_page")

    if(page.innerHTML === '1') {
        document.getElementById("next_button").disabled = false
        document.getElementById("back_button").disabled = true

    } else if(page.innerHTML === max_page.innerHTML) {
        document.getElementById("back_button").disabled = false
        document.getElementById("next_button").disabled = true

    } else {
        document.getElementById("back_button").disabled = false
        document.getElementById("next_button").disabled = false

    }
}


window.onload = function() {
    checkFirstAndLastPage()
}