new Vue({
	el : '#login',
	data : {
		user : {},
	},
	methods : {
		onSubmit : function(event) {
			this.$http.post("/shop-console/login", 
					{"name":this.user.name,"passwd":this.user.passwd}
			).then(function(response) {
				console.log(response);
				this.message = response.bodyText;
				this.$toast(this.message);
			}, function(response) {
				this.message = response.status;
				this.$toast("fail:" + this.message);
			});
		}
	}
})
