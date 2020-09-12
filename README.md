# LanguageLib

## Information
LanguageLib is a simple library to make it easier to handle multi languages

### Repository
````xml
````

#### Usage

##### Create instance

Available implementations:
  - BukkitLanguageLib
  - BungeeLanguageLib

````java
LanguageLib languageLib = new BukkitLanguageLib.builder(your plugin, "language").build();
````

In any case, those who want to put their own implementations can use
````java
LanguageLib languageLib = BukkitLanguageLib.builder(plugin, "en")
                                    .setFileLoader(fileLoader)
                                    .setFilesManageable(filesManageable)
                                    .setTranslationManager(translationManager)
                                    .setI18n(i18n)
                                    .build();
````

##### Get simple message
````java
public void sendMessage(Player player) {
    TranslatableMessage translateMessage = languageLib.getTranslationManager().getTranslation("path");
    
    // Set variable from message
    translateMessage.setVariable("%player_name%", player.getName());

    // Set color from message
    translateMessage.colorize();
    
    //Get message
    String message = translateMessage.getMessage("language");

    player.sendMessage(message);
}
````

##### Get list messages
````java
public void sendMessages(Player player) {
    TranslatableMessage translateMessage = languageLib.getTranslationManager().getTranslation("path");
    
    // Set variable from messages
    translateMessage.setVariable("%player_name%", player.getName());

    // Set color from messages
    translateMessage.colorize();
    
    //Get messages in List format
    List<String> message = translateMessage.getMessages("language");

    message.forEach(player::sendMessage);
}
````

#### Placeholders

#### Create your placeholders

##### There are 2 ways available to create placeholders

The first is this
````java
public class TestPlaceholderApplier implements PlaceholderApplier {

    public String set(Object holder, String text) {
          if (holder == null) {
              return text;
          }
          if (!(holder instanceof Player)) {
              return text;
          }
          Player player = (Player) player;

          return text.replace("%player_name%", player.getName());
    }
}
````

And the second is this
````java
translateMessage#addPlaceholder((holder, text) -> {
          if (holder == null) {
              return text;
          }
          if (!(holder instanceof Player)) {
              return text;
          }
          Player player = (Player) player;

          return text.replace("%player_name%", player.getName());
})
````

#### Add your placeholders from message

##### In order to add them as a placeholder, they have to implement PlaceholderApplier

We can add 1 single placeholder

```java
translateMessage#addPlaceholder(new YourPlaceholder())
```

or multiple

```java
translateMessage#addPlaceholders(new PlaceholderTest(), new Placeholder());
```

