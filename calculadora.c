#include <stdio.h>

char obterOperador() {
    char operador;
    printf("Digite a operação (+, -, *, /): ");
    scanf(" %c", &operador);
    return operador;
}

float obterNumero() {
    float num;
    printf("Digite um número: ");
    scanf("%f", &num);
    return num;
}

float somar(float num1, float num2) {
    return num1 + num2;
}

float subtrair(float num1, float num2) {
    return num1 - num2;
}

float multiplicar(float num1, float num2) {
    return num1 * num2;
}

float dividir(float num1, float num2) {
    if (num2 != 0) {
        return num1 / num2;
    } else {
        printf("Erro! Divisão por zero.\n");
        return 0; // Retorno padrão em caso de erro
    }
}

int main() {
    char operador = obterOperador();
    float num1 = obterNumero();
    float num2 = obterNumero();
    
    float resultado;
    switch (operador) {
        case '+':
            resultado = somar(num1, num2);
            break;
        case '-':
            resultado = subtrair(num1, num2);
            break;
        case '*':
            resultado = multiplicar(num1, num2);
            break;
        case '/':
            resultado = dividir(num1, num2);
            break;
        default:
            printf("Operador inválido.\n");
            resultado = 0;
    }
    printf("Resultado: %.2f\n", resultado);
    
    return 0;
}
