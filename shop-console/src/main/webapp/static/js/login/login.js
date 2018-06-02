var vm = new Vue({
  el: '#login',
  data: {
    user: {}
  },
  methods: {
	  onSubmit: function (event) {
		  alert("login: " + this.user.name);
    }
  }
})