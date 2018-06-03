var vm = new Vue({
  el: '#login',
  data: {
	  message:"",
	  user: {}
  },
  methods: {
	  onSubmit: function (event) {
		  // alert("login: " + this.user.name);
		  this.$http.get("login/"+this.user.name,{params: this.user},{emulateJSON: true}).then(function(response){
			  console.log(response);
			  this.message = response.bodyText;
			  // alert("succ:" + response.bodyText);
		  }, function(response){
			  this.message = response.status;
		  });
    },
  }
})

// 基于全局Vue对象使用http
// Vue.http.get('/someUrl', [options]).then(successCallback, errorCallback);
// Vue.http.post('/someUrl', [body], [options]).then(successCallback, errorCallback);

// 在一个Vue实例内使用$http
// this.$http.get('/someUrl', [options]).then(successCallback, errorCallback);
// this.$http.post('/someUrl', [body], [options]).then(successCallback, errorCallback);