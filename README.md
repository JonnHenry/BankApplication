# 🏦 Devsu Bank Application API

Base URLs:

* **Clients API:** `http://localhost:8001/api/clients`
* **Accounts API:** `http://localhost:8000/api/accounts`
* **Transactions API:** `http://localhost:8000/api/transactions`


Environment variable:  
Client ws URL `EXTERNAL_SERVICE_URL`, default value is `http://localhost:8001`

---

## 👥 Clients API

### 1️⃣ Get a Client

* **Method:** `GET`
* **Endpoint:** `/api/clients/{id}`
* **Description:** Retrieve a single client by ID.

**Response Example (200 OK):**

```json

{
    "id": 1,
    "dni": "0105476097",
    "name": "Jonnathan",
    "password": "123456789.",
    "gender": "M",
    "age": 21,
    "address": "prueba@prueba.com",
    "phone": "0984655221",
    "isActive": true
}
```

| Status | Meaning          |
| ------ | ---------------- |
| 200 ✅  | Client found     |
| 404 ❌  | Client not found |

---

### 2️⃣ Get All Clients

* **Method:** `GET`
* **Endpoint:** `/api/clients`
* **Description:** Retrieve all clients.

**Response Example:**

```json
[
    {
        "id": 1,
        "dni": "0105476099",
        "name": "Jonnathan",
        "password": "123456789.",
        "gender": "M",
        "age": 21,
        "address": "prueba@prueba.com",
        "phone": "0984655221",
        "isActive": true
    },
    {
        "id": 2,
        "dni": "0105476098",
        "name": "Jonnathan",
        "password": "123456789.",
        "gender": "M",
        "age": 21,
        "address": "prueba@prueba.com",
        "phone": "0984655221",
        "isActive": true
    }
]
```

---

### 3️⃣ Create a New Client

* **Method:** `POST`
* **Endpoint:** `/api/clients`
* **Description:** Creates a new client.

**Request Body:**

```json
{
	"dni": "0105476098",
	"name": "Jonnathan",
	"password": "123456789.",
	"gender": "M",
	"age": 21,
	"address": "prueba@prueba.com",
	"phone": "0984655221",
	"isActive": true
}
```

**Response Example (201 Created):**

```json
{
    "id": 2,
    "dni": "0105476098",
    "name": "Jonnathan",
    "password": "123456789.",
    "gender": "M",
    "age": 21,
    "address": "prueba@prueba.com",
    "phone": "0984655221",
    "isActive": true
}
```

---

### 4️⃣ Update Client Information

* **Method:** `PUT`
* **Endpoint:** `/api/clients/{id}`
* **Description:** Updates all fields of an existing client.

**Request Body Example:**

```json
{
    "id": 1,
    "dni": "0105476099",
    "name": "JONNATHAN",
    "password": "123456789.",
    "gender": "M",
    "age": 21,
    "address": "prueba@prueba.com",
    "phone": "0984655221",
    "isActive": false
}
```

---

### 5️⃣ Update Partial Client Information

* **Method:** `PATCH`
* **Endpoint:** `/api/clients/{id}`
* **Description:** Updates specific fields of a client (e.g., `isActive`).

**Request Body Example:**

```json
{
  "isActive": true
}
```

---

### 6️⃣ Delete a Client

* **Method:** `DELETE`
* **Endpoint:** `/api/clients/{id}`
* **Description:** Deletes a client by ID.

| Status | Meaning              |
| ------ | -------------------- |
| 204 ✅  | Successfully deleted |
| 404 ❌  | Client not found     |

---

## 🏦 Accounts API

### 1️⃣ Get All Accounts

* **Method:** `GET`
* **Endpoint:** `/api/accounts`
* **Description:** Retrieves all accounts.

---

### 2️⃣ Get an Account

* **Method:** `GET`
* **Endpoint:** `/api/accounts/{id}`
* **Description:** Retrieve a single account by ID.

---

### 3️⃣ Create an Account

* **Method:** `POST`
* **Endpoint:** `/api/accounts`
* **Description:** Creates a new account linked to a client.

**Request Body Example:**

```json
{
    "number": "0000000002",
    "type": "SAVINGS",
    "initialAmount": 100,
    "isActive": true,
    "clientId": 1
}
```

---

### 4️⃣ Update Account

* **Method:** `PUT`
* **Endpoint:** `/api/accounts/{id}`
* **Description:** Updates all fields of an account.

**Request Body Example:**

```json
{
	"number": "0000000001",
	"type": "SAVINGS",
	"initialAmount": 200,
	"clientId": 1,
	"isActive": true
}
```

---

### 5️⃣ Update Partial Account

* **Method:** `PATCH`
* **Endpoint:** `/api/accounts/{id}`
* **Description:** Updates specific fields of an account.

**Request Body Example:**

```json
{
  "isActive": false
}
```

---

### 6️⃣ Delete an Account

* **Method:** `DELETE`
* **Endpoint:** `/api/accounts/{id}`
* **Description:** Deletes an account by ID.

---

## 💳 Transactions API

### 1️⃣ Get All Transactions

* **Method:** `GET`
* **Endpoint:** `/api/transactions`
* **Description:** Retrieves all transactions.

---

### 2️⃣ Get a Transaction

* **Method:** `GET`
* **Endpoint:** `/api/transactions/{id}`
* **Description:** Retrieves a transaction by ID.

---

### 3️⃣ Create a Transaction

* **Method:** `POST`
* **Endpoint:** `/api/transactions`
* **Description:** Creates a new transaction linked to an account.

**Request Body Example:**

```json
{
    "type": "DEPOSITS",
    "amount": 10.203565,
    "accountId": "1"
}
```

---

### 4️⃣ Report Transactions by Client

* **Method:** `GET`
* **Endpoint:** `/api/transactions/clients/{clientId}/report`
* **Query Parameters:** `dateTransactionStart`, `dateTransactionEnd`
* **Description:** Retrieves all transactions for a client in a date range.

**Example Request:**

```
/api/transactions/clients/1/report?dateTransactionStart=2025-08-23&dateTransactionEnd=2025-08-27
```

---

### ⚡ Status Codes

| Status | Meaning            |
| ------ | ------------------ |
| 200 ✅  | Success            |
| 201 ✅  | Resource created   |
| 204 ✅  | Resource deleted   |
| 404 ❌  | Resource not found |
