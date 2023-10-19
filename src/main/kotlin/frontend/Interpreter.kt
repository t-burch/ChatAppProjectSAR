package frontend

import data.MessageStore
import data.SharedStore.alive
import message.MessageUtils

object Interpreter {

    private val commandMap: MutableMap<String, (List<String>) -> Unit> = mutableMapOf()

    init {
        // Map methods that have the @Command annotation to commandMap.
        this::class.members.filter{
            it.annotations.any{ annotation ->
                annotation is Command
            }
        }.forEach{ function ->
            commandMap[function.name] = { args: List<String> ->
                function.call(this, args)
            }
        }
    }

    @Command
    fun `target-host`(args: List<String>) {

    }
    @Command
    fun t(args: List<String>){
        `target-host`(args)
    }

    @Command
    fun `direct-connect`(args: List<String>) {
        alive = false
        Console.put("Direct Connect 1111111111")
    }
    @Command
    fun dc(args: List<String>) {
        `direct-connect`(args)
    }

    @Command
    fun quit(args: List<String>) {
        alive = false
        Console.put("")
    }

    private fun submitMessage(msg: String) {
        MessageUtils.storeUserMessage(msg)
        // TODO Use the MessageSerializer to serialize and then the MessageTransceiver to actually send the message.
    }

    fun processInput(prompt: String) {
        val promptTrimmed = prompt.trim()

        if (!promptTrimmed.startsWith("/")) {
            submitMessage(prompt)
            return
        }

        val tokens = promptTrimmed.drop(1).split(" ")
        val command = tokens[0]
        val args = tokens.drop(1)

        commandMap[command]?.invoke(args) ?: MessageUtils.storeSystemMessage("Unknown command: $command")
    }
}

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Command