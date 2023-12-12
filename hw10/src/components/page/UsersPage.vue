<template>
  <div class="users datatable">
    <div class="caption">User</div>
    <table>
      <thead>
      <tr>
        <th>Id</th>
        <th>Login</th>
        <th>Name</th>
      </tr>
      </thead>
      <tbody>
      <div v-if="users.size === 0">
        <tr class="noData">
          <td colspan="3">
            No data
          </td>
        </tr>
      </div>
      <template v-else>
        <tr v-for="user in users" :key="user.id">
          <td class="id">{{user.id}}</td>
          <td class="login">{{user.login}}</td>
          <td class="name">{{user.name}}</td>
        </tr>
      </template>
    </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: "UsersPage",
  props: ["users"]
}
</script>

<style scoped>

</style>