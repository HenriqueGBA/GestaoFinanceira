/*@Composable
fun HistoryScreen() {
    val context = LocalContext.current // Obter o contexto para o compartilhamento

    // Obter a instância do banco de dados e DAO
    val db = AppDatabase.getDatabase(context)
    val transactionDao = db.transactionDao()

    // Lista de transações do banco
    var transactions by remember { mutableStateOf(listOf<Transaction>()) }
    var newTitle by remember { mutableStateOf("") }
    var newDescription by remember { mutableStateOf("") }
    var newAmount by remember { mutableStateOf("") }
    var editingIndex by remember { mutableStateOf<Int?>(null) }

    // Carregar as transações do banco quando a tela é carregada
    LaunchedEffect(Unit) {
        transactions = transactionDao.getAllTransactions()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Campos para adicionar ou editar transação
        TextField(
            value = newTitle,
            onValueChange = { newTitle = it },
            label = { Text("Título da Transação") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = newDescription,
            onValueChange = { newDescription = it },
            label = { Text("Descrição da Transação") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = newAmount,
            onValueChange = { newAmount = it },
            label = { Text("Valor da Transação (R$)") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botões para adicionar, editar ou excluir transação
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (editingIndex != null) {
                // Editar Transação
                IconButton(onClick = {
                    if (newTitle.isNotEmpty() && newDescription.isNotEmpty() && newAmount.isNotEmpty()) {
                        val updatedTransaction = Transaction(
                            transactions[editingIndex!!].id,
                            newTitle,
                            newDescription,
                            newAmount.toDouble(),
                            true // Assuming positive for now
                        )
                        transactionDao.updateTransaction(updatedTransaction)
                        editingIndex = null
                        newTitle = ""
                        newDescription = ""
                        newAmount = ""
                    }
                }) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar Transação")
                }
            } else {
                // Adicionar Nova Transação
                IconButton(onClick = {
                    if (newTitle.isNotEmpty() && newDescription.isNotEmpty() && newAmount.isNotEmpty()) {
                        val newTransaction = Transaction(0, newTitle, newDescription, newAmount.toDouble(), true)
                        transactionDao.insertTransaction(newTransaction)
                        newTitle = ""
                        newDescription = ""
                        newAmount = ""
                    }
                }) {
                    Icon(Icons.Default.Add, contentDescription = "Adicionar Transação")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de transações
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(transactions) { transaction ->
                TransactionCard(transaction, context, onDelete = {
                    transactionDao.deleteTransaction(transaction)
                    transactions = transactions.filter { it != transaction }
                }, onEdit = {
                    editingIndex = transactions.indexOf(transaction)
                    newTitle = transaction.title
                    newDescription = transaction.description
                    newAmount = transaction.amount.toString()
                })
            }
        }
    }
}
*/