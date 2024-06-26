<script setup>
import { ref } from 'vue';
import axios from 'axios';
import { getAddressByCep } from '../../services/getAddressByCep.vue';
import ModalWarning from '../screenMessage/ModalWarning.vue';
import { useRouter } from 'vue-router';

const router = useRouter();

const name = ref('');
const cpf = ref('');
const email = ref('');
const phone = ref('');
const cep = ref('');
const street = ref('');
const numberStreet = ref('');
const complement = ref('');
const state = ref('');
const city = ref('');
const neighborhood = ref('');
const modalMessage = ref('');

const getAddressData = async (cepValue) => {
  try {
    return await getAddressByCep(cepValue.replace('-', ''));
  } catch (error) {
    console.error('Erro ao buscar o endereço:', error);
    return null;
  }
};

const formatCep = async (event) => {
  let cepValue = event.target.value.replace(/\D/g, '');
  if (cepValue.length > 5) {
    cepValue = cepValue.slice(0, 5) + '-' + cepValue.slice(5, 8);
  }
  cep.value = cepValue;

  if (cep.value.length === 9) {
    const address = await getAddressData(cep.value);
    if (address) {
      street.value = address.logradouro;
      neighborhood.value = address.bairro;
      city.value = address.localidade;
      state.value = address.uf;
    }
  }
};

const formatCpf = (event) => {
  let cpfValue = event.target.value.replace(/\D/g, '');

  if (cpfValue.length > 3) {
    cpfValue = cpfValue.slice(0, 3) + '.' + cpfValue.slice(3);
  }
  if (cpfValue.length > 7) {
    cpfValue = cpfValue.slice(0, 7) + '.' + cpfValue.slice(7);
  }
  if (cpfValue.length > 11) {
    cpfValue = cpfValue.slice(0, 11) + '-' + cpfValue.slice(11);
  }

  cpf.value = cpfValue;
};

const formatPhone = (event) => {
  let phoneNumber = event.target.value.replace(/\D/g, '');

  if (phoneNumber.length > 0) {
    phoneNumber = '(' + phoneNumber;
  }
  if (phoneNumber.length > 3) {
    phoneNumber = phoneNumber.slice(0, 3) + ') ' + phoneNumber.slice(3);
  }
  if (phoneNumber.length > 10) {
    phoneNumber = phoneNumber.slice(0, 10) + '-' + phoneNumber.slice(10);
  }

  phone.value = phoneNumber;
};

const createCustomer = async () => {
  const address = `CEP: ${cep.value}, Rua: ${street.value}, Número: ${numberStreet.value}, Complemento: ${complement.value}, Bairro: ${neighborhood.value}, Cidade: ${city.value}, Estado: ${state.value}`;
  
  try {
    const response = await axios.post("http://localhost:8080/api/v1/customers", {
      name: name.value,
      cpf: cpf.value,
      phone: phone.value,
      email: email.value,
      address: address
    });

    const modal = document.getElementById("modal");
    modal.style.display = 'block';
    modalMessage.value = 'Cliente cadastrado com sucesso!';
    setTimeout(() => {
        router.push('/clientes')
    }, 2000);

  } catch (error) {
    console.error('Erro ao criar o cliente:', error);
  }
};
</script>

<template>
    <section>
        <form @submit.prevent="createCustomer">
            <h2>Cadastro de Cliente</h2>
            <div class="row mt-3">
                <div class="col-md-8">
                    <label for="inputName" class="form-label">Nome Completo</label>
                    <input type="text" class="form-control" placeholder="Digite Nome Completo" maxlength="45"
                        aria-label="Nome Completo" v-model="name" required>
                </div>
                <div class="col-md-4">
                    <label for="cpf" class="form-label">CPF</label>
                    <input type="text" class="form-control" id="cpf" @input="formatCpf" maxlength="14" required
                        placeholder="xxx.xxx.xxx-xx" v-model="cpf">
                </div>
            </div>
            <div class="row mt-3  ">
                <div class="col-md-8">
                    <label for="inputEmail4" class="form-label">Email</label>
                    <input type="email" class="form-control" maxlength="150" id="inputEmail4" v-model="email"
                        placeholder="E-mail">
                </div>
                <div class="col-md-4">
                    <label for="cel" class="form-label">Celular</label>
                    <input type="tel" class="form-control" id="cel" maxlength="15" required
                        placeholder="(xx) xxxxx-xxxx" v-model="phone" @input="formatPhone">
                </div>
            </div>
            <div class="row mt-4">
                <div class="col-12">
                    <h4>Endereço</h4>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label for="cep" class="form-label">CEP</label>
                    <input type="text" class="form-control" id="cep" required placeholder="xxxxx-xxx" v-model="cep"
                        maxlength="9" @input="formatCep">
                </div>
                <div class="col-md-8">
                    <label for="street" class="form-label">Rua</label>
                    <input type="text" class="form-control" id="inputStreet" v-model="street" required>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-md-4">
                    <label for="inputState" class="form-label">Número</label>
                    <input type="text" class="form-control" id="inputZip" v-model="numberStreet">
                </div>
                <div class="col-md-8">
                    <label for="inputZip" class="form-label">Complemento</label>
                    <input type="text" class="form-control" id="inputZip" v-model="complement">
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-md-4">
                    <label for="inputCity" class="form-label">Estado</label>
                    <input type="text" class="form-control" id="inputCity" v-model="state" required>
                </div>
                <div class="col-md-6">
                    <label for="inputState" class="form-label">Cidade</label>
                    <input type="text" class="form-control" id="inputZip" v-model="city" required>
                </div>

                <div class="col-md-2">
                    <label for="inputZip" class="form-label">Bairro</label>
                    <input type="text" class="form-control" id="inputZip" v-model="neighborhood" required>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-12 d-flex justify-content-end mt-4">
                    <button type="submit" class="btn btn-primary me-2">Salvar</button>
                    <a @click="$router.go(-1)" class="btn btn-secondary">Cancelar</a>
                </div>
            </div>
        </form>
    </section>
    <ModalWarning :modalText="modalMessage" id="modal"/>
</template>
