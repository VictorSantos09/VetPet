<script>
</script>

<template>

    <!-- Sidebar -->
    <nav id="sidebarMenu" class="collapse d-lg-block sidebar collapse bg-white">
        <div class="position-sticky">
            <div class="list-group list-group-flush mx-3">
                <!--Aba para acessar funcionalidades de cliente -->
                <!--<a href="#" class="list-group-item list-group-item-action py-2 ripple">
                    <li class="dropdown">
                        <button class="btn btn-white dropdown-toggle text-black text-aling-center" type="button"
                            id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                            Clientes
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <li>
                                <router-link 
                                    to="/principal/cadastro/cliente"
                                    class="dropdown-item">
                                    Cadastro
                                </router-link>
                            </li>
                            <li>
                                <router-link
                                    to="/list/cliente"
                                    class="dropdown-item">
                                    Consultar
                                </router-link>
                            </li>
                        </ul>
                    </li>
                </a>-->

                <router-link 
                    to="/agenda"
                    class="list-group-item list-group-item-action py-2 ripple">
                    Agenda
                </router-link>
                <router-link 
                    to="/clientes"
                    class="list-group-item list-group-item-action py-2 ripple">
                    Clientes
                </router-link>
                <router-link 
                    to="/especies"
                    class="list-group-item list-group-item-action py-2 ripple">
                    Espécies
                </router-link>
                <router-link 
                    to="/racas"
                    class="list-group-item list-group-item-action py-2 ripple">
                    Raças
                </router-link>
                <router-link 
                    to="/servicos"
                    class="list-group-item list-group-item-action py-2 ripple">
                    Serviço
                </router-link>
                <router-link 
                    to="/funcionarios"
                    class="list-group-item list-group-item-action py-2 ripple">
                    Funcionários
                </router-link>
            </div>
        </div>
    </nav>
</template>

<style>
.sidebar {
    width: 210px;
    height: 100vh;
}

li {
    list-style-type: none;
}
</style>