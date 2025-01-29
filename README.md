# 🛍️ JavaFX Product Manager
This project is a Java application for managing a list of products. It includes functionalities to add, delete, search, and display products.

## ✨ Features
* ➕ Add new products to the list
* ❌ Delete products from the list
* 🔍 Search for products by various criteria 
* ✏️ Modify a product's details given its ID 
* 💾 Save the list to a JSON file
* 📋 Display all products in the list

## 🚀 Installation
1. **Download the project JAR from the repository**
2. **Run the JAR file using the following command:**
```bash
java -jar ProductManager.jar
```

---

# 🛠️ How to use

### 1. First time running the program
* 🆕 You will see an empty list of products.
* 📝 Fill in the product details and click the **"Add Product"** button.
* ➕ You can add as many products as you want.
* 💾 You will need to save the list to a new JSON file of your choice by clicking the **"Save"** button.
* 📂 After saving the list, you can load it back by clicking the **"Load"** button and selecting the JSON file.
* 🔄 When you load a list of products, the program will allow you to use the **"Save"** button directly to save the list to the same file you have loaded.

### 2. Search for products by various criteria
* 🔍 Fill in the search criteria and click the **"Search"** button.
* 📋 The program will fill in the text fields with the product details.
* 🔄 You can modify the product details and click the **"Update Product"** button to save the changes.
* ❌ If no products match the criteria, a message will be displayed.
* 🔄 You can search by any combination of criteria.

### 3. Delete products from the list
* 🗑️ Select the product you want to delete from the list.
* ❌ Click the **"Delete Product"** button.
* 🆓 The product will be removed from the list.
* 🔄 You can delete as many products as you want.

### 4. Display all products in the list
* 📋 Click the **"Display All"** button.
* 👀 The program will display all products in the list.
* ❌ If the list is empty, a message will be displayed.

---

# 📝 Notes
* 📂 The program products are stored in a JSON file that can be loaded and saved.
* 🖥️ The program uses JavaFX for the GUI
* 🛠️ The program uses a manual JSON parser for serialization and deserialization



# 🤝  Contributing 
Pull requests are welcome. 
For major changes, please open an issue first to discuss what you would like to change.

# 📄 License
This project is licensed under the MIT License.