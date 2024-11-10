<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.question"%>
<%@ page import="java.util.List"%>
<%
List<question> questionlist = (List<question>) request.getAttribute("questionlist");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>問題</title>
<script>
document.addEventListener('DOMContentLoaded', function() {
    // ラジオボタンが選択されたときにラベルの色を変更する
    const allQuestions = document.querySelectorAll('.answer');

    allQuestions.forEach((question, questionIndex) => {
        const radioButtons = question.querySelectorAll('input[type="radio"]');

        radioButtons.forEach((radioButton) => {
            radioButton.addEventListener('click', function() {
                // 各選択肢のラベル色をリセット
                radioButtons.forEach(rb => {
                    rb.parentElement.style.backgroundColor = '';  // ラベルの色をリセット
                });

                // 選択されたラベルの色を変更
                this.parentElement.style.backgroundColor = 'lightyellow';
            });
        });
    });
});
</script>
</head>
<body>
    <div class="title">科目：難易度</div>
    <%
    for (int i = 0; i < questionlist.size(); i++) {
        question question = questionlist.get(i);
    %>
    <div class="question">
        <p>
            問題<%=i + 1%>:
            <%=question.getQuestionText()%></p>
    </div>
    <div class="answer">
        <div class="answer1">
            <label for="1_<%=i%>">
                <input type="radio" id="1_<%=i%>" name="answer_<%=i%>">
                <%=question.getAnswer()%>
            </label>
        </div>
        <div class="answer2">
            <label for="2_<%=i%>">
                <input type="radio" id="2_<%=i%>" name="answer_<%=i%>">
                <%=question.getFakeAnswer1()%>
            </label>
        </div>
        <div class="answer3">
            <label for="3_<%=i%>">
                <input type="radio" id="3_<%=i%>" name="answer_<%=i%>">
                <%=question.getFakeAnswer2()%>
            </label>
        </div>
        <div class="answer4">
            <label for="4_<%=i%>">
                <input type="radio" id="4_<%=i%>" name="answer_<%=i%>">
                <%=question.getFakeAnswer3()%>
            </label>
        </div>
    </div>
    <%
    }
    %>

    <div class="back">
        <a href="./dojyo.html">もどる</a>
    </div>

    <div class="confirmation">
        <a href="#">答え合わせ</a>
    </div>
</body>
</html>
