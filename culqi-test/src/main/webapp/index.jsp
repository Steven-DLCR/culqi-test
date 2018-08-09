<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
<meta name="author" content="Steven-DLCR">
<meta name="theme-color" content="#000000">
<title>PRUEBA API REST</title>

<link type="text/css" rel="stylesheet"
	href="//unpkg.com/bootstrap/dist/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet"
	href="//unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.css" />

</head>
<body>
	<div class="container" id="app">
		<template>
		<div class="p-3 mb-1">
			<b-form-group id="exampleInputGroup1" label="Pan:"
				label-for="exampleInput1"> <b-form-input
				id="exampleInput1" type="text" v-model="form.pan"> </b-form-input> </b-form-group>
			<b-form-group id="exampleInputGroup2" label="Exp Year:"
				label-for="exampleInput2"> <b-form-input
				id="exampleInput2" type="text" v-model="form.exp_year">
			</b-form-input> </b-form-group>
			<b-form-group id="exampleInputGroup3" label="Exp Month:"
				label-for="exampleInput3"> <b-form-input
				id="exampleInput3" type="text" v-model="form.exp_month">
			</b-form-input> </b-form-group>
			<b-button type="button" variant="primary" @click="enviar">Go
			API Rest</b-button>
			<b-button type="button" variant="danger" @click="reset">Reset
			Form</b-button>

		</div>
		</template>

		<div v-show="active">
			<b-card-group deck> 
				<b-card header="REQUEST"
						header-tag="header" 
						footer=" " 
						footer-tag="footer">
					<p class="card-text"><pre>{{ this.form }}</pre></p>
				</b-card> 
				<b-card header="RESPONSE" 
						header-tag="header" 
						footer=" "
						footer-tag="footer">
					<p class="card-text"><pre>{{ this.response }}</pre></p>
				</b-card>
			</b-card-group>
		</div>

	</div>

	<script src="resources/assets/lib/axios-0.16.1/axios.min.js"></script>
	<script src="resources/assets/lib/vue-2.3.3/vue.js"></script>
	<script src="//unpkg.com/babel-polyfill@latest/dist/polyfill.min.js"></script>
	<script src="//unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.js"></script>
	<script>
		Vue.config.debug = true;
		Vue.config.devtools = true;

		var vm = new Vue({
			el: '#app',
			data: {
				form: {
					pan: '457173604444442222',
					exp_year: 2020,
					exp_month: 10,
			      },
			      response: null,
			      active: false
			},

			mounted: function () {
				
			},

			methods: {
				enviar: function () {
					axios.post('http://localhost:8080/culqi-test/tokens', this.form)
						.then((response) => {
							this.response = response.data;
							this.active = true;
						})
						.catch((error) => {
							if (error.response) {
								this.response = error.response.data;
								this.response.status = error.response.status;
							}
							this.active = true;
						})
						
			    },
			    
			    reset: function () {
			      this.form.pan = '457173604444442222';
			      this.form.exp_year = 2020;
			      this.form.exp_month = 10;
			      
			      this.response = null;
			      
			      this.active = false;
			    }

			}
		})
		</script>
</body>

</html>