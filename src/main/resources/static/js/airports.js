let airportApi = Vue.resource('/airports{/id}');
let countryApi = Vue.resource('/countries{/id}');

Vue.component('airport-form', {
    props: ['airports', 'countries'],
    template:
        '<div>' +
        '<select v-model="country">' +
        '<option value=""></option>' +
        '<option v-for="country in countries">{{country}}</option>' +
        '</select>' +
        '<input type="button" value="Filter" @click="filter" />' +
        '</div>',
    methods: {
        filter: function () {
        airportApi.get({id: this.country}).then(result =>
            result.json().then(data => {
                this.airports.splice(0, this.airports.length);
                data.forEach(airport => this.airports.push(airport))
                }
                )
            )
        }
    }
});

Vue.component('airport-row', {
    props: ['airport', 'airports'],
    template:
        '<tr>' +
        '<td>{{ airport.id }}</td>' +
        '<td>{{ airport.name }}</td>' +
        '<td>{{ airport.output }}</td>' +
        '<td>{{ airport.country }}</td>' +
        '<td>{{ airport.outputCode }}</td>' +
        '<td>{{ airport.inputCode }}</td>' +
        '<td>{{ airport.coordinateX }}</td>' +
        '<td>{{ airport.coordinateY }}</td>' +
        '<td>{{ airport.data1 }}></td>' +
        '<td>{{ airport.data2 }}</td>' +
        '<td>{{ airport.data3 }}</td>' +
        '<td>{{ airport.data4 }}</td>' +
        '<td>{{ airport.data5 }}</td>' +
        '<td>{{ airport.data6}}</td>' +
        '</tr>'
});

Vue.component('airports-list', {
    props: ['airports', 'countries'],
    template:
        '<div style="position: relative; width: 300px;">' +
        '<airport-form :airports="airports" :countries="countries"/>' +
        '  <table border="1">' +
        '   <caption>Airports</caption>' +
        '   <tr>' +
        '    <th>id</th>' +
        '    <th>name</th>' +
        '    <th>output</th>' +
        '    <th>input</th>' +
        '    <th>outputCode</th>' +
        '    <th>inputCode</th>' +
        '    <th>coordinateX</th>' +
        '    <th>coordinateY</th>' +
        '    <th>data1</th>' +
        '    <th>data2</th>' +
        '    <th>data3</th>' +
        '    <th>data4</th>' +
        '    <th>data5</th>' +
        '    <th>data6</th>' +
        '   </tr>' +
        '<airport-row v-for="airport in airports" :airport="airport"/>' +
        '</table>' +
        '</div>',
    created: function () {
        airportApi.get().then(result =>
            result.json().then(data =>
                data.forEach(airport => this.airports.push(airport))
                )
            ),
        countryApi.get().then(result =>
            result.json().then(data =>
                data.forEach(country => this.countries.push(country))
                )
            )

    }
});

let app = new Vue({
    el: '#app',
    template: '<airports-list :airports="airports" :countries="countries" />',
    data: {
        airports: [],
        countries: []
    }
});