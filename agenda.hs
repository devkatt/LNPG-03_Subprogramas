import Data.List

type Evento = String
type Agenda = [Evento]

-- Função principal
main :: IO ()
main = do
    putStrLn "Bem-vindo ao Gerenciador de Agenda!"
    loop []

loop :: Agenda -> IO ()
loop agenda = do
    putStrLn "\nSelecione uma opção:"
    putStrLn "1. Adicionar evento"
    putStrLn "2. Remover evento"
    putStrLn "3. Visualizar agenda"
    putStrLn "4. Sair"
    opcao <- getLine
    case opcao of
        "1" -> adicionarEvento agenda
        "2" -> removerEvento agenda
        "3" -> visualizarAgenda agenda
        "4" -> putStrLn "Saindo do programa..."
        _   -> do
            putStrLn "Opção inválida. Tente novamente."
            loop agenda

adicionarEvento :: Agenda -> IO ()
adicionarEvento agenda = do
    putStrLn "Digite o evento a ser adicionado:"
    evento <- getLine
    let novaAgenda = agenda ++ [evento]
    putStrLn "Evento adicionado com sucesso!"
    loop novaAgenda

removerEvento :: Agenda -> IO ()
removerEvento agenda = do
    putStrLn "Digite o índice do evento a ser removido:"
    indice <- getLine
    if all (`elem` ['0'..'9']) indice
        then do
            let i = read indice
            if i >= 0 && i < length agenda
                then do
                    let eventoRemovido = agenda !! i
                    let novaAgenda = delete eventoRemovido agenda
                    putStrLn $ "Evento \"" ++ eventoRemovido ++ "\" removido com sucesso!"
                    loop novaAgenda
                else do
                    putStrLn "Índice inválido. Tente novamente."
                    loop agenda
        else do
            putStrLn "Índice inválido. Tente novamente."
            loop agenda

visualizarAgenda :: Agenda -> IO ()
visualizarAgenda agenda = do
    putStrLn "Eventos na Agenda:"
    mapM_ putStrLn agenda
    loop agenda
