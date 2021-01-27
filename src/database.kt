//Основной класс и методы(функции) класса.
class Database {
    private val listOfProducts = mutableListOf<Product>()

    //Бегаем по меню.
    fun startDB() {
        var how: String
        listOfProducts.add(Product("Магнитофон", "Музыкальное", "Хороший магнитофон", 5000))
        do {
            menu()
            how = readLine()!!
            when (how) {
                "1" -> {
                    productAdd()
                }
                "2" -> {
                    productEdit()
                }
                "3" -> {
                    productDelete()
                }
                "4" -> {
                    sort()
                }
                "5" -> {
                    search()
                }
                "6" -> {
                    println("--------------------[${listOfProducts.size}]--------------------")
                    printProducts(listOfProducts)
                    println("-------------------------------------------")
                }
            }
        } while (how != "7")
    }


    //Меню
    private fun menu() {
        println("[1] Добавление")
        println("[2] Изменение")
        println("[3] Удаление")
        println("[4] Сортировка")
        println("[5] Поиск")
        println("[6] Вывод на экран")
        println("[7] Завершить программу")
        print(">")
    }


    //Добавление товара
    private fun productAdd() {
        try{
            println("[Меню добавления]")
            println("Введите наименование товара: ")
            val name = readLine()!!.toString()
            println("Введите категорию товара: ")
            val category = readLine()!!.toString()
            println("Введите описание товара: ")
            val description = readLine()!!.toString()
            println("Введите цену товара: ")
            val price = readLine()!!.toInt()
            listOfProducts.add(Product(name, category, description, price))
        }
        catch(e:NumberFormatException)
        {
            println("Ошибка -  ($e), что говорит нам о том, что вы не правильно ввели цену товара;\nПовторите попытку!");
        }
    }


    //Редактирование товара
    private fun productEdit() {
        try{
            println("[Меню редактирования]")
            printProducts(listOfProducts)
            println("Введите номер товара:")
            val index = readLine()!!.toInt()
            if ((index <= 0) || (index > listOfProducts.size)) {
                println("Произошла непредвиденная ошибка!")
            } else {
                val product = listOfProducts.elementAt(index - 1)
                println("Вы изменяете данные продукта:\nНаименование товара - ${product.name}\nКатегория товара - ${product.category}\nОписание товара - ${product.description}\nЦена = ${product.price}\n")
                println("Введите наименование товара:")
                val name = readLine()!!.toString()
                product.name = name
                println("Введите категорию товара:")
                val category = readLine()!!.toString()
                product.category = category
                println("Введите описание товара:")
                val description = readLine()!!.toString()
                product.description = description
                println("Введите цену товара:")
                val price = readLine()!!.toInt()
                product.price = price
            }

        }
        catch(e:NumberFormatException)
        {
            println("Ошибка - [$e], повторите попытку!")
        }


    }
    //Удаление товара.
    private fun productDelete() {
        printProducts(listOfProducts)
        println("Введите уникальный идентификатор товара, который хотите удалить:\n")
        val index = readLine()!!.toInt()
        if ((index <= 0) || (index > listOfProducts.size)) {
            println("Ошибка!\nПовторите попытку\n")
        } else {
            listOfProducts.removeAt(index - 1)
            println("Товар успешно удален")
        }
    }
    //Сортировка товара
    private fun sort() {
        printFields()
        println("Введите номер поля, по которому будет происходить сортировка:")
        when (readLine()!!.toInt()) {
            1 -> listOfProducts.sortBy { it.name }
            2 -> listOfProducts.sortBy { it.category }
            3 -> listOfProducts.sortBy { it.description }
            4 -> listOfProducts.sortBy { it.price }
            in 1..4 -> println("Успешно отсортирован!")
            else -> println("Ошибка!\nПовторите попытку!")
        }
    }


    //Поиск товара
    private fun search() {
        printFields()
        println("Введите поле, по которому будет происходить поиск")
        val field = readLine()!!.toInt()
        print("Введите значение: ")
        val value = readLine()!!.toLowerCase()

        when (field) {
            1 -> printProducts(listOfProducts.filter { it.name.toLowerCase().contains(value) } as MutableList<Product>)
            2 -> printProducts(listOfProducts.filter { it.category.toLowerCase().contains(value) } as MutableList<Product>)
            3 -> printProducts(listOfProducts.filter { it.description.toLowerCase().contains(value) } as MutableList<Product>)
            else -> println("Ошибка!\nПовторите попытку!")
        }
    }

    //Вывод товаров на экран
    private fun printProducts(list: MutableList<Product>) {
        list.forEachIndexed { index, element -> println("Уникальный идентификатор - ${index+1}\n" +
                "Наименование товара - ${element.name}\n" +
                "Категория товара - ${element.category}\n" +
                "Описание товара - ${element.description}\n" +
                "Цена товара - ${element.price}\n") }
    }

    //Вывод полей, товара.
    private fun printFields() {
        println("1. Наименование товара")
        println("2. Категория товара")
        println("3. Описание товара")
    }
}