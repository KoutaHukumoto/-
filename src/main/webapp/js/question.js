
document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.answer input[type="radio"]').forEach((radio) => {
        radio.addEventListener('change', (event) => {
            // 同じ質問グループの選択肢の背景色をリセット
            document.querySelectorAll(`input[name="${event.target.name}"]`).forEach((btn) => {
                btn.nextElementSibling.style.backgroundColor = ''; // リセット
            });

            // チェックされたラジオボタンのラベルに色を付ける
            if (event.target.checked) {
                event.target.nextElementSibling.style.backgroundColor = '#4CAF50'; // 好きな色
            }
        });
    });
});