// const chartDataStr = decodeHtml(chartData);
let chartJsonArray = JSON.parse(decodeHtml(chartData));

let arrLength = chartJsonArray.length;

let numericData = [];
let labelData = [];

for (let i = 0; i < arrLength; i++) {
    numericData[i] = chartJsonArray[i].projectStageCount;
    labelData[i] = chartJsonArray[i].label;
}

const ctx = document.getElementById('myPieChart');
new Chart(ctx, {
    type: 'pie',
    data: {
        labels: labelData,
        datasets: [{
            label: '#',
            backgroundColor: ["#3e95cd", "#8e5ea2", "3cba9f"],
            data: numericData
        }]
    },
    options: {
        title: {
            display: true,
            text: 'Project Status'
        }
    }
});

function decodeHtml(html) {
    const txt = document.createElement("textarea");
    txt.innerHTML = html;
    return txt.value;
}